package services;

import models.Book;
import repo.BookRepo;
import interfaces.Searchable;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service that encapsulates logic related to inventory. Delegates storage operations to the BookRepo interface.
 *
 */

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

    public Optional<Book> findByIsbn(String isbn){
        return bookRepo.findByIsbn(isbn);
    }



    public List<Book> listAvailableBooks() {
        return bookRepo.findAll().stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    public List<Book> listUnavailableBooks(){
        return bookRepo.findAll().stream()
                .filter(book -> !book.isAvailable()).toList();
    }


    public List<Book> getBooksSortedByTitle(){
        return bookRepo.findAll().stream().sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    public void printAllBookTitles(){
        bookRepo.findAll().stream().map(Book::getTitle).forEach(System.out::println);
    }
}
