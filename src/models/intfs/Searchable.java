package models.intfs;

import models.Book;

import java.util.List;

public interface Searchable {
    List<Book> searchByTitle(String title);
}
