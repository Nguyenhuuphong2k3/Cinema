<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tạo Phòng Phim</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Tạo Phòng Phim</h1>
    <form action="/room1/create" method="post">
        <div class="form-group">
            <label for="name">Tên phòng</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="movie">Chọn phim</label>
            <select class="form-control" id="movie" name="movie1.id" required>
                <c:forEach items="${movies}" var="movie">
                    <option value="${movie.id}">${movie.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Tạo Phòng</button>
    </form>
</div>
</body>
</html>
