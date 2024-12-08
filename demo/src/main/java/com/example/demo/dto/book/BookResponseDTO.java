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
    private String user;


    public static BookResponseDTO from(Book book) {
        return new BookResponseDTO(
                book.getBookId(),
                book.getTitle(),
                book.getPicture(),
                book.getAuthor(),
                book.getContent(),
                book.getUser().getUsername()
        );
    }
}
