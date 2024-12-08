package com.example.demo.dto.user;

import com.example.demo.domain.User;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserJoinRequestDTO {
    private String userId;
    private String userPassword;
    private String username;
    private Long goal;
    private String favorite;

public User toEntity(PasswordEncoder passwordEncoder) {
    return User.builder()
            .userId(this.userId)
            .userPassword(passwordEncoder.encode(this.userPassword))
            .username(this.username)
            .goal(this.goal)
            .favorite(this.favorite)
            .build();
    }
}
