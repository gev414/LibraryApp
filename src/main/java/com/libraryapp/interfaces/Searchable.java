package com.libraryapp.interfaces;

import com.libraryapp.models.Book;

import java.util.List;

public interface Searchable {
    List<Book> searchByTitle(String title);
}
