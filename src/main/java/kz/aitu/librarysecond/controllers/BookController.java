package kz.aitu.librarysecond.controllers;

import kz.aitu.librarysecond.models.Book;
import kz.aitu.librarysecond.models.User;
import kz.aitu.librarysecond.services.interfaces.BookServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookServiceInterface service;

    public BookController(BookServiceInterface service) {
        this.service = service;
    }

    @GetMapping("hello")
    public String sayHello(){
        return "Hello World";
    }

    @GetMapping("/")
    public List<Book> getAll(){
        return service.getAllBooks();
    }
    @GetMapping("/top/{number_of_readers}")
    public ResponseEntity<Book> getBooksByNumberOfReaders(@PathVariable("number_of_readers") int number_of_readers){
        Book book = (Book) service.getByNOR(number_of_readers);
        if(book==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
