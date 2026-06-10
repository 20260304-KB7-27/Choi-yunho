package org.example.ex04.session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/cart_save")
public class CartSaveServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //MIME 타입 설정
        response.setContentType("text/html; charset=UTF-8");

        // 자바 I/O
        PrintWriter out = response.getWriter();

        String product = request.getParameter("product");
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(20);

        ArrayList<String> productList = (ArrayList<String>) session.getAttribute("product");

        if (productList == null) {
            productList = new ArrayList<>();
            session.setAttribute("product", productList);
        }

        if (product != null && !product.trim().isEmpty()) {
            productList.add(product);
        }

        // html 작성
        out.println("<html><body>");
        out.println("Product 추가!!<br>");
        out.println("<a href='session_product.jsp'>상품 선택 페이지</a><br>");
        out.println("<a href='cart_view'>장바구니 보기</a>");
        out.println("</body></html>");
    }
}
