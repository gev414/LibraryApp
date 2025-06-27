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


        BookRepo repo = new MySQLBookRepo();

//        Disabling in-memory book repo for db testing;
//        BookRepo repo = new InMemoryBookRepo();
        UserRepo userRepo = new InMemoryUserRepo();

        //inject dependency into the service

        InventoryService service = new InventoryService(repo);
        UserService userService = new UserService(userRepo);
        BorrowService borrowService = new BorrowService();


        User ivan = new Member("Ivan");
        User penka = new Member("Penka");
        userService.registerUser(ivan);
        userService.registerUser(penka);

//        Disabled for database repo test
//        service.addBook(new Book("978-1", "golden apple", "Third Brother", BookCategory.FICTION));
//        service.addBook(new Book("978-2", "e83 manual", "Some German", BookCategory.TECHNOLOGY));
//        service.addBook(new Book("978-3", "Macedonian Nadenitza", "Bozo the Clown", BookCategory.FICTION));


        Book book1 = service.findByIsbn("978-1").orElseThrow();
        borrowService.borrowBook(ivan, book1);

        Book book2 = service.findByIsbn("978-2").orElseThrow();
        borrowService.borrowBook(penka, book2);


        System.out.println(" Book Titles");
        service.printAllBookTitles();

        System.out.println("\n Available Books");
        service.listAvailableBooks().forEach(System.out::println);

        System.out.println("\n Books sorted by title");
        service.getBooksSortedByTitle().forEach(System.out::println);

        try (Connection conn = MySQLConnection.getConnection()){
            System.out.println("\nMySQL Connection Successful");;
        } catch (SQLException e){
            System.out.println("Connection failed:");
            e.printStackTrace();
        }

        /**
         * Testing output to file as console refuses to print Cyrillic characters
         * after the addition of Gradle. Output is successfully written in Cyrillic
         * outside the IDE console
         */
        List<Book> books = service.listAvailableBooks();
        try (PrintWriter writer = new PrintWriter(new File("library_output.txt"), "UTF-8")){
            for (Book book : books){
                writer.println(book.getTitle());
            }
            System.out.println("Done printing to file");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}