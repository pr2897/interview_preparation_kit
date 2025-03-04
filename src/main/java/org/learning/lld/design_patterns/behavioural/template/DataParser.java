package org.learning.lld.design_patterns.behavioural.template;

public abstract class DataParser {
    public final void parse() {
        openFile();
        parseData();
        closeFile();
    }

    public abstract void parseData();

    private void openFile() {
        System.out.println("opening file");
    }

    private void closeFile() {
        System.out.println("closing file");
    }
}
