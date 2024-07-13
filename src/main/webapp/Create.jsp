<%-- 
    Document   : Create
    Created on : Jul 13, 2024, 10:59:22 PM
    Author     : Bang
--%>

<%@page import="DAOs.SchoolDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <sql:setDataSource var="conn"
                           driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url="jdbc:sqlserver://ASUS3DK\\CHIBANG:1433;databaseName=EduReview;encrypt=true;trustServerCertificate=true;"
                           user="sa"
                           password="sa123"/>
        <sql:query var="rsProvince" dataSource="${conn}">
            SELECT ProvinceID, ProvinceName FROM Province
        </sql:query>
        <sql:query var="rsSchoolType" dataSource="${conn}">
            SELECT TypeID, [Type] FROM SchoolType
        </sql:query>
        <h1>Add New School</h1>
        <div class="tab-pane fade show active" id="general" role="tabpanel" aria-labelledby="general-tab">
            <form action="CreateServlet" method="post" enctype="multipart/form-data" >
                <div class="form-row">
                    <div class="form-group">
                        <label for="SchoolID">School ID</label>
                        <input type="text" id="SchoolID" name="SchoolID" placeholder="Enter School ID" required />
                    </div>
                    <div class="form-group">
                        <label for="SchoolName">School Name</label>
                        <input type="text" id="SchoolName" name="SchoolName" placeholder="Enter School Name" required />
                    </div>
                    <div class="form-group">
                        <label for="EstablishedDate">Established Date</label>
                        <input type="date" id="EstablishedDate" name="EstablishedDate" placeholder="Enter Established Date" required />
                    </div>
                    <div class="form-group">
                        <label for="Website">Website</label>
                        <input type="text" id="Website" name="Website" placeholder="Enter School Website"  required/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label for="tag">Tag</label>
                        <select name="tag" required>
                            <option value="">Select Tag</option>
                            <c:forEach var="row" items="${rsSchoolType.rows}">
                                <option value="${row.TypeID}">${row.Type}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="TotalStudents">Total Students</label>
                        <input type="number" id="TotalStudents" name="TotalStudents" placeholder="Enter Total Students" required />
                    </div>
                    <div class="form-group">
                        <label for="Picture">Picture</label>
                        <input type="file" id="Picture" name="Picture" required />
                    </div>
                    <div class="form-group">
                        <label for="Website">Website</label>
                        <input type="text" id="Website" name="Website" required />
                    </div>
                    <div class="form-group">
                        <label for="Description">Description</label>
                        <input type="text" id="Description" name="Description" placeholder="Enter Description" required />
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label for="province">Province</label>
                        <select id="provinceID" name="province" required>
                            <option value="">Select Province</option>
                            <c:forEach var="row" items="${rsProvince.rows}">
                                <option value="${row.ProvinceID}">${row.ProvinceName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="district">District</label>
                        <select id="DistrictID" name="district" required>
                            <option value="">Select District</option>
                        </select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label for="ward">Ward</label>
                        <select id="WardID" name="ward" required>
                            <option value="">Select Ward</option>
                        </select>
                    </div>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Create</button>
                    <button type="button" class="btn btn-secondary">Cancel</button>
                </div>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#provinceID').change(function () {
                    var provinceId = $(this).val();
                    fillOptions('DistrictID', provinceId);
                });
                $('#DistrictID').change(function () {
                    var districtId = $(this).val();
                    fillOptions('WardID', districtId);
                });
            });

            function fillOptions(ddId, parentId) {
                var dd = $('#' + ddId);
                $.getJSON('/GetDistrict', {dd: ddId, val: parentId}, function (opts) {
                    dd.empty(); // Clear old options first.
                    if (opts) {
                        $.each(opts, function (key, value) {
                            dd.append($('<option/>').val(key).text(value));
                        });
                    } else {
                        dd.append($('<option/>').text("Please select parent"));
                    }
                });
            }
        </script>
    </body>
</html>
