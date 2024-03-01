package kz.aitu.librarysecond.services.interfaces;

import kz.aitu.librarysecond.models.Book;
import java.util.List;
public interface BookServiceInterface {
    List<Book> getAllBooks();
    List<Book> getByType(String type);
    List<Book> takeBook(int number);
    Book create(Book book);
    Book buyBook(int number);
}

