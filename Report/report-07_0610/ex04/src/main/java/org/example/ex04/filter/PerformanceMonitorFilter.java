package org.example.ex04.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PerformanceMonitorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        String requestTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(startTime));
        String requestUrl = ((HttpServletRequest) request).getRequestURI();

        try {
            chain.doFilter(request, response);
        } finally {
            long endTime = System.currentTimeMillis();
            System.out.println("[" + requestTime + "] " + requestUrl + " - " + (endTime - startTime) + "ms 소요.");
        }
    }

    @Override
    public void destroy() {
    }
}
