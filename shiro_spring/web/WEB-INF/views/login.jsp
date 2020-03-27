<%--
  Created by IntelliJ IDEA.
  User: Hushunkang
  Date: 2020/3/27
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户</title>
</head>
<body>
    <h4>Login Page...</h4>

    <form action="shiro/login" method="post">
        username: <input type="text" name="username"/>
        <br/><br/>

        password: <input type="password" name="password"/>
        <br/><br/>

        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
