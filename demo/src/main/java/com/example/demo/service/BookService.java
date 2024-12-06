package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.domain.User;
import com.example.demo.dto.book.BookRequestDTO;
import com.example.demo.dto.book.BookResponseDTO;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Transactional
    public BookResponseDTO writeMyBook(String username, BookRequestDTO request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        // BookRequestDTO에서 Book 엔티티로 변환
        Book book = request.toEntity(user);

        // Book 엔티티 저장
        Book savedBook = bookRepository.save(book);

        // Book을 BookResponseDTO로 변환하여 반환
        return BookResponseDTO.from(savedBook);
    }

    @Transactional
    public BookResponseDTO modifyMyBook(Long bookId, String username, BookRequestDTO request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("다이어리 없음"));


        if (!book.getUser().equals(user)) {
            throw new RuntimeException("수정 권한 없음");
        }

        // 내용 업데이트
        book.setContent(request.getContent());

        Book updatedBook = bookRepository.save(book);


        return BookResponseDTO.from(updatedBook);
    }

//    @Transactional
//    @Override
//    public DiaryResponseDTO modifyMyImage(String username, LocalDate diaryDate, MultipartFile diaryImage){
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("유저 없음"));
//
//        Diary diary = diaryRepository.findByDate(diaryDate)
//                .orElseThrow(() -> new RuntimeException("다이어리 없음"));
//
//        if (!diary.getUser().equals(user)) { throw new RuntimeException("수정 권한 없음"); }
//
//        String imgUrl = null;
//        if (diaryImage != null && !diaryImage.isEmpty()) {
//            try {
//                imgUrl = s3Service.uploadFile(diaryImage);
//            } catch (IOException e) {
//                throw new RuntimeException("이미지 업로드 실패", e);
//            }
//        }
//
//        diary.setImg(imgUrl);
//
//        Diary updatedDiary = diaryRepository.save(diary);
//
//        DiaryResponseDTO responseDTO = DiaryConverter.responseDTO(updatedDiary);
//        return responseDTO;
//    }
//
//    @Transactional
//    @Override
//    public DiaryResponseDTO eraseMyImage(String username, LocalDate diaryDate){
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("유저 없음"));
//
//        Diary diary = diaryRepository.findByDate(diaryDate)
//                .orElseThrow(() -> new RuntimeException("다이어리 없음"));
//
//        if (!diary.getUser().equals(user)) { throw new RuntimeException("수정 권한 없음"); }
//
//        String imageUrl = diary.getImg();
//        if (imageUrl==null) { throw new RuntimeException("삭제할 이미지가 없습니다.");}
//
//        diary.setImg(null);
//        Diary erasedImg = diaryRepository.save(diary);
//
//        DiaryResponseDTO responseDTO = DiaryConverter.responseDTO(erasedImg);
//        return responseDTO;
//    }
//

    public BookResponseDTO showMyBook(String username, Long bookId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Book book = bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new RuntimeException("해당 책 없음"));

        if (!book.getUser().equals(user)) {
            throw new RuntimeException("조회 권한 없음");
        }

        // Book 엔티티를 BookResponseDTO로 변환하여 반환
        return BookResponseDTO.from(book);
    }

    public List<BookResponseDTO> showAllBooks() {
        // 모든 책(Book) 엔티티 조회
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            throw new RuntimeException("책이 존재하지 않습니다");
        }

        // 책(Book) 엔티티 리스트를 BookResponseDTO 리스트로 변환하여 반환
        return books.stream()
                .map(BookResponseDTO::from)
                .collect(Collectors.toList());
    }


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
