package org.learning.lld.design_patterns.behavioural.iterator;


import java.util.ArrayList;
import java.util.List;

record Book(String title, double price) {
}

public class BookCollections {
    private List<Book> books = new ArrayList<>();

    void addBook(Book b) { this.books.add(b); }

    void removeBook(Book b) { this.books.remove(b); }

    public Iterator<Book> createIterator() {
        return new BookIterator(books);
    }

    // Another nest class
    private class BookIterator implements Iterator<Book> {
        private List<Book> books;
        private int position = 0;

        public BookIterator(List<Book> books) {
            this.books = books;
        }

        @Override
        public boolean hasNext() {
            return position < books.size();
        }

        @Override
        public Book next() {
            return books.get(position++);
        }
    }
}
