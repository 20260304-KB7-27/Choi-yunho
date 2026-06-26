package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.security.account.domain.CustomUser;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Log4j2
@RequestMapping("/member")
public class TestController {

    /*
    * 인증 객체를 꺼내는 방법
    * 1. principal : username만 꺼낼 수 있음
    * 2. Authentication : 권한, 인증상태 Security 정보가 필요할 때 (UserDetail로 형변환필요)
    * 3. @AuthenicationPrincipal : CustomUser에 바로 접근 가능*/

    // Principal
    @GetMapping("/1")
    public String print1(Principal principal) {
        return principal.getName();
    }

    // Authentication
    @GetMapping("/2")
    public String print2(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    // @AuthenticationPrincipal
    @GetMapping("/3")
    public MemberVO print3(@AuthenticationPrincipal CustomUser customUser) {
        MemberVO memberVO = customUser.getMemberVO();
        return memberVO;
    }

    // @AuthenticationPrincipal
    // Post 요청으로 오고 게시글 생성이다
    @PostMapping("/board")
    public MemberVO print4(@AuthenticationPrincipal CustomUser customUser) {
        MemberVO memberVO = customUser.getMemberVO();
        return memberVO;
    }
}
