package org.example.ex05;

import org.example.ex05.domain.Member;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/scope")
public class ScopeServlet extends HttpServlet {
    ServletContext sc;

    /*
     * EL Scope
     *
     * page -> request -> session -> Application
     * - EL은 내부적으로 getter 메서드를 사용해 객체의 프로퍼티에 접근한다
     * */
    @Override
    public void init(ServletConfig config) throws ServletException {
        sc = config.getServletContext();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        sc.setAttribute("scopeName", "applicationScope 값"); // Application Scope

        // Session 만들고 sessionScope에 데이터 담
        HttpSession session = req.getSession(); // Session Scope
        session.setAttribute("scopeName", "sessionScope 값");

        // RequestScope에 값 담아주기
        req.setAttribute("scopeName", "requestScope 값"); // Request Scope

        Member member = new Member("홍길동", "hong");

        req.setAttribute("member", member);

        req.getRequestDispatcher("scope.jsp").forward(req, res);
    }
}