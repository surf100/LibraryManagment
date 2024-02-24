package kz.aitu.librarysecond.repositories;

import kz.aitu.librarysecond.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepositoryInterface extends JpaRepository<Book, Integer> {
    List<Book> findByNumberOfReaders(int numberOfReaders);
}

