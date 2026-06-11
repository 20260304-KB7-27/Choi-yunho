package org.example.ex05;

import org.example.ex05.domain.Member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/jstl2")
public class ForEachServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Member> members = new ArrayList<>();

		members.add(new Member("홍길동", "hong"));
		members.add(new Member("김철수", "kkim"));
		members.add(new Member("이영희", "leee"));

		request.setAttribute("memberList", members);
		request.getRequestDispatcher("jstl/forEach.jsp").forward(request, response);
    }
}