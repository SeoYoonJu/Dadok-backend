package com.example.demo.dto.book;

import com.example.demo.domain.Book;
import com.example.demo.domain.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestDTO {

        private String title;

        private String picture;

        private String author;

        private String content;

public Book toEntity(User user) {
        return Book.builder()
                .user(user)
                .title(this.title)
                .picture(this.picture)
                .author(this.author)
                .content(this.content)
                .user(user)
                .build();
}

}
