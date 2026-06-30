package org.scoula.config;

import io.swagger.models.Swagger;
import org.scoula.security.config.SecurityConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    final String LOCATION = "c:/upload";
    final long MAX_FILE_SIZE = 1024 * 1024 * 10L;
    final long MAX_REQUEST_SIZE = 1024 * 1024 * 10L;
    final int FILE_SIZE_THRESHOLD = 1024 * 1024 * 5;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                ServletConfig.class,
                SwaggerConfig.class};
    }

    /*
     * DispatcherServelt이 처리할 URL 패턴 목록
     * */
    @Override
    protected String[] getServletMappings() {

        return new String[] {
                "/",
                "/swagger-ui.html",       // Swagger UI 진입점
                "/swagger-resources/**",  // Swagger 내부 리소스 요청
                "/v2/api-docs",           // JSON 형태 API 명세 요청
                "/webjars/**"             // Swagger UI JS/CSS 등 정적 자원
        };
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[]{characterEncodingFilter};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        registration.setInitParameter("ThrowExceptionIfNoHandlerFound", "true");

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                LOCATION,MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD
        );

        registration.setMultipartConfig(multipartConfigElement);
    }
}