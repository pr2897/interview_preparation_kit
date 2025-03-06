package org.learning.lld.revision_practice.StackOverflow;

import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Getter
class User {
    private int id;
    private String username;
    private String email;
    private int reputation;
    private final List<Question> questions;
    private final List<Answer> answers;
    private final List<Comment> comments;

    private static final int QUESTION_REPUTATION = 5;
    private static final int ANSWER_REPUTATION = 10;
    private static final int COMMENT_REPUTATION = 2;

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.reputation = 0;
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Question askQuestion(String title, String content, List<String> tags) {
        Question question = new Question(this, title, content, tags);
        questions.add(question);
        updateReputation(QUESTION_REPUTATION);
        return question;
    }

    public Answer answerQuestion(Question question, String content) {
        Answer answer = new Answer(this, question, content);
        answers.add(answer);
        updateReputation(ANSWER_REPUTATION);
        return answer;
    }

    public Comment addComment(Commentable commentable, String content) {
        Comment comment = new Comment(this, content);
        comments.add(comment);
        commentable.addComment(comment);
        updateReputation(COMMENT_REPUTATION);
        return comment;
    }

    public synchronized void updateReputation(int value) {
        this.reputation += value;
        if (this.reputation < 0) this.reputation = 0;
    }
}

interface Votable {
    void vote(User user, int value);
    int getVoteCount();
}

interface Commentable {
    void addComment(Comment comment);
    List<Comment> getComments();
}

@Getter
class Question implements Votable, Commentable {
    private final int id;
    private final String title;
    private final String content;
    private final User author;
    private final Date creationDate;
    private final List<Answer> answers;
    private final List<Comment> comments;
    private final List<Tag> tags;
    private final List<Vote> votes;

    public Question(User author, String title, String content, List<String> tags) {
        this.id = generateId();
        this.author = author;
        this.title = title;
        this.content = content;
        this.creationDate = new Date();
        this.answers = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.votes = new ArrayList<>();
        this.comments = new ArrayList<>();

        tags.forEach(tag -> this.tags.add(new Tag(tag)));
    }

    public void addAnswer(Answer answer) {
        if (!answers.contains(answer)) {
            answers.add(answer);
        }
    }

    @Override
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(this.comments);
    }

    @Override
    public void vote(User user, int value) {
        if (value != 1 && value != -1) {
            throw new IllegalArgumentException("Vote must be either 1 or -1");
        }
        votes.removeIf(v -> v.getUser().equals(user));
        votes.add(new Vote(user, value));
        author.updateReputation(value * 5);
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(Vote::getValue).sum();
    }

    private int generateId(){
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }
}

@Getter
class Answer implements Votable, Commentable {
    private final int id;
    private final String content;
    private final User author;
    private final Question question;
    private boolean isAccepted;
    private final Date creationDate;
    private final List<Comment> comments;
    private final List<Vote> votes;

    public Answer(User author, Question question, String content) {
        this.id = generateId();
        this.author = author;
        this.question = question;
        this.content = content;
        this.creationDate = new Date();
        this.votes = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.isAccepted = false;

        this.question.addAnswer(this);
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public void vote(User user, int value) {
        if (value != 1 && value != -1) {
            throw new IllegalArgumentException("value must be 1 or -1");
        }

        votes.removeIf(v -> v.getUser().equals(user));
        votes.add(new Vote(user, value));
        author.updateReputation(value * 10);
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(Vote::getValue).sum();
    }

    public void markAsAccepted() {
        if (isAccepted) {
            throw new IllegalArgumentException("this answer is already accepted");
        }
        isAccepted = true;
        author.updateReputation(15);
    }

    private int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }
}

@Getter
class Comment {
    private final int id;
    private final String content;
    private final User author;
    private final Date creationDate;

    public Comment(User author, String content) {
        this.id = generateId();
        this.author = author;
        this.content = content;
        this.creationDate = new Date();
    }

    public int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }
}

@Getter
class Tag {
    private final int id;
    private final String name;

    public Tag(String name) {
        this.id = generateId();
        this.name = name;
    }

    private int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }
}

@Getter
class Vote {
    private final User user;
    private final int value;

    public Vote(User user, int value) {
        this.user = user;
        this.value = value;
    }
}

class StackOverFlow {
    private final Map<Integer, User> users;
    private final Map<Integer, Question> questions;
    private final Map<Integer, Answer> answers;
    private final Map<String, Tag> tags;

    public StackOverFlow() {
        this.users = new ConcurrentHashMap<>();
        this.questions = new ConcurrentHashMap<>();
        this.answers = new ConcurrentHashMap<>();
        this.tags = new ConcurrentHashMap<>();
    }

    public User createUser(String username, String email) {
        int id = users.size() + 1;
        User user = new User(id, username, email);
        users.put(id, user);
        return user;
    }

    public Question askQuestion(User user, String title, String content, List<String> tags) {
        Question question = user.askQuestion(title, content, tags);
        questions.put(question.getId(), question);

        for (Tag tag: question.getTags()) {
            this.tags.putIfAbsent(tag.getName(), tag);
        }

        return question;
    }

    public Answer answerQuestion(User user, Question question, String content) {
        Answer answer = user.answerQuestion(question, content);
        answers.put(answer.getId(), answer);
        return answer;
    }

    public Comment addComment(User user, Commentable commentable, String content) {
        return user.addComment(commentable, content);
    }

    public void vote(User user, Votable votable, int value) {
        votable.vote(user, value);
    }

    public void acceptAnswer(Answer answer) {
        answer.markAsAccepted();
    }

    public List<Question> searchQuestions(String query) {
        return questions.values().stream()
                .filter(q -> q.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        q.getContent().toLowerCase().contains(query.toLowerCase()) ||
                        q.getTags().stream().anyMatch(t ->t.getName().equalsIgnoreCase(query)))
                .toList();
    }

    public List<Question> getQuestionsByUser(User user) {
        return user.getQuestions();
    }

    // Getters
    public User getUser(int id) { return users.get(id); }
    public Question getQuestion(int id) { return questions.get(id); }
    public Answer getAnswer(int id) { return answers.get(id); }
    public Tag getTag(String name) { return tags.get(name); }
}

public class DriverCode {
    public static void main(String[] args) {
        StackOverFlow system = new StackOverFlow();

        // Create users
        User alice = system.createUser("Alice", "alice@example.com");
        User bob = system.createUser("Bob", "bob@example.com");
        User charlie = system.createUser("Charlie", "charlie@example.com");

        // Alice asks a question
        Question javaQuestion = system.askQuestion(alice, "What is polymorphism in Java?",
                "Can someone explain polymorphism in Java with an example?",
                Arrays.asList("java", "oop"));

        // Bob answers Alice's question
        Answer bobAnswer = system.answerQuestion(bob, javaQuestion,
                "Polymorphism in Java is the ability of an object to take on many forms...");

        // Charlie comments on the question
        system.addComment(charlie, javaQuestion, "Great question! I'm also interested in learning about this.");

        // Alice comments on Bob's answer
        system.addComment(alice, bobAnswer, "Thanks for the explanation! Could you provide a code example?");

        // Charlie votes on the question and answer
        system.vote(charlie, javaQuestion, 1);  // Upvote
        system.vote(charlie, bobAnswer, 1);  // Upvote

        // Alice accepts Bob's answer
        system.acceptAnswer(bobAnswer);

        // Bob asks another question
        Question pythonQuestion = system.askQuestion(bob, "How to use list comprehensions in Python?",
                "I'm new to Python and I've heard about list comprehensions. Can someone explain how to use them?",
                Arrays.asList("python", "list-comprehension"));

        // Alice answers Bob's question
        Answer aliceAnswer = system.answerQuestion(alice, pythonQuestion,
                "List comprehensions in Python provide a concise way to create lists...");

        // Charlie votes on Bob's question and Alice's answer
        system.vote(charlie, pythonQuestion, 1);  // Upvote
        system.vote(charlie, aliceAnswer, 1);  // Upvote

        // Print out the current state
        System.out.println("Question: " + javaQuestion.getTitle());
        System.out.println("Asked by: " + javaQuestion.getAuthor().getUsername());
        System.out.println("Tags: " + javaQuestion.getTags().stream().map(Tag::getName).reduce((a, b) -> a + ", " + b).orElse(""));
        System.out.println("Votes: " + javaQuestion.getVoteCount());
        System.out.println("Comments: " + javaQuestion.getComments().size());
        System.out.println("\nAnswer by " + bobAnswer.getAuthor().getUsername() + ":");
        System.out.println(bobAnswer.getContent());
        System.out.println("Votes: " + bobAnswer.getVoteCount());
        System.out.println("Accepted: " + bobAnswer.isAccepted());
        System.out.println("Comments: " + bobAnswer.getComments().size());

        System.out.println("\nUser Reputations:");
        System.out.println("Alice: " + alice.getReputation());
        System.out.println("Bob: " + bob.getReputation());
        System.out.println("Charlie: " + charlie.getReputation());

        // Demonstrate search functionality
        System.out.println("\nSearch Results for 'java':");
        List<Question> searchResults = system.searchQuestions("java");
        for (Question q : searchResults) {
            System.out.println(q.getTitle());
        }

        System.out.println("\nSearch Results for 'python':");
        searchResults = system.searchQuestions("python");
        for (Question q : searchResults) {
            System.out.println(q.getTitle());
        }

        // Demonstrate getting questions by user
        System.out.println("\nBob's Questions:");
        List<Question> bobQuestions = system.getQuestionsByUser(bob);
        for (Question q : bobQuestions) {
            System.out.println(q.getTitle());
        }
    }
}
