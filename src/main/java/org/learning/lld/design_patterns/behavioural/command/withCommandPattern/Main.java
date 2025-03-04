package org.learning.lld.design_patterns.behavioural.command.withCommandPattern;

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        Button button = new Button();
        button.setCommand(new BoldCommand(editor));

        button.click();
    }
}
