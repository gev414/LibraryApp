package models.intfs;

import models.Book;

import java.util.List;
import java.util.Optional;

/**
 * Interface to define data-accessing functions related to books.
 */

public interface BookRepo {

    void save(Book book);
    void deleteByISBN(String isbn);
    Optional<Book> findBySIBN(String isbn);
    List<Book> findByTitle(String title);
    List<Book> findAll();
}
