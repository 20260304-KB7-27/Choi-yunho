package org.example.ex05.domain;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/jstl")
public class ConditionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String scoreStr = request.getParameter("score");

		int score = 0;

		if (scoreStr != null && scoreStr.trim().isEmpty()) {
			score = Integer.parseInt(scoreStr);
		}

		request.setAttribute("score", score);

		RequestDispatcher rd = request.getRequestDispatcher("jstl/condition.jsp");

		rd.forward(request, response);
	}
}