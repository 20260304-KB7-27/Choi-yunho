package org.scoula.exception;


import lombok.extern.log4j.Log4j2;
import org.scoula.board.controller.ApiResponseController;
import org.scoula.board.controller.BoardController;
import org.scoula.common.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

// assignableTypes : 에외 처리 대상 컨트롤러를 클래스 단위로 지정
@RestControllerAdvice(assignableTypes = ApiResponseController.class)
@Log4j2
public class RestExceptionAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse<Void>> handle404(NoHandlerFoundException ex) {

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