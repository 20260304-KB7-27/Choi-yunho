package org.example.ex04.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cart_delete")
public class CartDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();
        session.invalidate();

        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("장바구니 비웠음!!");
        out.println("<a href='session_product.jsp'>상품 선택 페이지</a>");
        out.println("</body></html>");
    }
}
