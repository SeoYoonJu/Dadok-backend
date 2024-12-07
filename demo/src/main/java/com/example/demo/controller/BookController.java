package com.example.demo.controller;

import com.example.demo.apipayload.ApiResponse;
import com.example.demo.domain.User;
import com.example.demo.dto.book.BookRequestDTO;
import com.example.demo.dto.book.BookResponseDTO;
import com.example.demo.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        System.out.println(authentication);
        return null;  // 인증되지 않은 경우 null 반환
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<BookResponseDTO>> writeBook(@RequestBody BookRequestDTO request) {
        String username = getCurrentUsername();
        BookResponseDTO written = bookService.writeMyBook(username, request);

        return ResponseEntity.ok(ApiResponse.onSuccess(written));
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<ApiResponse<BookResponseDTO>> modifyBook(@PathVariable Long bookId,@RequestBody BookRequestDTO request){
        String username = getCurrentUsername();
        BookResponseDTO modified = bookService.modifyMyBook(bookId, username, request);

        return ResponseEntity.ok(ApiResponse.onSuccess(modified));
    }


    @GetMapping("/{bookId}")
    public ResponseEntity<ApiResponse<BookResponseDTO>> showBook(@PathVariable Long bookId){
        String username = getCurrentUsername();
        BookResponseDTO eachDiary = bookService.showBook(username, bookId);

        return ResponseEntity.ok(ApiResponse.onSuccess(eachDiary));
    }

    @GetMapping("/who/{bookId}")
    public ResponseEntity<Boolean> giveMyInfo(@AuthenticationPrincipal User user, @PathVariable Long bookId){
        Boolean myId = bookService.compareUser(user.getId(), bookId);
        System.out.println(myId);
        return ResponseEntity.ok(myId);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<BookResponseDTO>>> showAllBook(){
        List<BookResponseDTO> allDiary = bookService.showAllBooks();

        return ResponseEntity.ok(ApiResponse.onSuccess(allDiary));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse<String>> deleteBook(@AuthenticationPrincipal User user, @PathVariable Long bookId){
        bookService.deleteMyBook(user.getId(),bookId);

        return ResponseEntity.ok(ApiResponse.onSuccess("삭제완료"));
    }

}
