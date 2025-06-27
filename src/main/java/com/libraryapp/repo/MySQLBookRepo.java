package com.libraryapp.repo;

import com.libraryapp.models.Book;
import com.libraryapp.models.BookCategory;
import com.libraryapp.repo.BookRepo;
import com.libraryapp.repo.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLBookRepo implements BookRepo {

    @Override
    public void save(Book book) {
        String sql = "INSERT INTO books (isbn, title, author, category) VALUES (?, ?, ?, ?)";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getIsbn());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getCategory().name());

            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteByISBN(String isbn) {
        String deleteCopies = "DELETE FROM book_copies WHERE book_id = (SELECT id FROM books WHERE isbn = ?)";
        String deleteBook = "DELETE FROM books WHERE isbn = ?";

        try (Connection conn = MySQLConnection.getConnection()){

            //delete book copies first
            try(PreparedStatement stmt1 = conn.prepareStatement(deleteCopies)){
                stmt1.setString(1, isbn);
                stmt1.executeUpdate();
            }

            //delete the book itself
            try(PreparedStatement stmt2 = conn.prepareStatement(deleteBook)){
                stmt2.setString(1,isbn);
                stmt2.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {

        String sql = "SELECT * FROM books WHERE isbn = ?";
        try (Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setString(1, isbn);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()){
                    Book book = new Book(
                            rs.getString("isbn"),
                            rs.getString("title"),
                            rs.getString("author"),
                            BookCategory.valueOf(rs.getString("category"))
                    );
                    return Optional.of(book);
                }
            }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
        }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ?";

        try (Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, "%"+ title + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                books.add(new Book(
                        rs.getString("isbn"),
                        rs.getString("title"),
                        rs.getString("author"),
                        BookCategory.valueOf(rs.getString("category"))
                ));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = MySQLConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                books.add(new Book(
                        rs.getString("isbn"),
                        rs.getString("title"),
                        rs.getString("author"),
                        BookCategory.valueOf(rs.getString("category"))
                ));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return books;
    }
}

