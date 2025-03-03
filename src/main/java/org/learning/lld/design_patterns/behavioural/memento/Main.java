package org.learning.lld.design_patterns.behavioural.memento;

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        CareTaker careTaker = new CareTaker();  // history or state management work


        editor.write("hello world");
        careTaker.saveState(editor);

        System.out.println(editor.getContent());

        editor.write("what is happening");
        System.out.println(editor.getContent());
        careTaker.undo(editor);

        System.out.println(editor.getContent());
    }
}
