<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh Sách Phòng Phim</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Danh Sách Phòng Phim</h1>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>Tên phòng</th>
                <th>Phim</th>
                <th>Thành viên</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${rooms}" var="room">
                <tr>
                    <td>${room.id}</td>
                    <td>${room.name}</td>
                    <td>${room.movie1.name}</td>
                    <td>
                        <c:forEach items="${room.users}" var="user">
                            <span>${user}, </span>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
