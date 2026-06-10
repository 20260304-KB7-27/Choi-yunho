package org.example.ex03;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/*
Session
- 서버 쪽에 저장됨 (JSESSIONID -> 식별표, 어떤 세션과의 연결)
- 객체도 통째로 저장이 가능
- 일정 시간 동안 활동이 없으면 만료 또는 invalidate()로 즉시 삭제 가능
 */
@WebServlet("/session-save")
public class SessionSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        //MIME 타입 설정
        response.setContentType("text/html; charset=UTF-8");

        String value = request.getParameter("sessionValue");

        // getSession() : 세션이 없으면 새로 생성 (JSESSIONID 쿠키 발급)
        HttpSession session = request.getSession();

        session.setAttribute("mySessionData", value); // 기본ㄱ밧 30

        // 마지막 요청 후 지정 시간 동안 요청이 없으면 세션 소멸
        session.setMaxInactiveInterval(10);

        // 자바 I/O
        PrintWriter out = response.getWriter();

        // html 작성
        out.println("<html><body>");
        out.println("<h2>Session 저장 결과 </h2>");
        out.println("<h2>저장된 값 : " + session.getAttribute("mySessionData") + "</h2>");
        out.println("<a href = 'session_cookie.jsp'>돌아가기</a>");
        out.println("</body></html>");
    }
}