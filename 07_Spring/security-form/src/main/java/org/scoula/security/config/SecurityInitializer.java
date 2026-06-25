package org.scoula.security.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

// SecurityFilterChain 필터를 서블릿 컨텍스트에 등록해주는 역할
// 스프링 필터의 동작 순서를 결정
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
