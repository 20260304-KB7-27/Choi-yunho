<%--
  Created by IntelliJ IDEA.
  User: youknow
  Date: 2026. 6. 16.
  Time: 오후 3:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>Insert title here</title>
</head>
<body>
<form action="/example/multi/" method="post" enctype="multipart/form-data">
  <div>
    <input type="file" name="files" />
  </div>
  <div>
    <input type="file" name="files" />
  </div>
  <div>
    <input type="file" name="files" />
  </div>
  <div>
    <input type="file" name="files" />
  </div>
  <div>
    <input type="file" name="files" />
  </div>
  <div>
    <input type="submit" />
  </div>
</form>
</body>
</html>
