package org.example.ex03;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 한글 인코딩 설정
        request.setCharacterEncoding("UTF-8");

        Enumeration<String> enu = request.getParameterNames();

        //MIME 타입 설정
        response.setContentType("text/html; charset=UTF-8");

        // 자바 I/O
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        while (enu.hasMoreElements()) {

            // parameter key 목록에서 1개만 꺼냄
            String name = enu.nextElement();
            // request에서 name에 맞는 value 찾기
            String value = request.getParameter(name);

            out.print(name + " : " + value);
        }

        // html 작성
        out.println("");
        out.println("</body></html>");
    }
}