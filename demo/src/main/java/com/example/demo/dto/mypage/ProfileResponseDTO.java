package com.example.demo.dto.mypage;


import com.example.demo.domain.Book;
import com.example.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileResponseDTO {

    private String username;
    private Long goal;
    private String favorite;

    public static ProfileResponseDTO from(User user) {
        return new ProfileResponseDTO(
                user.getUsername(),
                user.getGoal(),
                user.getFavorite()
        );
    }
}
