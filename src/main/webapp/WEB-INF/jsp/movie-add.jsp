<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Phim</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <h1>Thêm Phim Mới</h1>
    <form action="/movie-management/add" method="post">
        <div class="form-group">
            <label for="name">Tên Phim</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>

        <div class="form-group">
            <label for="director">Đạo Diễn</label>
            <input type="text" class="form-control" id="director" name="director" required>
        </div>

        <div class="form-group">
            <label for="actors">Diễn Viên</label>
            <input type="text" class="form-control" id="actors" name="actors" required>
        </div>

        <div class="form-group">
            <label for="categories">Thể Loại</label>
            <input type="text" class="form-control" id="categories" name="categories" required>
        </div>

        <div class="form-group">
            <label for="releaseDate">Ngày Phát Hành</label>
            <input type="date" class="form-control" id="releaseDate" name="releaseDate" required>
        </div>

        <div class="form-group">
            <label for="duration">Thời Gian</label>
            <input type="number" class="form-control" id="duration" name="duration" required>
        </div>

        <div class="form-group">
            <label for="language">Ngôn Ngữ</label>
            <input type="text" class="form-control" id="language" name="language" required>
        </div>

        <div class="form-group">
            <label for="rated">Đánh Giá</label>
            <input type="text" class="form-control" id="rated" name="rated" required>
        </div>

        <div class="form-group">
            <label for="shortDescription">Mô Tả Ngắn</label>
            <textarea class="form-control" id="shortDescription" name="shortDescription" rows="3" required></textarea>
        </div>

        <div class="form-group">
            <label for="longDescription">Mô Tả Dài</label>
            <textarea class="form-control" id="longDescription" name="longDescription" rows="5"></textarea>
        </div>

        <!-- Ảnh Nhỏ -->
        <div class="form-group">
            <label for="smallImageURl">Ảnh Nhỏ (URL)</label>
            <input type="url" class="form-control" id="smallImageURl" name="smallImageURl" required>
        </div>

        <!-- Ảnh Lớn -->
        <div class="form-group">
            <label for="largeImageURL">Ảnh Lớn (URL)</label>
            <input type="url" class="form-control" id="largeImageURL" name="largeImageURL" required>
        </div>

        <div class="form-group">
            <label for="isShowing">Đang Chiếu</label>
            <select class="form-control" id="isShowing" name="isShowing">
                <option value="1">Có</option>
                <option value="0">Không</option>
            </select>
        </div>

        <!-- Link Trailer -->
        <div class="form-group">
            <label for="trailerURL">Link Trailer</label>
            <input type="url" class="form-control" id="trailerURL" name="trailerURL">
        </div>

        <button type="submit" class="btn btn-primary">Thêm Phim</button>
        <a href="/" class="btn btn-secondary">Quay lại</a>
    </form>
</div>


</body>
</html>
