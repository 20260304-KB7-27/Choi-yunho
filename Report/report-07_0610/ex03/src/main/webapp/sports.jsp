<%--
  Created by IntelliJ IDEA.
  User: youknow
  Date: 2026. 6. 10.
  Time: 오후 4:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>getParameterValues 실습</title>
</head>
<body>
<h1>getParameterValues 실습</h1>

<form action="sports" method="post">
    <fieldset>
        <legend>좋아하는 운동 및 성별</legend>
        <ul style="list-style: none">
            <li>
                야구 <input type="checkbox" name="sports" value="야구">
                축구 <input type="checkbox" name="sports" value="축구">
                농구 <input type="checkbox" name="sports" value="농구">
            </li>
            <li>
                남 <input type="radio" name="sex" value="남자" checked>
                여 <input type="radio" name="sex" value="여자">
            </li>
            <li>
                <input type="submit" value="전송">
            </li>
        </ul>
    </fieldset>
</form>
</body>
</html>
