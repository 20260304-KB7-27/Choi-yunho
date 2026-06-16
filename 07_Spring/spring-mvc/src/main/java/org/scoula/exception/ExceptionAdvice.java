package org.scoula.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/*
* 전역처리를 하기 위한 어노테이션
* */
@ControllerAdvice
@Log4j2
public class ExceptionAdvice {

    // 모든 종류를 처리하는 핸들러 메소드
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error_page";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String exceptionHandle2(NoHandlerFoundException e, Model model, HttpServletRequest request) {

        model.addAttribute("uri", request.getRequestURI());

        return "custom404";
    }
}
