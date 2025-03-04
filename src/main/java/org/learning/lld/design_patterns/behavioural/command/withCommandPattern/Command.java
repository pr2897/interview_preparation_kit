package org.learning.lld.design_patterns.behavioural.command.withCommandPattern;

interface Command {
    void execute();
}

class BoldCommand implements Command {
    private final TextEditor editor;

    @Override
    public void execute() {
        editor.boldText();
    }

    public BoldCommand(TextEditor editor) {
        this.editor = editor;
    }
}

class Button {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void click() {
        command.execute();
    }
}