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



}
