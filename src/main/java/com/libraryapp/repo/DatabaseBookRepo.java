package com.libraryapp.repo;

import com.libraryapp.models.Book;

import java.util.List;
import java.util.Optional;

public class DatabaseBookRepo implements BookRepo {

    @Override
    public void save(Book book) {

    }

    @Override
    public void deleteByISBN(String isbn) {

    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.empty();
    }

    @Override
    public List<Book> findByTitle(String title) {
        return List.of();
    }

    @Override
    public List<Book> findAll() {
        return List.of();
    }
}
