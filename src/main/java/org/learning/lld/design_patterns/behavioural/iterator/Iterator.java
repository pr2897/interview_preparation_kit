package org.learning.lld.design_patterns.behavioural.iterator;

public interface Iterator<T> {
    boolean hasNext();
    T next();
}
