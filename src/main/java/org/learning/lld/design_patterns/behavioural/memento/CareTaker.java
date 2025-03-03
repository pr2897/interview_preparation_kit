package org.learning.lld.design_patterns.behavioural.memento;

import java.util.Stack;

// Caretaker class: Manage mementos (snapshots of the text editor state.)
public class CareTaker {
    private final Stack<EditorMemento> history = new Stack<>();

    public void saveState(TextEditor editor) {
        history.push(editor.save());
    }

    public void undo(TextEditor editor) {
        if (!history.isEmpty()) {
            editor.restore(history.pop());
        }
    }
}
