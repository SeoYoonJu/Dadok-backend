package com.example.demo.service.book;

import com.example.demo.dto.book.BookRequestDTO;
import com.example.demo.dto.book.BookResponseDTO;

import java.util.List;

public interface BookService {
    BookResponseDTO writeMyBook(String username, BookRequestDTO request);
    BookResponseDTO modifyMyBook(Long bookId, String username, BookRequestDTO request);
    BookResponseDTO showBook(String username, Long bookId);
    Boolean compareUser(Long userId, Long bookId);
    List<BookResponseDTO> showAllBooks();
    void deleteMyBook(Long userId, Long bookId);




}
