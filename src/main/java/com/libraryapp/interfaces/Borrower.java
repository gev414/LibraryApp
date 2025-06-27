package com.libraryapp.interfaces;

import com.libraryapp.models.Book;

public interface Borrower {
    void borrowBook(Book book);

    void returnBook(Book book);
}
