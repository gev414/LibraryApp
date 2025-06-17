import models.Book;
import models.BookCategory;
import models.Member;
import models.User;
import repo.InMemoryBookRepo;
import repo.InMemoryUserRepo;
import repo.UserRepo;
import services.BorrowService;
import services.InventoryService;
import repo.BookRepo;
import services.UserService;

public class Main {
    public static void main(String[] args) {

        //Dependency inversion
        BookRepo repo = new InMemoryBookRepo();
        UserRepo userRepo = new InMemoryUserRepo();

        //inject dependency into the service
        InventoryService service = new InventoryService(repo);
        UserService userService = new UserService(userRepo);
        BorrowService borrowService = new BorrowService();


        User ivan = new Member("Ivan");
        User penka = new Member("Penka");
        userService.registerUser(ivan);
        userService.registerUser(penka);

        service.addBook(new Book("978-1", "golden apple", "Third Brother", BookCategory.FICTION));
        service.addBook(new Book("978-2", "e83 manual", "Some German", BookCategory.TECHNOLOGY));
        service.addBook(new Book("978-3", "Macedonian Heritage Across The Milky Way", "Bozo the Clown", BookCategory.FICTION));


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



    }
}