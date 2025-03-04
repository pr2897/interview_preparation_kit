package org.learning.lld.design_patterns.behavioural.template;

public class Main {
    public static void main(String[] args) {
        DataParser parser = new XmlParser();
        parser.parse();

        parser = new JSONParser();
        parser.parse();
    }
}
