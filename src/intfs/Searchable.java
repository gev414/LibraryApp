package intfs;

import java.awt.print.Book;
import java.util.List;

public interface Searchable {
    List<Book> searchByTitle(String title);
}
