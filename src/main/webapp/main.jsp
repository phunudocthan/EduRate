<%-- 
    Document   : main
    Created on : Jul 3, 2024, 1:49:21 PM
    Author     : Bang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            header, footer {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px;
                background-color: #f4f4f4;
            }
            header {
                border-bottom: 1px solid #ddd;
            }
            footer {
                border-top: 1px solid #ddd;
            }
            .container {
                max-width: 1200px;
                margin: 0 auto;
                padding: 20px;
            }
            .user-info {
                display: flex;
                align-items: center;
            }
            .user-info a {
                display: flex;
                align-items: center;
                text-decoration: none;
                color: inherit;
            }
            .user-info img {
                border-radius: 50%;
                margin-right: 10px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #f4f4f4;
            }
            a {
                text-decoration: none;
                color: #007bff;
            }
            a:hover {
                text-decoration: underline;
            }
            .admin-actions {
                display: flex;
                gap: 10px;
            }
            .admin-actions button {
                padding: 5px 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
                cursor: pointer;
            }
            .admin-actions button.delete {
                background-color: #dc3545;
            }
        </style>
    </head>
    <body>

        <sql:setDataSource var="conn"
                           driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url="jdbc:sqlserver://ASUS3DK\\CHIBANG:1433;databaseName=EduReview;encrypt=true;trustServerCertificate=true;"
                           user="sa"
                           password="sa123" />
        <sql:query dataSource="${conn}" var="rsSchool">
            Select SchoolID, SchoolName, EstablishedDate, ReviewScore, 
            (p.ProvinceName + ', ' + d.DistrictName + ', ' + w.WardName) as [Address],
            st.[Type], Picture
            from School s
            join Province p on s.ProvinceID = p.ProvinceID
            join District d on s.DistrictID = d.DistrictID
            join Ward w on s.WardID = w.WardID
            join SchoolType st on s.SchoolTypeID = st.TypeID
        </sql:query>
        <%
            String user = (String) session.getAttribute("SessionName");
        %>       
        <header>
        <a href="/">Back</a>
        <div class="user-info">
            <a href="/UserInfo/User/<%= user %>">
                <%=user%>
            </a>
        </div>
    </header>

        <div class="container">
            <h1>EDURATE</h1>
            <table>
                <thead>
                    <tr>
                        <th>SchoolID</th>
                        <th>SchoolName</th>
                        <th>EstablishedDate</th>
                        <th>ReviewScore</th>
                        <th>Address</th>
                        <th>SchoolType</th>
                        <th>Picture</th>
                            <c:if test="${sessionScope.role == 'admin'}">
                            <th>Actions</th>
                            </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${rsSchool.rows}">
                        <tr>
                            <td>${row.SchoolID}</td>
                            <td><a href="/SchoolServlet/SchoolInfo/${row.SchoolID}">${row.SchoolName}</a></td>
                            <td>${row.EstablishedDate}</td>
                            <td>${row.ReviewScore}</td>
                            <td>${row.Address}</td>
                            <td>${row.Type}</td>
                            <td><img src="<%= request.getContextPath()%>/${row.Picture}" alt="School Picture" width="100" height="100"></td>
                                <c:if test="${sessionScope.role == 'admin'}">
                                <td class="admin-actions">
                                    <button onclick="location.href = 'createSchool.jsp'">Create</button>
                                    <button class="delete" onclick="location.href = 'deleteSchool.jsp?schoolId=${row.SchoolID}'">Delete</button>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <footer>
            <div>© 2024 EduReview</div>
        </footer>

    </body>
</html>
