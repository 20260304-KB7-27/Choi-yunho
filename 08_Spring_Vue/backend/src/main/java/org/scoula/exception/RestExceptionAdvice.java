package org.scoula.exception;


import lombok.extern.log4j.Log4j2;
import org.scoula.common.dto.ApiResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;

// assignableTypes : 에외 처리 대상 컨트롤러를 클래스 단위로 지정
@RestControllerAdvice
@Log4j2
@Order(2)
public class RestExceptionAdvice {

    // 404 - 리소스 찾을 수 없음
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse<Void>> handle404(NoSuchElementException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // 404
                .body(ApiResponse.fail(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> except(Exception ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500 Error -> 개발자잘못
                .body(ApiResponse.fail(ex.getMessage()));
    }
}