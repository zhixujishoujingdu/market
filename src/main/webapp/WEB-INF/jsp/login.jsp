<%--
  Created by IntelliJ IDEA.
  User: zqx
  Date: 2018/6/5
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/user/login.do">
        <input type="text" name="username">
        <input type="text" name="password">
        <input type="submit" value="submit">
    </form>
</body>
</html>
