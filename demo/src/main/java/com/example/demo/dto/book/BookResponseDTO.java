package com.example.demo.dto.book;

import com.example.demo.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookResponseDTO {
    private Long id;
    private String title;
    private String picture;
    private String author;
    private String content;
    private String user; // 예시로 userNickname을 포함할 수 있습니다. 필요에 따라 추가

    // Book 엔티티를 BookResponseDTO로 변환하는 정적 메서드
    public static BookResponseDTO from(Book book) {
        return new BookResponseDTO(
                book.getBookId(),
                book.getTitle(),
                book.getPicture(),
                book.getAuthor(),
                book.getContent(),
                book.getUser().getUsername() // User의 닉네임을 응답에 포함할 경우
        );
    }
}
