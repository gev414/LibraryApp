package models;

import models.intfs.BookRepo;
import models.intfs.Searchable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InventoryService implements Searchable {

    private final BookRepo bookRepo;

    public InventoryService(BookRepo bookRepo){
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> searchByTitle(String title){
        return bookRepo.findByTitle(title);
    }

    public void addBook(Book book){
        bookRepo.save(book);
    }

    public void removeBook(String isbn){
        bookRepo.deleteByISBN(isbn);
    }

    public Optional<Book> findBook(String isbn){
        return bookRepo.findBySIBN(isbn);
    }

    public List<Book> listAvailableBooks() {
        return bookRepo.findAll().stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }


}
