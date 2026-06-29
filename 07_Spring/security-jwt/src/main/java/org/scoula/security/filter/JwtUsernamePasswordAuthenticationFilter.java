package org.scoula.security.filter;

import org.scoula.security.account.dto.LoginDTO;
import org.scoula.security.handler.LoginFailureHandler;
import org.scoula.security.handler.LoginSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 로그인 요청을 처리하기 위한 필터
@Component
public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager,
                                                   LoginSuccessHandler successHandler,
                                                   LoginFailureHandler failureHandler) {

        super(authenticationManager);
        setFilterProcessesUrl("/api/auth/login"); // POST 로그인 요청이 왔을 때 동작하는 필터

        setAuthenticationSuccessHandler(successHandler);
        setAuthenticationFailureHandler(failureHandler);
    }

    @Override // 로그인 요청이 왔을 때 처리될 작업을 정의
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // UsernamePasswordAuthenticationTokent 구성
        // LoginDTO
        LoginDTO login = LoginDTO.of(request);

        // 인증 토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        login.getUsername(), login.getPassword()
                );

        // AuthenticationManager에게 인증 요청
        return getAuthenticationManager().authenticate(authenticationToken);
    }
}
