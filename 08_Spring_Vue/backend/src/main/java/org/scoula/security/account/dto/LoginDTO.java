package org.scoula.security.account.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private String username;
    private String password;

    // HTTP 요청에서 LoginDTO 객체로 만드는 펙토리 메소드

    public static LoginDTO of(HttpServletRequest request) {
        ObjectMapper om = new ObjectMapper();

        // request에 있는 내용 LoginDTO에 매핑 없으면
        // BadCredentialsException 던짐
        try {
            return om.readValue(request.getInputStream(), LoginDTO.class);
        } catch (IOException e) {
            throw new BadCredentialsException("username 또는 password가 없습니다.");
        }
    }

}
