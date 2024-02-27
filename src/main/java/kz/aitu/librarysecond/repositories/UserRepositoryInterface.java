package kz.aitu.librarysecond.repositories;

import kz.aitu.librarysecond.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepositoryInterface extends JpaRepository<User, Integer>{

    List<User> findBySurname(String surname);
    User findByEmail(String email);
}
