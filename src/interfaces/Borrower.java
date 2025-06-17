package interfaces;

import models.Book;

public interface Borrower {
    void borrowBook(Book book);

    void returnBook(Book book);
}
