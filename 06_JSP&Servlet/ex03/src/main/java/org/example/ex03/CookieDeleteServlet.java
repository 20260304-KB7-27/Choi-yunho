package org.example.ex03;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cookie-delete")
public class CookieDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //MIME 타입 설정
        response.setContentType("text/html; charset=UTF-8");

        // 쿠키 객체 생성
        Cookie cookie = new Cookie("myCookieData", "");

        // 쿠키 만료 시간 설정
        cookie.setMaxAge(0);

        // 같은 이름으로 MaxAge(0)으로 설정한 쿠키를 전송 -> 브라우저에서 즉시 삭제
        response.addCookie(cookie);

        // 자바 I/O
        PrintWriter out = response.getWriter();

        // html 작성
        out.println("<html><body>");
        out.println("쿠키 삭제 완료");
        out.println("<a href = 'session_cookie.jsp'>돌아가기</a>");
        out.println("</body></html>");
    }
}