package org.scoula.security.handler;

import lombok.RequiredArgsConstructor;
import org.scoula.security.account.domain.CustomUser;
import org.scoula.security.account.dto.AuthResultDTO;
import org.scoula.security.account.dto.UserInfoDTO;
import org.scoula.security.util.JsonResponse;
import org.scoula.security.util.JwtProcessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 로그인 성공 했을 때 -> JWT 만들어서 return 해주기
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProcessor jwtProcessor;

    // 인증 성송 시 토큰 만들기
    private AuthResultDTO makeAuthResult(CustomUser user) {
        String username = user.getUsername(); // security context에 있는 인증 객체

        String jwt = jwtProcessor.generateToken(username);

        return new AuthResultDTO(jwt, UserInfoDTO.of(user.getMemberVO()));
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 성공 했을 때 응답할 내용
        CustomUser user = (CustomUser) authentication.getPrincipal();

        AuthResultDTO result = makeAuthResult(user);

        // JSON 형태로 응답
        JsonResponse.send(response, result);
    }
}
