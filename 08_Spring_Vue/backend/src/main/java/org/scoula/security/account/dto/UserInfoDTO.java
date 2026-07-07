package org.scoula.security.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.security.account.domain.MemberVO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private String username;
    private String email;
    private List<String> roles;

    public static UserInfoDTO of(MemberVO memberVO) {
        return new UserInfoDTO(
                memberVO.getUsername(),
                memberVO.getEmail(),
                memberVO.getAuthList().stream()
                        .map(a -> a.getAuth()) // 권한리스트 문자열 변환
                        .toList()
        );
    }
}
