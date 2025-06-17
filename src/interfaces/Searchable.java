package interfaces;

import models.Book;

import java.util.List;

public interface Searchable {
    List<Book> searchByTitle(String title);
}
