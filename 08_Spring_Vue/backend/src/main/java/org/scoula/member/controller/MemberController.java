package org.scoula.member.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    final MemberService service;

    // 회원 중복 검사
    @GetMapping("/checkusername/{username}")
    public ResponseEntity<Boolean> checkUserName(@PathVariable String username) {
        return ResponseEntity.ok().body(service.checkDuplicated(username));
    }

    // 회원가입
    @PostMapping("")
    public ResponseEntity<MemberDTO> join(MemberJoinDTO member) {
        return ResponseEntity.ok(service.join(member));
    }
}
