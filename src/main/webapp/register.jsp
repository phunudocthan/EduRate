<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    </head>
    <body>
        <a href="/">Back</a>
        <sql:setDataSource var="conn"
                           driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url="jdbc:sqlserver://ASUS3DK\\CHIBANG:1433;databaseName=EduReview;encrypt=true;trustServerCertificate=true;"
                           user="sa"
                           password="sa123" />
        <sql:query dataSource="${conn}" var="rsTag">
            SELECT TagID, TagCategory FROM TagCategory
        </sql:query>

        <sql:query var="rsGender" dataSource="${conn}">
            SELECT GenderID, Gender FROM Gender
        </sql:query>

        <sql:query var="rsProvince" dataSource="${conn}">
            SELECT ProvinceID, ProvinceName FROM Province
        </sql:query>

        <h1>Register</h1>
        <form action="RegisterServlet" method="post" onsubmit="return validateForm()" enctype="multipart/form-data">
            <label>First Name: <input type="text" id="firstName" name="firstName" required></label><br>
            <label>Last Name: <input type="text" id="lastName" name="lastName" required></label><br>
            <label>Username: <input type="text" name="userName" required></label><br>
            <label>Password: <input type="password" name="passWord" required></label><br>
            <label>Birthday: <input type="date" name="birthDay" required></label><br>
            <label>Picture: <input type="file" name="picture"></label><br>
            <label>Email: <input type="email" name="email" required=""></label><br>
            <label>Phone: <input type="tel" id="phone" name="phone" required=""></label><br>
            <label>Tag:
                <select name="tagId">
                    <c:forEach var="row" items="${rsTag.rows}">
                        <option value="${row.TagID}">${row.TagCategory}</option>
                    </c:forEach>
                </select>
            </label><br>

            <label>Gender:
                <select name="genderID">
                    <c:forEach var="row" items="${rsGender.rows}">
                        <option value="${row.GenderID}">${row.Gender}</option>
                    </c:forEach>
                </select>
            </label><br>

            <label>Province:
                <select id="provinceID" name="provinceID" required>
                    <option value="">Chọn tỉnh/thành phố</option>
                    <c:forEach var="row" items="${rsProvince.rows}">
                        <option value="${row.ProvinceID}">${row.ProvinceName}</option>
                    </c:forEach>
                </select>
            </label><br>

            <label for="DistrictID">Quận/Huyện:</label>
            <select id="DistrictID" name="DistrictID" required>
                <option value="">Chọn quận/huyện</option>
            </select><br>

            <label for="WardID">Xã/Phường:</label>
            <select id="WardID" name="WardID" required>
                <option value="">Chọn xã/phường</option>
            </select><br>

            <input type="submit" value="Register">
        </form>
        <%-- Hiển thị thông báo lỗi username --%>
    <% if (request.getSession().getAttribute("userNameError") != null) { %>
        <p style="color: red;"><%= request.getSession().getAttribute("userNameError") %></p>
    <% } %>

    <%-- Hiển thị thông báo lỗi email --%>
    <% if (request.getSession().getAttribute("emailError") != null) { %>
        <p style="color: red;"><%= request.getSession().getAttribute("emailError") %></p>
    <% } %>

    <%-- Hiển thị thông báo lỗi phone --%>
    <% if (request.getSession().getAttribute("phoneError") != null) { %>
        <p style="color: red;"><%= request.getSession().getAttribute("phoneError") %></p>
    <% } %>

    <%-- Hiển thị thông báo thành công --%>
    <% if (request.getSession().getAttribute("successNote") != null) { %>
        <p style="color: green;"><%= request.getSession().getAttribute("successNote") %></p>
    <% } %>

    <%-- Hiển thị thông báo thất bại --%>
    <% if (request.getSession().getAttribute("failNote") != null) { %>
        <p style="color: red;"><%= request.getSession().getAttribute("failNote") %></p>
    <% } %>
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

            function validateForm() {
                var firstName = document.getElementById('firstName').value.trim();
                var lastName = document.getElementById('lastName').value.trim();
                var phoneNumber = document.getElementById('phone').value.trim();
                var namePattern = /[A-Z][a-zA-Z]+/;
                var phonePattern = /^$|(09|08|07|05|03)\d{8}$/;

                if (!namePattern.test(firstName) || !namePattern.test(lastName)) {
                    alert('Tên và họ chỉ được chứa chữ cái, và chữ cái đầu tiên phải viết hoa.');
                    return false;
                }

                if (!phonePattern.test(phoneNumber)) {
                    alert('Số điện thoại không hợp lệ. Vui lòng nhập số điện thoại di động Việt Nam đúng định dạng.');
                    return false;  
                }

                var provinceID = document.getElementById('provinceID').value;
                var districtID = document.getElementById('DistrictID').value;
                var wardID = document.getElementById('WardID').value;

                if (!provinceID || provinceID === "" || provinceID === "Chọn tỉnh/thành phố" ||
                        !districtID || districtID === "" || districtID === "Chọn quận/huyện" ||
                        !wardID || wardID === "" || wardID === "Chọn xã/phường") {
                    alert('Vui lòng chọn đầy đủ Tỉnh/Thành phố, Quận/Huyện và Xã/Phường.');
                    return false;
                }

                return true; // Submit the form if all validations pass
            }
        </script>
    </body>
</html>
