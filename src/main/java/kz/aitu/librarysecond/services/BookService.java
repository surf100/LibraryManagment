package kz.aitu.librarysecond.services;

import kz.aitu.librarysecond.models.Book;
import kz.aitu.librarysecond.repositories.BookRepositoryInterface;
import kz.aitu.librarysecond.services.interfaces.BookServiceInterface;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService implements BookServiceInterface {

    private final BookRepositoryInterface bookRepository;
    public BookService(BookRepositoryInterface bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> takeBook(int number) {
        List<Book> books = bookRepository.findByNumber(number);
        if (!books.isEmpty()) {
            Book book = books.get(0);
            if(book.isHas_price()){
                return null;
            }
            book.setReaders(book.getReaders() + 1);
            bookRepository.save(book);
            return books;
        }
        return null;
    }

    @Override
    public List<Book> buyBook(int number) {
        List<Book> books = bookRepository.findByNumber(number);
        if (!books.isEmpty()) {
            Book book = books.get(0);
            if(!book.isHas_price()){
                return null;
            }
            book.setReaders(book.getReaders() + 1);
            bookRepository.save(book);
            return books;
        }
        return null;
    }

}
