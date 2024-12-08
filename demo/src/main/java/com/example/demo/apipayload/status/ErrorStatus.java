package com.example.demo.apipayload.status;


import com.example.demo.apipayload.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    // 가장 일반적인 응답
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 유저 관련 에러
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER4001", "유저를 찾을 수 없습니다."),
    USER_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "USER4002",
            "이미 존재하는 유저입니다."),
    USER_NOT_MATCH(HttpStatus.BAD_REQUEST, "USER4003", "유저 정보가 일치하지 않습니다."),
    USER_NOT_AUTHORIZED(HttpStatus.BAD_REQUEST, "USER4004", "유저 권한이 없습니다."),
    USER_NOT_VALID(HttpStatus.BAD_REQUEST, "USER4005", "유저 정보가 유효하지 않습니다."),
    USER_NOT_ACTIVE(HttpStatus.BAD_REQUEST, "USER4006", "유저가 비활성화 되었습니다."),
    USER_NOT_LOGIN(HttpStatus.BAD_REQUEST, "USER4007", "로그인이 필요합니다."),
    USER_NOT_EMAIL(HttpStatus.BAD_REQUEST, "USER4008", "이메일이 없습니다."),
    USER_NOT_PASSWORD(HttpStatus.BAD_REQUEST, "USER4009",
            "비밀번호가 없습니다."),
    USER_NOT_NAME(HttpStatus.BAD_REQUEST, "USER4010", "이름이 없습니다."),
    USER_NOT_PHONE(HttpStatus.BAD_REQUEST, "USER4011", "전화번호가 없습니다."),

    // Token 관련 에러
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST, "REFRESH4001", "리프레시 토큰이 없습니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "REFRESH4002", "리프레시 토큰이 만료되었습니다."),
    ACCESS_TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "ACCESS4001", "액세스 토큰이 만료되었습니다."),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "TOKEN4001", "유효하지 않은 토큰입니다."),

    FILE_UPLOAD_FAILED(HttpStatus.BAD_REQUEST, "FILE4001", "파일 업로드에 실패했습니다."),

    // 커뮤니티 관련 에러
    POST_NOT_EXIST(HttpStatus.BAD_REQUEST, "COMMUNITY3001", "존재하지 않는 게시글 입니다."),
    COMMENT_NOT_EXIST(HttpStatus.BAD_REQUEST, "COMMUNITY3001", "존재하지 않는 댓글 입니다."),
    VOTE_NOT_EXIST(HttpStatus.BAD_REQUEST, "COMMUNITY3002", "존재하지 않는 투표입니다."),
    VOTE_NOT_MULTI(HttpStatus.BAD_REQUEST, "COMMUNITY3003", "복수 선택이 불가능한 투표입니다."),
    VOTE_AFTER_END(HttpStatus.BAD_REQUEST, "COMMUNITY3004", "투표기간이 끝났습니다."),
    VOTE_COUNT_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "COMMUNITY3005", "이미 투표했습니다."),
    VOTE_ITEM_NOT_EXIST(HttpStatus.BAD_REQUEST, "COMMUNITY3006", "존재하지 않는 투표 선택지입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
