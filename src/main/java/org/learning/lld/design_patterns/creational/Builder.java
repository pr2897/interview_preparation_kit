package org.learning.lld.design_patterns.creational;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * <pre> builder design pattern is typically used to construct complex objects step by step. </pre>
 * <hr/>
 * <b> How to implement: </b>
 *  <pre>
 *      1. create a static inner builder class with all the field.
 *      2. and then add method with set/with pattern to set the value and return this.
 *      3. create a build which return the object constructor with builder as argument.
 *  </pre>
 */

@ToString
@AllArgsConstructor
class Home {
    private String name;
    private int window;
    private int door;

    public Home(Builder builder) {
        this(builder.name, builder.window,builder.door);
    }

    public static class Builder {
        private String name;
        private int window;
        private int door;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setWindow(int window) {
            this.window = window;
            return this;
        }

        public Builder setDoor(int door) {
            this.door = door;
            return this;
        }

        public Home build() {
            return new Home(this);
        }
    }


}

public class Builder {
    public static void main(String[] args) {
        Home home = new Home.Builder()
                .setName("Rachna's villa")
                .setWindow(400)
                .setDoor(100)
                .build();

        System.out.println(home);
    }
}
