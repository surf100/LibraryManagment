package kz.aitu.librarysecond.services.interfaces;

import kz.aitu.librarysecond.models.Book;
import java.util.List;

public interface BookServiceInterface {
    List<Book> getAllBooks();
    List<Book> getByNOR(int numberOfReaders);
}

