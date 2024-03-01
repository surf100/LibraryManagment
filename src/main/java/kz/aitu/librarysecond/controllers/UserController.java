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
    @GetMapping("/")
    public List<User> getAll(){
        return service.getAllUser();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        User isInDb =  service.getUserByEmail(user.getEmail());
        if (isInDb == null){
            User newUser = service.create(user);
            if(newUser==null){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else{
                return new ResponseEntity<>("Registration successful",HttpStatus.CREATED);
            }
        }
        else{
            return new ResponseEntity<>("Registration failed", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/login")
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
    @GetMapping("/balance/{user_id}")
    public ResponseEntity<?> getUserBalanceById(@PathVariable("user_id") int userId) {
        float balance = service.getUserBalanceById(userId);
        if (balance >= 0) {
            return new ResponseEntity<>(balance,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Oops unknown error occured",HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/up/{user_id}/{amount}")
    public ResponseEntity<String> topUpBalance(@PathVariable("user_id") int userId, @PathVariable("amount") float amount) {
        service.topUpBalance(userId, amount);
        return new ResponseEntity<>("Balance topped up successfully", HttpStatus.OK);
    }

}
