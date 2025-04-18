package kz.aitu.librarysecond.controllers;

import kz.aitu.librarysecond.models.Book;
import kz.aitu.librarysecond.models.User;
import kz.aitu.librarysecond.repositories.BookRepositoryInterface;
import kz.aitu.librarysecond.repositories.UserRepositoryInterface;
import kz.aitu.librarysecond.services.interfaces.BookServiceInterface;
import kz.aitu.librarysecond.services.interfaces.UserServiceInterface;
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
    private final BookRepositoryInterface bookRepository;
    private final UserServiceInterface userService;
    private final UserRepositoryInterface userRepository;
    public BookController(BookServiceInterface service, BookRepositoryInterface bookRepository, UserServiceInterface userService, UserRepositoryInterface userRepository) {

        this.service = service;
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping("/")
    public List<Book> getAll() {
        return service.getAllBooks();
    }

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

    /*
    POST
   {
    "name": "pohod",
    "year": 1889,
    "type": "history",
    "readers": 0,
    "has_price": true,
    "price": 9999
    }
     */
    @PostMapping("/add-book/")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        Book createdBook = service.create(book);
        if (createdBook == null){
            return new ResponseEntity<>("Ooops,unknown error occured",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
        }
    }
    @PostMapping("/buy/{book_number}")
    public ResponseEntity<?> buyBook(@PathVariable("book_number") int number, @RequestBody User enterUser) {
        String email = enterUser.getEmail();
        String password = enterUser.getPassword();

        User userDb = userService.getUserByEmail(email);

        if (userDb != null && userDb.getPassword().equals(password)) {
            Book book = service.buyBook(number);
            if (book != null) {
                if (book.isHas_price()) {
                    User user = userRepository.findByEmail(email);
                    double bookPrice = book.getPrice();
                    if (user.getBalance() >= bookPrice) {
                        user.setBalance((float) (user.getBalance() - bookPrice));
                        userRepository.save(user);
                        return new ResponseEntity<>(book, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Insufficient balance", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return new ResponseEntity<>("Book is free", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/type/{book_type}")
    public List<Book> getByType(@PathVariable("book_type") String type){
        return service.getByType(type);
    }

}
