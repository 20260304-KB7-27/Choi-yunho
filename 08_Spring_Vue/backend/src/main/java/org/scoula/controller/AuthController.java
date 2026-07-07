package org.scoula.controller;

import org.scoula.security.account.dto.AuthResultDTO;
import org.scoula.security.account.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Swagger 에 Security Login 요청 문서를 만들기위한 컨트롤러
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<AuthResultDTO> login(@RequestBody LoginDTO loginDTO) {

        return ResponseEntity.ok().build();
    }
}
