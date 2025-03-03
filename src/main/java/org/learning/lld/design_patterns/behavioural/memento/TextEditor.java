package org.learning.lld.design_patterns.behavioural.memento;


/*
    a text editor where the user can undo the changes, such as text addition or formatting.
    the editor store snapshots of its state (text content) after each change, enabling the user
    to revert to previous states.
 */
public class TextEditor {
    private String content;

    public void write(String content) {
        this.content = content;
    }

    // save the current state of memento
    public EditorMemento save() {
        return new EditorMemento(content);
    }

    // Restore (memento -> update the current state of the editor.
    public void restore(EditorMemento memento) {
        content = memento.getContent();
    }

    public String getContent() { return content; }
}
