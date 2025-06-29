package com.libraryapp;

import com.libraryapp.models.Book;
import com.libraryapp.models.BookCategory;
import com.libraryapp.models.Member;
import com.libraryapp.models.User;
import com.libraryapp.repo.*;
import com.libraryapp.services.BorrowService;
import com.libraryapp.services.InventoryService;
import com.libraryapp.services.UserService;
import com.libraryapp.repo.MySQLBookRepo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Setup repositories and services
        BookRepo repo = new MySQLBookRepo();
        UserRepo userRepo = new InMemoryUserRepo(); // Swap as needed

        InventoryService inventoryService = new InventoryService(repo);
        UserService userService = new UserService(userRepo);
        BorrowService borrowService = new BorrowService();

        // Register users
        User ivan = new Member("Ivan");
        User penka = new Member("Penka");
        userService.registerUser(ivan);
        userService.registerUser(penka);

        // Seed books
        DemoData.populateBooks(inventoryService);

        // Borrow sample books
        inventoryService.findByIsbn("978-1").ifPresent(book -> borrowService.borrowBook(ivan, book));
        inventoryService.findByIsbn("978-2").ifPresent(book -> borrowService.borrowBook(penka, book));

        // Console output
        System.out.println("Book Titles");
        inventoryService.printAllBookTitles();

        System.out.println("\nAvailable Books");
        inventoryService.listAvailableBooks().forEach(System.out::println);

        System.out.println("\nBooks sorted by title");
        inventoryService.getBooksSortedByTitle().forEach(System.out::println);

        // Test MySQL connection
        try (Connection conn = MySQLConnection.getConnection()) {
            System.out.println("\nMySQL Connection Successful");
        } catch (SQLException e) {
            System.out.println("Connection failed:");
            e.printStackTrace();
        }

        // Output available books to file (UTF-8)
        outputBooksToFile(inventoryService.listAvailableBooks(), "library_output.txt");
    }

    // Helper method for file output
    private static void outputBooksToFile(List<Book> books, String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName), "UTF-8")) {
            for (Book book : books) {
                writer.println(book.getTitle());
            }
            System.out.println("Done printing to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
