package org.example.ex03;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/session-delete")
public class SessionDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //MIME 타입 설정
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();

        // 세션에서 키에 해당하는 데이터 삭제
        session.removeAttribute("mySessionData");

        // 세션 전체 삭제
//        session.invalidate();

        // 자바 I/O
        PrintWriter out = response.getWriter();

        // html 작성
        out.println("<html><body>");
        out.println("삭제 완료");
        out.println("<br><a href='session_cookie.jsp'>돌아가기</a>");
        out.println("</body></html>");
    }
}