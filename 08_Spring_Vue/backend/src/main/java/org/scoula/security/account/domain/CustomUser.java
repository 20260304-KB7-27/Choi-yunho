package org.scoula.security.account.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
// Spring Seucirty에서 DB의 회원정보를 보관하는 인증 객체
// 인증 성공하면 Authentication 객체의 principal로 저장됨 (SecurityContext)
public class CustomUser extends User {

    private MemberVO memberVO; // 추가정보

    // 개별적으로 직접 생성할때 사용
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(MemberVO member) {
        super(member.getUsername(), member.getPassword(), member.getAuthList());
        this.memberVO = member;
    }
}
