package kz.aitu.librarysecond.services;

import kz.aitu.librarysecond.models.User;
import kz.aitu.librarysecond.repositories.UserRepositoryInterface;
import kz.aitu.librarysecond.services.interfaces.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    public UserRepositoryInterface repo;

    public UserService(UserRepositoryInterface repo) {
        this.repo = repo;
    }

    @Override
    public List<User> getAllUser() {
        return repo.findAll();
    }

    @Override
    public User create(User user) {
        return repo.save(user);
    }

    @Override
    public List<User> getBySurname(String surname) {
        return repo.findBySurname(surname);
    }

    @Override
    public User getUserByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public float getUserBalanceById(int userId) {
    User user  = userRepository.findById(userId).stream().findFirst().orElse(null);
    return user.getBalance();
}
    @Override
    public void topUpBalance(int userId, float amount) {
        Optional<User> userList = repo.findById(userId);
        if (!userList.isEmpty()) {
            User user = userList.get();
            float currentBalance = user.getBalance();
            user.setBalance(currentBalance + amount);
            repo.save(user);
}
        }
    }


}
