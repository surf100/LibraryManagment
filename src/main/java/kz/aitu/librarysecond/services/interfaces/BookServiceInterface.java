package kz.aitu.librarysecond.services.interfaces;

import kz.aitu.librarysecond.models.Book;
import java.util.List;
import java.util.Optional;

public interface BookServiceInterface {
    List<Book> getAllBooks();
    List<Book> takeBook(int number);
    List<Book> buyBook(int number);
}

