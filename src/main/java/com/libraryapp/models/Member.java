package com.libraryapp.models;

import com.libraryapp.interfaces.Borrower;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a library member who can borrow and return books.
 * Extends User and implements Borrower.
 */

public class Member extends User implements Borrower {

    private final List<Book> borrowedBooks = new ArrayList<>();

    public Member(String name){
        super(name);
    }

    @Override
    public void doAction() {
        //actions WIP
        System.out.println("is browsing the library");
    }

    @Override
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        //Marking book as borrowed
        if (!book.isAvailable()){
            System.out.println(book.getTitle() + " is already borrowed.");
            return;
        }
        borrowedBooks.add(book);
        book.setAvailable(false);
        System.out.println(getName() + " borrowed: " + book.getTitle());
    }

    @Override
    public void returnBook(Book book) {
        if (!borrowedBooks.contains(book)){
            System.out.println(getName() + " has not borrowed " + book.getTitle());
            return;
        }
        borrowedBooks.remove(book);
        book.setAvailable(true);
        System.out.println(getName() + " returned: " + book.getTitle());
        //Marking book as returned
    }

    public List<Book> getBorrowedBooks(){
        return borrowedBooks;
    }

}
