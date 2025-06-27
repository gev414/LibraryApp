package com.libraryapp.models;

/** Encapsulates book data. Represents a single book entity including its metadata such as ISBN, title, author, category
 * and availability.
 */
public class Book {

    private final String isbn;
    private final String title;
    private final String author;
    private final BookCategory category;
    private boolean isAvailable = true;

    public Book(String isbn, String title, String author, BookCategory category){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }

    public BookCategory getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s by %s [%s]",
                isbn, title, author, category);
    }
}
