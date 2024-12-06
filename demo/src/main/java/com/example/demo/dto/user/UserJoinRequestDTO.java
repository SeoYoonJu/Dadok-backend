package com.example.demo.dto.user;

import com.example.demo.domain.User;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserJoinRequestDTO {
    private String userId;
    private String userPassword; // 비밀번호
    private String username;
    private Long goal;
    private String favorite;

public User toEntity(PasswordEncoder passwordEncoder) {
    return User.builder()
            .userId(this.userId)
            .userPassword(passwordEncoder.encode(this.userPassword))  // 암호화된 비밀번호 저장
            .username(this.username)
            .goal(this.goal)
            .favorite(this.favorite)
            .build();
}

}
