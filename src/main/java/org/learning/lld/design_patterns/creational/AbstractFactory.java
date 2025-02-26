package org.learning.lld.design_patterns.creational;


/**
 * <p>The <b>Abstract Factory Pattern</b> is a creational design pattern that provides an
 * interface for creating families of related or dependent objects without specifying their
 * concrete classes.</p>
 */

interface Button {
    void render();
    void onClick();
}

interface CheckBox {
    void render();
    void onCheck();
}

class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("rendering a button on windows...");
    }

    @Override
    public void onClick() {
        System.out.println("windows button clicked!");
    }
}

class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("rendering Button on Mac...");
    }

    @Override
    public void onClick() {
        System.out.println("Mac button clicked!!");
    }
}

class WindowsCheckBox implements CheckBox {
    @Override
    public void render() {
        System.out.println("rendering a checkbox on windows...");
    }

    @Override
    public void onCheck() {
        System.out.println("windows button checked!");
    }
}

class MacCheckBox implements CheckBox {
    @Override
    public void render() {
        System.out.println("rendering checkbox on Mac...");
    }

    @Override
    public void onCheck() {
        System.out.println("Mac checkbox clicked!!");
    }
}

// abstract factory
interface GUIFactory {
    Button createButton();
    CheckBox createCheckBox();
}

class WindowFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new WindowsCheckBox();
    }
}

class MacUiFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new MacCheckBox();
    }
}

class UIFactory {
    public static GUIFactory getFactory(String os) {
        if (os.equals("mac")) return new MacUiFactory();
        else if (os.equals("win")) return new WindowFactory();

        return null;
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        Button button = UIFactory.getFactory("win")
                .createButton();

        button.render();
        button.render();
    }
}
