
<%-- index.jsp --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <style>
        .button-link {
            display: inline-block;
            padding: 10px 20px;
            margin-right: 10px;
            text-decoration: none;
            background-color: #3498db;
            color: #ffffff;
            border-radius: 5px;
            border: 1px solid #2980b9;
            font-size: 14px;
        }

        .button-link:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <%
            Cookie c[] = request.getCookies();
            if (c != null) {
                for (Cookie cookie : c) {
                    if (cookie.getName().equals("CookieName")) {
                        response.sendRedirect("/MainPage/Main");
                        break;
                    }
                }
            }
        %>        
<a href="/LoginServlet/Login" class="button-link">Đăng nhập</a>
<a href="/RegisterServlet/Register" class="button-link">Đăng ký</a>
<a href="/MainPage/Main" class="button-link">Trang chủ</a>
</body>
</html>
<%-- Hiển thị thông báo thành công hoặc lỗi --%>
<% if (session.getAttribute("successMessage") != null) { %>
    <p style="color: green;"><%= session.getAttribute("successMessage") %></p>
    <% session.removeAttribute("successMessage"); %>
<% } %>
<% if (session.getAttribute("errorMessage") != null) { %>
    <p style="color: red;"><%= session.getAttribute("errorMessage") %></p>
    <% session.removeAttribute("errorMessage"); %>
<% } %>