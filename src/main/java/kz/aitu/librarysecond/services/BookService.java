package kz.aitu.librarysecond.services;

import kz.aitu.librarysecond.models.Book;
import kz.aitu.librarysecond.models.User;
import kz.aitu.librarysecond.repositories.BookRepositoryInterface;
import kz.aitu.librarysecond.repositories.UserRepositoryInterface;
import kz.aitu.librarysecond.services.interfaces.BookServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book buyBook(int number) {
        Book book  = bookRepository.findByNumber(number).stream().findFirst().orElse(null);
        if(book!=null && book.isHas_price()) {
            book.setReaders(book.getReaders() + 1);
            bookRepository.save(book);
            return book;
        }
        return null;
    }
    @Override
    public List<Book> getByType(String type) {
        return bookRepository.findByType(type);
    }

}


