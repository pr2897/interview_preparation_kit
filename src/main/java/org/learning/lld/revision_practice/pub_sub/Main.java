package org.learning.lld.revision_practice.pub_sub;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@AllArgsConstructor
@Getter
class Message {
    private final String content;
}

class Topic {
    @Getter
    private final String name;
    private final Set<Subscriber> subscribers = new CopyOnWriteArraySet<>();

    public Topic(String name) {
        this.name = name;
    }

    public void addSubscriber(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    public void publish(Message message) {
        for (Subscriber subscriber: subscribers) {
            subscriber.onMessage(message);
        }
    }
}

interface Subscriber {
    void onMessage(Message message);
}

class Publisher {

}

class PubSubSystem {

}

public class Main {
    public static void main(String[] args) {

    }
}
