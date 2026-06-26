package org.scoula.security.service;

import lombok.RequiredArgsConstructor;
import org.scoula.security.account.domain.CustomUser;
import org.scoula.security.account.domain.MemberVO;
import org.scoula.security.account.mapper.UserDetailsMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
// pk값으로 DB에서 사용자정보를 찾아오는 방법을 security에게 알려주는 역할
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDetailsMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO member = mapper.get(username); // DB에서 pk로 조회

        if (member == null) {
            throw new UsernameNotFoundException(username + "은(는) 없는 ID 입니다.");
        }

        return new CustomUser(member); // Authentication 객체에 저장 (Security Context)
    }
}
