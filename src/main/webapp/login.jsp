<%-- 
    Document   : login
    Created on : Jun 22, 2024, 7:42:30 PM
    Author     : Bang
--%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Education Rating</title>
    </head>
    <body>
        <h1>Login</h1>
        <a href="/" >Back</a>
        <form action="LoginServlet" method="post">
            <div class="form-group">
                <label for="txtUsername">Username:</label>
                <input type="text" class="form-control" id="txtUsername" name="txtUsername" placeholder="Enter username">
            </div>
            <div class="form-group">
                <label for="txtPassword">Password:</label>
                <input type="password" class="form-control" id="txtPassword" name="txtPassword" placeholder="Enter password">
            </div>
            <button name="btn-Login" type="submit" >Login</button>   <a href="/RegisterServlet/Register" class="button-link">Chưa có mật khẩu</a>
        </form>
        <c:if test="${not empty err}">
            <p style="color: red" >${err}</p>
        </c:if>
    </body>
</html>
