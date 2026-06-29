package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.scoula.security.filter.JwtUsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity // 필터 체인 활성화
@RequiredArgsConstructor
@Log4j2
@MapperScan(basePackages = {"org.scoula.security.account.mapper"})
@ComponentScan(basePackages = {"org.scoula.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    private JwtUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter;

    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    /*
     * CSRF : 로그인한 사용자를 악의적인 사이트에서 몰래 요청을 보내게하는 공격*/
    // 시큐리티 세팅할 공간
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // CSRF 필터 앞에 encodingFilter을 놓겠다
        http.addFilterBefore(encodingFilter(), CsrfFilter.class)
                .addFilterBefore(jwtUsernamePasswordAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);

        // CORS 설정 추가
        http.cors();

        http.httpBasic().disable() // 기본 HTTP 인증 비활성화
                .csrf().disable() // CSRF 비활성화
                .formLogin().disable() // formlogin 비활성화
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션 생성 안함

        // URL별 접근 권한 설정
        http.authorizeRequests()
                .antMatchers("/security/alle")
                .permitAll()
                .antMatchers("/security/admin")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/security/member")
                .access("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')");

    }

    // 테스트용으로 메모리 상에 사용자 정보 등록
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        // 관리자 계정
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("$2a$10$t5oZbmRtWxsx9vhU2kzkGuIqA8xn3vub4v0FcY9BnsS0D8XTyQxWm")
//                .roles("ADMIN", "MEMBER");

//        //멤버 계정
//        auth.inMemoryAuthentication()
//                .withUser("member")
//                .password("1234")
//                .roles("MEMBER");

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder()); // userDetailService
    }

    // 커스텀 하기 위한 Authentication 객체를 빈으로 등록
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // 인증인가 제외 URL
    // - 보안검사가 필요없는 정적 리소스나 특정 API는 Security Filter를 거치지 않게 함
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        "/assets/**",
                        "/*", // 루트 경로 바로 아래 /login, /member
                        "/api/member/**" // /api/member 하위 경로 제외
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration(); // CORS 설정 객체

        // origin -> 출처
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); // 모든 출처에 대해 허용

        // 허용할 HTTP 메소드 목록
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        // 모든 요청헤더 허용
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // 쿠키, Authorization 헤더 등 자격증명을 포함한 요청 허용
        configuration.setAllowCredentials(true);

        // 특정 URL 경로 패턴에 대해 CORS 설정 적용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source; // Security 필터 체인에서 사용
    }
}
/*
 * Spring Security의 보안 설정 클래스
 * */
