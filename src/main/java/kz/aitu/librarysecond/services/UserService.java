package kz.aitu.librarysecond.services;

import kz.aitu.librarysecond.models.Book;
import kz.aitu.librarysecond.models.User;
import kz.aitu.librarysecond.repositories.UserRepositoryInterface;
import kz.aitu.librarysecond.services.interfaces.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    public UserRepositoryInterface userRepository;

    public UserService(UserRepositoryInterface repo) {
        this.userRepository = repo;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getBySurname(String surname) {
        return userRepository.findBySurname(surname);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public float getUserBalanceById(int userId) {
        User user  = userRepository.findById(userId).stream().findFirst().orElse(null);
        return user.getBalance();
    }
    @Override
    public void topUpBalance(int userId, float amount) {
        User user  = userRepository.findById(userId).stream().findFirst().orElse(null);
        user.setBalance(user.getBalance()+amount);
        userRepository.save(user);
    }


}
