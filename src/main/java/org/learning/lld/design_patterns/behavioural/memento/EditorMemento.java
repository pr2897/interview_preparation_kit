package org.learning.lld.design_patterns.behavioural.memento;

// mementor class: stores the internal states of the text editor.
public class EditorMemento {
    private final String content;

    public EditorMemento(String content) {
        this.content = content;
    }

    public String getContent() { return content; }
}
