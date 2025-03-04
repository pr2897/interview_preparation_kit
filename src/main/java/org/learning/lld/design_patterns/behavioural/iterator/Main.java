package org.learning.lld.design_patterns.behavioural.iterator;

public class Main {
    public static void main(String[] args) {
        BookCollections books = new BookCollections();
        books.addBook(new Book("book1", 2));
        books.addBook(new Book("book1", 2));
        books.addBook(new Book("book1", 2));
        books.addBook(new Book("book1", 2));

        Iterator<Book> iterator = books.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
