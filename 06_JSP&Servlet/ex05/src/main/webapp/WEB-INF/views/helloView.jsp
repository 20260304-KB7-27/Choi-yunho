<%--
  Created by IntelliJ IDEA.
  User: youknow
  Date: 2026. 6. 11.
  Time: 오후 1:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- EL (Expression Language)
- JSP 자바 코드를 직접 작성하지 않고 속성 출력을 할 수 있도록 돕는 표현식 문법
- 주로 jsp에서 scope에 저장된 데이터를 꺼낼 때 사용
--%>
<p>안녕하세요, ${username}</p>
<%--<p>안녕하세요 <%= request.getAttribute("username")%>> </p>--%>
</body>
</html>
