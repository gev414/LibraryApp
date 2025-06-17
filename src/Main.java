import models.Book;
import models.BookCategory;
import repo.InMemoryBookRepo;
import services.InventoryService;
import repo.BookRepo;

public class Main {
    public static void main(String[] args) {

        //Dependency inversion
        BookRepo repo = new InMemoryBookRepo();
        //inject dependency into the service
        InventoryService service = new InventoryService(repo);

        service.addBook(new Book("978-1", "golden apple", "Third Brother", BookCategory.FICTION));
        service.addBook(new Book("978-2", "e83 manual", "Some German", BookCategory.TECHNOLOGY));
        service.addBook(new Book("978-3", "Macedonian History", "Bozo the Clown", BookCategory.FICTION));


        System.out.println(" Book Titles");
        service.printAllBookTitles();

        System.out.println("\n Available Books");
        service.listAvailableBooks().forEach(System.out::println);

        System.out.println("\n Books sorted by title");
        service.getBooksSortedByTitle().forEach(System.out::println);

    }
}