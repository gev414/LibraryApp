package models.users;

import models.Book;
import models.intfs.Borrower;

import java.util.ArrayList;
import java.util.List;

public class Member extends User implements Borrower {

    private List<Book> borrowedBooks = new ArrayList<>();

    public Member(String name){
        super(name);
    }

    @Override
    public void doAction() {
        //actions WIP
    }

    @Override
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        //Marking book as borrowed
    }

    @Override
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        //Marking book as returned
    }
}
