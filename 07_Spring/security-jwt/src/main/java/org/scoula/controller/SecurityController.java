package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.security.account.domain.CustomUser;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
@RequestMapping("/security")
@Log4j2
public class SecurityController {

    @GetMapping("/all")
    public ResponseEntity<String> doAll() {
        log.info("do all can access everybody");
        return ResponseEntity.ok("All can access everybody");
    }

    @GetMapping("/member")
    public ResponseEntity<String> doMember(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info("username = " + userDetails.getUsername());
        return ResponseEntity.ok(userDetails.getUsername());
    }

    @GetMapping("/admin")
    public ResponseEntity<MemberVO> doAdmin(@AuthenticationPrincipal CustomUser customUser) {
        MemberVO member = customUser.getMemberVO();
        log.info("username = " + member);
        return ResponseEntity.ok(member);

//    // 모두 접근 가능
//    @GetMapping("/all")
//    public void doAll() {
//        log.info("비회원, 회원, 관리자 모두 접근 가능한 페이지");
//    }
//    // 회원 또는 관리자만 가능
//    @GetMapping("/member")
//    public void doMember() {
//        log.info("회원, 관리자만 접근 가능한 페이지");
//    }
//    // 관리자만 가능
//    @GetMapping("/admin")
//    public void doAdmin() {
//        log.info("관리자만 접근 가능한 페이지");
//    }
//
//    // 우리가 만든 페이지로 이동
//    @GetMapping("/login")
//    public void login() {
//        log.info("로그인 페이지 이동");
//    }
//
//    // 로그아웃 페이지로 이동
//    @GetMapping("/logout")
//    public void logout() {
//        log.info("로그아웃 페이지 이동");
//    }

    }
}