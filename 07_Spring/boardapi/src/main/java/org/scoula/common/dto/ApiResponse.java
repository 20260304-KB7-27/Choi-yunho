package org.scoula.common.dto;

import com.google.protobuf.Api;
import lombok.Builder;
import lombok.Getter;

/*
* 공통 응답 래퍼 패턴
* - RestAPI는 엔드 포인트마다 성공/예외에 대한 응답 구조가 달라짐
* - 따라서 FrontEnd에서 처리하기가 어려움 이를 동일한 구조로 감싸서(래핑)
* - 클라이언트가 쉽게 판단
*
* */
@Getter
@Builder
public class ApiResponse<T> {

    private boolean success; // 성공여부
    private String message; // 성공시 "success" 실패시 에러 메세지
    private T data; // 실제 응답 데이터

    // 성공
    public static <T> ApiResponse<T> ok(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message("success")
                .data(data)
                .build();
    }

    // 실패
    public static <T> ApiResponse<T> fail(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .build();
    }
}
