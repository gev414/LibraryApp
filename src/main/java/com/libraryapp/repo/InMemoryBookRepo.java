package com.libraryapp.repo;

import com.libraryapp.models.Book;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In-memory book repository for initial testing. Using HashMap.
 * To be replaced with a database main.java.com.libraryapp.repo later.
 */
public class InMemoryBookRepo implements BookRepo {
    private final Map<String, Book> books = new HashMap<>();

    @Override
    public void save(Book book) {
        books.put(book.getIsbn(),book);
    }

    @Override
    public void deleteByISBN(String isbn) {
        books.remove(isbn);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }

    @Override
    public List<Book> findByTitle(String title) {
        return books.values().stream().filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }
}
