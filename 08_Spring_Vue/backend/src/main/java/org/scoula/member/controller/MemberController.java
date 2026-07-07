package org.scoula.member.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.member.dto.ChangePasswordDTO;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.dto.MemberUpdateDTO;
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

    // 회원명 중복검사
    @GetMapping("/checkusername/{username}")
    public ResponseEntity<Boolean> checkUserName(@PathVariable String username) {

        return ResponseEntity.ok().body(service.checkDuplicate(username));
    }

    // 회원가입
    @PostMapping("")
    public ResponseEntity<MemberDTO> join(MemberJoinDTO member) {

        return ResponseEntity.ok(service.join(member));
    }


    // "c:/upload/avatar/username.png"
    // 아바타(프로필) 이미지 전송
    @GetMapping("/{username}/avatar")
    public void getAvatar(@PathVariable String username, HttpServletResponse response) {

        String avatarPath = UploadFiles.BASE_DIR + "/avatar/" + username + ".png";
        File file = new File(avatarPath);

        // 해당 경로에 파일(이미지)가 없으면 기본 이미지로 변경
        if(!file.exists()) {
            file = new File(UploadFiles.BASE_DIR + "/avatar/default.png");
        }
        UploadFiles.downloadImage(response, file);
    }

    // 회원정보 수정
    @PutMapping("/{username}")
    public ResponseEntity<MemberDTO> changeProfile(MemberUpdateDTO member) {
        return ResponseEntity.ok(service.update(member));
    }

    // 비밀번호 수정
    @PutMapping("/{username}/changepassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        service.changePassword(changePasswordDTO);
        return ResponseEntity.ok().build();
    }

}
