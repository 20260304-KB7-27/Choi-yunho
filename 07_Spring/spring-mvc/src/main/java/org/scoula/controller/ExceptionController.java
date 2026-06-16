package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/test")
    public String test(@RequestParam int age) {
        if (age < 0) {
            throw new IllegalArgumentException("나이가 0보다 커야합니다...");
        }

        return "ok";
    }

    /*
    * @ExceptionHandler
    * - Spring MVC에서 발생한 예외를 개발자가 직접 처리할 수 있게 Handler 메소드를 제공함
    *
    * Spring이 예외를 처리하는 순서
    * 1. 컨트롤러 내부에서 처리하다 예외가 발생
    * 2. @ExceptionHandler를 Controller 안에서 찾음
    *       없다면 -> @ControllerAdvice가 붙은 클래스를 찾음 -> 내부의 ExceptionHandler 호출
    * */
    @ExceptionHandler(IllegalArgumentException.class)
    public String exceptionHandle(Exception e, Model model) {

        model.addAttribute("errorMessage", e.getMessage());
        return "illegal_error";
    }
}
