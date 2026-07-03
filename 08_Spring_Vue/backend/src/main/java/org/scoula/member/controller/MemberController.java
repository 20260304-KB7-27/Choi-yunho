package org.scoula.member.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.service.MemberService;
import org.scoula.utils.UploadFiles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

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

    // /Users/youknow/Desktop/Projects/KB/fullstack/Choi-yunho/08_Spring_Vue/upload
    // 아바타(프로필) 이미지 전송
    @GetMapping("/{username}/avatar")
    public void getAvatar(@PathVariable String username, HttpServletResponse response) {

        String avatarPath = "/Users/youknow/Desktop/Projects/KB/fullstack/Choi-yunho/08_Spring_Vue/upload/" + username + ".png";
        File file = new File(avatarPath);

        // 해당 경로에 파일(이미지)가 없으면 기본 이미지로 변경
        if (!file.exists()) {
            file = new File("/Users/youknow/Desktop/Projects/KB/fullstack/Choi-yunho/08_Spring_Vue/upload/default.png");
        }
        UploadFiles.downloadImage(response, file);
    }
}
