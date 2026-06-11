package org.example.ex04.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DecoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestUrl = ((HttpServletRequest) request).getRequestURI();

        System.out.println(requestUrl + " =======>");
        try {
            chain.doFilter(request, response);
        } finally {
            System.out.println("<=======");
        }
    }

    @Override
    public void destroy() {
    }
}
