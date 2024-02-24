package kz.aitu.librarysecond.services;

import kz.aitu.librarysecond.models.Book;
import kz.aitu.librarysecond.repositories.BookRepositoryInterface;
import kz.aitu.librarysecond.services.interfaces.BookServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService implements BookServiceInterface {

    private final BookRepositoryInterface bookRepository;

    @Autowired
    public BookService(BookRepositoryInterface bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getByNOR(int numberOfReaders) {
        return bookRepository.findByNumberOfReaders(numberOfReaders);
    }
}
