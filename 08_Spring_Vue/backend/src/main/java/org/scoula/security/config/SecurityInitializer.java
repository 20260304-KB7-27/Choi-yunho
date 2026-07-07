package org.scoula.security.config;


import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;

// SecurityFilterChain 필터를 서블릿 컨텍스트에 등록해주는 역할
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    // 문자셋 필터
    private CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        insertFilters(servletContext, encodingFilter(), new MultipartFilter());
    }
}
