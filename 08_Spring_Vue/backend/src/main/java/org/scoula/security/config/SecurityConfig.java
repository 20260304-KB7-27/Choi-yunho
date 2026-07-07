package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.scoula.security.filter.AuthenticationErrorFilter;
import org.scoula.security.filter.JwtAuthenticationFilter;
import org.scoula.security.filter.JwtUsernamePasswordAuthenticationFilter;
import org.scoula.security.handler.CustomAccessDeniedHandler;
import org.scoula.security.handler.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;
import java.util.Arrays;

/*
 * Spring Security의 보안 설정 클래스
 * */
@Configuration
@EnableWebSecurity // 필터체인 활성화
@RequiredArgsConstructor
@Log4j2
@MapperScan(basePackages = {"org.scoula.security.account.mapper"})
@ComponentScan(basePackages = {"org.scoula.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter; // JWT 인증필터
    private final AuthenticationErrorFilter authenticationErrorFilter; // jwt 예외 필터
    private final CustomAuthenticationEntryPoint authEntryPoint; // 미인증 요청 처리
    private final CustomAccessDeniedHandler customAccessDeniedHandler; // 미인가 처리
    @Autowired
    private JwtUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter;


    /*
     * CSRF : 로그인한 사용자를 악의적인 사이트에서 몰래 요청을 보내게하는 공격
     * */
    // 시큐리티 세팅할 공간
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSRF 필터 앞에다 encodingFilter를 놓겠다.
        http
                // JWT 예외필터 -> JWT 인증필터 -> 로그인 필터 -> Usernamepassword필터
                .addFilterBefore(authenticationErrorFilter,
                        UsernamePasswordAuthenticationFilter.class) // JWT 예외 필터
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class) // JWT 인증 필터
                .addFilterBefore(jwtUsernamePasswordAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class); // 로그인 필터

        // 예외 핸들러 등록
        http.exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)// 인증이 안된 경우의 핸들러
                .accessDeniedHandler(customAccessDeniedHandler); // 인가 문제 핸들러

        // CORS 설정 추가
        http.cors();

        http.httpBasic().disable() // 기본 HTTP 인증 비활성화
                .csrf().disable() // CSRF 비활성화
                .formLogin().disable() // formLogin 비활성화 관련 필터 해제
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션 생성 모드 설정

        // URL별 접근 권한 설정
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(HttpMethod.POST, "/api/member").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/member", "/api/member/*/changepassword").authenticated()
                .antMatchers(HttpMethod.POST, "/api/board/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/board/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/board/**").authenticated()
                .anyRequest().permitAll();

    }


    // 테스트용으로 메모리 상에 사용자정보 등록
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService) // userDetailService
                .passwordEncoder(passwordEncoder());
    }

    // 커스텀하기위한 Authentication 객체를 빈으로 등록
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // 인증인가 제외 URL
    // - 보안검사가 필요없는 정적 리소스나 특정 API는 Security Filter Chain을 거치지 않도록 설정
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        "/assets/**",
                        "/*", // 루트경로 바로 아래 /login, /member
//                        "/api/member/**", // /api/member 하위 경로 제외
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/v2/api-docs"
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration(); // CORS 설정객체

        // origin -> 출처
//        configuration.setAllowedOriginPatterns(Arrays.asList("*")); // 모든 출처에대해 허용
        configuration.setAllowedOriginPatterns(Arrays.asList("http://127.0.0.1:5500"));

        // 허용할 HTTP 메소드 목록
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        // 모든 요청헤더 허용
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // 쿠키, Authorization 헤더 등 자격증명을 포함한 요청 허용
        configuration.setAllowCredentials(true);

        // 특정 URL 경로 패턴에 대해 CORS 설정 적용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source; // Security 필터체인에서 사용
    }

}

