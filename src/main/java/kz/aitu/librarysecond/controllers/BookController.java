package kz.aitu.librarysecond.controllers;

import kz.aitu.librarysecond.models.Book;
import kz.aitu.librarysecond.models.User;
import kz.aitu.librarysecond.services.interfaces.BookServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookServiceInterface service;

    public BookController(BookServiceInterface service) {
        this.service = service;
    }

    @GetMapping("hello")
    public String sayHello() {
        return "Hello World";
    }
// localhost:8081/  take all getAllBooks
    @GetMapping("/")
    public List<Book> getAll() {
        return service.getAllBooks();
    }
// localhost:8081/top shows rating books
    @GetMapping("/top")
    public List<Book> rating() {
        List<Book> books = service.getAllBooks();
        List<Book> topBooks = new ArrayList<>();
        if (!books.isEmpty()) {
            Collections.sort(books, (b1, b2) -> Double.compare(b2.getReaders(), b1.getReaders()));
            for (int i = 0; i < Math.min(3, books.size()); i++) {
                topBooks.add(books.get(i));
            }
        }

        return topBooks;
    }
    @GetMapping("/take/{book_number}")
    public List<Book> takeBook(@PathVariable("book_number") int number){
        return service.takeBook(number);
    }
    @PostMapping("/add-book/")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book createdBook = service.create(book);
        if(createdBook == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

}
