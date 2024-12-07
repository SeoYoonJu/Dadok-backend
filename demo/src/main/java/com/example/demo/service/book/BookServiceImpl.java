package com.example.demo.service.book;

import com.example.demo.domain.Book;
import com.example.demo.domain.User;
import com.example.demo.dto.book.BookRequestDTO;
import com.example.demo.dto.book.BookResponseDTO;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.book.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public BookResponseDTO writeMyBook(String username, BookRequestDTO request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Book book = request.toEntity(user);

        Book savedBook = bookRepository.save(book);

        return BookResponseDTO.from(savedBook);
    }

    @Override
    @Transactional
    public BookResponseDTO modifyMyBook(Long bookId, String username, BookRequestDTO request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("다이어리 없음"));


        if (!book.getUser().equals(user)) {
            throw new RuntimeException("수정 권한 없음");
        }

        book.setContent(request.getContent());

        Book updatedBook = bookRepository.save(book);


        return BookResponseDTO.from(updatedBook);
    }

    @Override
    public BookResponseDTO showBook(String username, Long bookId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Book book = bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new RuntimeException("해당 책 없음"));

        return BookResponseDTO.from(book);
    }

    @Override
    public Boolean compareUser(Long userId, Long bookId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Book book = bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new RuntimeException("해당 책 없음"));

        if (!book.getUser().equals(user)) {
            return false;
        }
        return true;
    }

    @Override
    public List<BookResponseDTO> showAllBooks() {
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            throw new RuntimeException("책이 존재하지 않습니다");
        }

        return books.stream()
                .map(BookResponseDTO::from)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteMyBook(Long userId, Long bookId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Book book = bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new RuntimeException("해당 책 없음"));

        if (!book.getUser().equals(user)) {
            throw new RuntimeException("삭제 권한 없음");
        }

        bookRepository.delete(book);
    }
}
