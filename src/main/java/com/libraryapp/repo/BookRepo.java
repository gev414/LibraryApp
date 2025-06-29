package com.libraryapp.repo;

import com.libraryapp.models.Book;

import java.util.List;
import java.util.Optional;

/** Decouples business logic from data implementation (DIP)
 * Interface to define data-accessing functions related to books.
 */

public interface BookRepo {

    void save(Book book);
    void deleteByISBN(String isbn);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByTitle(String title);
    List<Book> findAll();
}
