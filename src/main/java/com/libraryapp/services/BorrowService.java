package com.libraryapp.services;

import com.libraryapp.models.Book;
import com.libraryapp.models.User;

import java.util.*;

public class BorrowService {
    private final Map<Integer, List<Book>> borrowedBooks = new HashMap<>();
    // checks book availability -> registers the book as borrowed by user -> sets book availability to false -> return result
    public boolean borrowBook(User user, Book book){
        if(!book.isAvailable()){
            System.out.println("Book is not available: " + book.getTitle());
            return false;
        }

        borrowedBooks.computeIfAbsent(user.getId(), k -> new ArrayList<>()).add(book);

        book.setAvailable(false);
        System.out.println(user.getName() + " borrowed: " + book);
        return true;
    }
    // return the list associated with the user key, if no is present - returns an empty list to avoid null pointers.
    public List<Book> getBorrowedBooks(User user){
        return borrowedBooks.getOrDefault(user.getId(), Collections.emptyList());
    }
    // get user books, if book exists - remove it and mark book as available and return true.
    public boolean returnBook(User user, Book book){
        List<Book> books = borrowedBooks.get(user.getId());

        if (books != null && books.remove(book)){
            book.setAvailable(true);
            System.out.println(user.getName() + " returned: " + book);
            return true;
        } else {
            System.out.println("Book not found in user's borrowed list");
            return false;
        }

    }

}
