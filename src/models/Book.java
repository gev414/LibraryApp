package models;

/** Represents a single book entity including its metadata such as ISBN, title, author, category
 * and availability.
 */
public class Book {

    private final String isbn;
    private String title;
    private String author;
    private BookCategory category;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
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
