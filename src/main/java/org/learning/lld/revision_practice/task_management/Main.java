package org.learning.lld.revision_practice.task_management;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

enum TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED
}

@Getter
@Setter
class Task {
    private final String id;
    private String title;
    private String description;
    private Date dueDate;
    private int priority;
    private TaskStatus status;
    private User assignedUser;

    public Task(String id, String title, String description, Date dueDate, int priority, User assignedUser) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = TaskStatus.PENDING;
        this.assignedUser = assignedUser;
    }
}

@AllArgsConstructor
@Getter
@ToString
class User {
    private final String id;
    private final String name;
    private final String email;
}

class TaskManager {
    private static TaskManager instance;
    private final Map<String, Task> tasks;
    private final Map<String, List<Task>> userTasks;

    private TaskManager() {
        tasks = new ConcurrentHashMap<>();
        userTasks = new ConcurrentHashMap<>();
    }

    public static synchronized TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }

        return instance;
    }

    public void createTask(Task task) {
        tasks.put(task.getId(), task);
        assignTaskToUser(task.getAssignedUser(), task);
    }

    public void updateTask(Task updatedTask) {
        Task existingTask = tasks.get(updatedTask.getId());
        if (existingTask != null) {
            synchronized (existingTask) {
                existingTask.setTitle(updatedTask.getTitle());
                existingTask.setDescription(updatedTask.getDescription());
                existingTask.setDueDate(updatedTask.getDueDate());
                existingTask.setStatus(updatedTask.getStatus());
                existingTask.setPriority(updatedTask.getPriority());
                User previousUser = existingTask.getAssignedUser();
                User newUser = updatedTask.getAssignedUser();

                if (!previousUser.equals(newUser)) {
                    unassignTaskFromUser(previousUser, existingTask);
                    assignTaskToUser(newUser, existingTask);
                }
            }
        }
    }

    private void unassignTaskFromUser(User user, Task task) {
        List<Task> tasks = userTasks.get(user.getId());
        if (task != null){
            tasks.remove(task);
        }
    }

    public void deleteTask(String taskId) {
        Task task = tasks.remove(taskId);
        if (task != null) unassignTaskFromUser(task.getAssignedUser(), task);
    }

    public List<Task> searchTasks(String keyword) {
        List<Task> matchTasks = new ArrayList<>();
        for (Task task: tasks.values()){
            if (task.getTitle().contains(keyword) || task.getDescription().contains(keyword)){
                matchTasks.add(task);
            }
        }
        return matchTasks;
    }

    public List<Task> filterTasks(TaskStatus status, Date startDate, Date endDate, int priority) {
        List<Task> filteredTask = new ArrayList<>();
        for (Task task: tasks.values()) {
            if (task.getStatus() == status &&
            task.getDueDate().compareTo(startDate) >= 0 &&
            task.getDueDate().compareTo(endDate) <=0 && task.getPriority() == priority) {
                filteredTask.add(task);
            }
        }
        return filteredTask;
    }

    public synchronized void markTaskAsCompleted(String taskId) {
        Task task = tasks.get(taskId);
        if (task != null) {
                task.setStatus(TaskStatus.COMPLETED);
        }
    }

    public List<Task> getTaskHistory(User user) {
        return new ArrayList<>(userTasks.getOrDefault(user.getId(), new ArrayList<>()));
    }

    private void assignTaskToUser(User user, Task task) {
        userTasks.computeIfAbsent(user.getId(), k -> new CopyOnWriteArrayList<>()).add(task);
    }
}

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = TaskManager.getInstance();

        // Create users
        User user1 = new User("1", "John Doe", "john@example.com");
        User user2 = new User("2", "Jane Smith", "jane@example.com");

        // Create tasks
        Task task1 = new Task("1", "Task 1", "Description 1", new Date(), 1, user1);
        Task task2 = new Task("2", "Task 2", "Description 2", new Date(), 2, user2);
        Task task3 = new Task("3", "Task 3", "Description 3", new Date(), 1, user1);

        // Add tasks to the task manager
        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createTask(task3);

        // Update a task
        task2.setDescription("Updated description");
        taskManager.updateTask(task2);

        // Search tasks
        List<Task> searchResults = taskManager.searchTasks("Task");
        System.out.println("Search Results:");
        for (Task task : searchResults) {
            System.out.println(task.getTitle());
        }

        // Filter tasks
        List<Task> filteredTasks = taskManager.filterTasks(TaskStatus.PENDING, new Date(0), new Date(), 1);
        System.out.println("Filtered Tasks:");
        for (Task task : filteredTasks) {
            System.out.println(task.getTitle());
        }

        // Mark a task as completed
        taskManager.markTaskAsCompleted("1");

        // Get task history for a user
        List<Task> taskHistory = taskManager.getTaskHistory(user1);
        System.out.println("Task History for " + user1.getName() + ":");
        for (Task task : taskHistory) {
            System.out.println(task.getTitle());
        }

        // Delete a task
        taskManager.deleteTask("3");
    }
}
