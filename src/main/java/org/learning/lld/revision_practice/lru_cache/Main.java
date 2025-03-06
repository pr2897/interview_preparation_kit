package org.learning.lld.revision_practice.lru_cache;



// put key, value
// get key
// fixed capacity
// thread safe
// constant time


import java.util.HashMap;
import java.util.Map;

class Node<K,V> {
    K key;
    V value;
    Node<K, V> prev, next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K,V>> cache;
    private final Node<K, V> head;
    private final Node<K, V> tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public synchronized V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    public synchronized void put(K key, V value) {
        Node<K,V> node = cache.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
        } else {
            node = new Node<>(key, value);
            cache.put(key, node);
            addToHead(node);

            if (cache.size() > capacity) {
                Node<K,V> removedNode = removeTail();
                cache.remove(removedNode.key);
            }
        }
    }

    private void moveToHead(Node<K,V> node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(Node<K,V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node<K,V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private Node<K,V> removeTail() {
        Node<K,V> node = tail.prev;
        removeNode(node);
        return node;
    }
}

public class Main {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(2);

        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");

        System.out.println(cache.get(1));
        System.out.println(cache.get(2));

        cache.put(4, "four");
    }
}
