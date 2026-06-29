package org.scoula.security.account.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class AuthVO implements GrantedAuthority {

    private String username;
    private String auth;

    // Spring Security가 권한 확인 시 getAuthority() 메서드 호출
    @Override
    public String getAuthority() {
        return auth;
    }
}
