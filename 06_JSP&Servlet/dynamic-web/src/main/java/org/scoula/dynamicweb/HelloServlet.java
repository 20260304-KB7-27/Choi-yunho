package org.scoula.dynamicweb;

import java.io.*;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

/*
* @WebServlet
* - 현재 클래스를 Servlet으로 등록하는 어노테이션
* - web.xml에 직접 등록하는 설정을 대체함.
* */
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}