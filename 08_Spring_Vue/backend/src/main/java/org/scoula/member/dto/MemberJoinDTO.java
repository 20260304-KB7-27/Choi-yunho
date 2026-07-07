package org.scoula.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberJoinDTO {
    private String username;
    private String password;
    private String email;

    private MultipartFile avatar; // -> db 저장안하고 파일시스템으로 관리


    public MemberVO toVO() {
        return MemberVO.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
    }




}
