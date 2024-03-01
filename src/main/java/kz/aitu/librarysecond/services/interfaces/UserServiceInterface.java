package kz.aitu.librarysecond.services.interfaces;

import kz.aitu.librarysecond.models.User;

import java.util.ArrayList;
import java.util.List;

public interface UserServiceInterface {
    List<User> getAllUser();
    User create(User user);
    List<User> getBySurname(String surname);
    void topUpBalance(int userId, float amount);
    User getUserByEmail(String email);
    float getUserBalanceById(int userId);
    
}
