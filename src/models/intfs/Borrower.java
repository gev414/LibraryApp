package models.intfs;

import models.Book;

public interface Borrower {
    void borrowBook(Book book);

    void returnBook(Book book);
}
