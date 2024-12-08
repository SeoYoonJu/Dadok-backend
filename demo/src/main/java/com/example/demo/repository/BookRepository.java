package com.example.demo.repository;

import com.example.demo.domain.Book;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByBookId(Long bookId);

    Optional<Book> findById(Long id);
    Optional<Book> findByUser(User user);
}
