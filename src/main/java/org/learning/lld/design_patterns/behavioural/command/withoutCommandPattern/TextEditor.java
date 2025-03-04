package org.learning.lld.design_patterns.behavioural.command.withoutCommandPattern;


public class TextEditor {
    public void boldText() {
        System.out.println("Text has been bolded!");
    }

    public void italisedText() {
        System.out.println("Text has been italicised!");
    }

    public void underlineText() {
        System.out.println("Text is underlined!");
    }
}

class BoldButton  {
    private TextEditor editor;

    public BoldButton(TextEditor editor) {
        this.editor = editor;
    }

    public void click() {
        editor.boldText();
    }
}
