package kz.aitu.librarysecond.controllers;

import kz.aitu.librarysecond.models.User;
import kz.aitu.librarysecond.services.interfaces.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserServiceInterface service;

    public UserController(UserServiceInterface service) {
        this.service = service;
    }

    @GetMapping("hello")
    public String sayHello(){
        return "Hello World";
    }

    @GetMapping("/")
    public List<User> getAll(){
        return service.getAllUser();
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getById(@PathVariable("user_id") int id){
        User user = service.getById(id);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/reg")
    public ResponseEntity<String> register(@RequestBody User user) {
        User createdUser = service.create(user);
        if (createdUser != null) {
            return new ResponseEntity<>("Registration successful", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/log")
    public ResponseEntity<String> login(@RequestBody User loginUser) {
        String email = loginUser.getEmail();
        String password = loginUser.getPassword();
        User userFromDb = service.getUserByEmail(email);
        if (userFromDb != null && userFromDb.getPassword().equals(password)) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/surname/{user_surname}")
    public List<User> getAllBySurname(@PathVariable("user_surname") String surname){
        return service.getBySurname(surname);
    }

}
