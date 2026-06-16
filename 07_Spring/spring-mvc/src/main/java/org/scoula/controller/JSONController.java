package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.dto.LoginRequestDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/json")
@Log4j2
public class JSONController {

    /*
    * @ResponseBody
    * - 반환 값을 view 이름으로 해석하지 않고, HTTP Response Body에 직접 작성
    * - 객체로 반환시 HttpMessageConverter (Jackson 라이브러리 사용)가
    *   객체를 json 형태로 직렬화 한다.
    * */

    @ResponseBody
    @GetMapping("/ex01")
    public LoginRequestDTO example() {

        LoginRequestDTO dto = new LoginRequestDTO("user", "1234");
        // 비즈니스 로직
        return dto;
    }

    /*
    * @RequestBody
    * - 클라이언트가 보낸 json 형식의 데이터를 자바 객체로 변환 (역직렬화)*/
    @ResponseBody
    @PostMapping("/ex02")
    public LoginRequestDTO example2(@RequestBody LoginRequestDTO dto) {

        log.info("받은 데이터 =========== {}", dto.getId());
        log.info("받은 데이터 =========== {}", dto.getPassword());

        return dto;
    }

    /*
    * ResponseEntity
    * - 응답 본문, 상태 코드, 헤더를 직접 작성 가능한 방식*/
    @ResponseBody
    @GetMapping("/ex03")
    public ResponseEntity<String> example3() {

        String body = "BODYBODYBODYBODYBODYBODY";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charse=UTF-8");

//        return new ResponseEntity<>(body, headers, HttpStatus.ACCEPTED);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .headers(headers)
                .body(body);
    }
}

