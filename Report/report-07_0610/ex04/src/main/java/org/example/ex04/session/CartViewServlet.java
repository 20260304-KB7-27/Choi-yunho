package org.example.ex04.session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/cart_view")
public class CartViewServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //MIME 타입 설정
        response.setContentType("text/html; charset=UTF-8");

        // 자바 I/O
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(20);

        ArrayList<String> productList = (ArrayList<String>) session.getAttribute("product");

        // html 작성
        out.println("<html><body>");
        out.println("장바구니 리스트상품: " + productList + "<br>");
        out.println("<a href='session_product.jsp'>상품 선택 페이지</a><br>");
        out.println("<a href='cart_delete'>장바구니 비우기</a>");
        out.println("</body></html>");
    }
}
