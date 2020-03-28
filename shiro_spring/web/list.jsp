<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <title>List</title>
</head>
<body>
    <h4>List Page...</h4>

    Welcome:<shiro:principal></shiro:principal>

    <shiro:hasRole name="admin">
        <a href="admin.jsp">Admin Page。。。</a>
        <br/><br/>
    </shiro:hasRole>

    <shiro:hasRole name="user">
        <a href="user.jsp">User Page。。。</a>
        <br/><br/>
    </shiro:hasRole>

    <a href="shiro/testShiroAnnotation">Test Shiro Annotation</a>
    <br/><br/>

    <a href="shiro/logout">Logout</a>
    <br/><br/>

</body>
</html>
