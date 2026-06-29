package org.scoula.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@PropertySource("classpath:/application.properties")
public class JwtProcessor {

    // 만료시간
    static private final long TOKEN_VALID_MILLISECOND = 1000L * 60 * 5; // 5분

    // 서명용 키
    private final Key key;

    public JwtProcessor(@Value("${jwt.secret}") String key) {
        // 32바이트 이상이어야함.
        this.key = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
//        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 자동키 생성 (테스트용)
    }

    // JWT 발급
    public String generateToken(String subject) {
        return Jwts.builder()
                // 주체 클레임 (사용자 ID, 이메일 등)
                .setSubject(subject)
                .setIssuedAt(new Date()) // 발급 시간
                .setExpiration(new Date(new Date().getTime() + TOKEN_VALID_MILLISECOND)) // 만료 시간
                .signWith(key)
                .claim("key", "value") // 우리가 JWT 넣고 싶은 내용
                .compact(); // 하나의 토큰 문자열로 변환
    }


    // JWT에서 username 추출 (pk)
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token) // token 파싱
                .getBody()
                .getSubject();
    }

    // JWT 검증 -> 유효기간이나 서명 검증
    public boolean validateToken(String token) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        // JWS -> 서명과 만료일을 검증해주는 로직포함
        return true;
    }
}
