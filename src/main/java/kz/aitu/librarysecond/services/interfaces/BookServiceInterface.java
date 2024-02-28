package kz.aitu.librarysecond.services.interfaces;

import kz.aitu.librarysecond.models.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BookServiceInterface {
    List<Book> getAllBooks();
    List<Book> takeBook(int number);
    Book create(Book book);
}

