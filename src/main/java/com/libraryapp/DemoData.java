package com.libraryapp;

import com.libraryapp.models.Book;
import com.libraryapp.models.BookCategory;
import com.libraryapp.services.InventoryService;

/**
 * Helper class to test data operations and avoid Main.java clutter
 */


public class DemoData {
    public static void populateBooks(InventoryService service) {
        Book[] sampleBooks = {
                new Book("978-1", "golden apple", "Third Brother", BookCategory.FICTION),
                new Book("978-2", "e83 manual", "Some German", BookCategory.TECHNOLOGY),
                new Book("978-3", "Macedonian Nadenitza", "Bozo the Clown", BookCategory.FICTION)
        };

        for (Book b : sampleBooks) {
            if (service.findByIsbn(b.getIsbn()).isEmpty()) {
                service.addBook(b);
                System.out.println("Added book: " + b.getTitle());
            } else {
                System.out.println("Book already exists: " + b.getTitle());
            }
        }
    }
}
