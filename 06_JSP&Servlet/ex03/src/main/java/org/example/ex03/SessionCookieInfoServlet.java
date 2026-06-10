package org.example.ex03;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/info")
public class SessionCookieInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();

		out.println("<html><body>");
		out.println("<h2>Session / Cookie 현재 상태</h2>");

		out.println("<h3>Session</h3>");
		HttpSession session = req.getSession(false);

		if (session == null) {
			out.println("세션 없음<br>");
		} else {
			String sessionValue = (String) session.getAttribute("mySessionData");
			out.println("세션 ID: " + session.getId() + "<br>");
			out.println("mySessionData: " + (sessionValue != null ? sessionValue : "없음") + "<br>");
		}

		out.println("<h3>Cookie</h3>");

		// 쿠키들 꺼내기
		Cookie[] cookies = req.getCookies();

		if (cookies == null) {
			out.println("쿠키 없음<br>");
		} else {
			String cookieValue = null;
			for (Cookie c : cookies) {
				// key 값에 일치하는 쿠키 찾아서 value 꺼내기
				if ("myCookieData".equals(c.getName())) {
					cookieValue = c.getValue();
					break;
				}
			}
			out.println("myCookieData: " + (cookieValue != null ? cookieValue : "없음") + "<br>");
		}

		out.println("<br><a href='session_cookie.jsp'>돌아가기</a>");
		out.println("</body></html>");
	}
}