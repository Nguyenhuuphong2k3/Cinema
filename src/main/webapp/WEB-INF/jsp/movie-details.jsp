<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết Phim</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        th {
            padding: 0px 20px 5px 0px;
        }

        .choose-movie-container {
            margin-top: 30px;
            margin-bottom: 30px;
        }

        .choose-movie-title {
            font-family: 'Roboto', sans-serif;
            font-size: 2.5em;
            font-weight: bold;
            color: #FADA5E;
            text-transform: uppercase;
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.8);
        }

        @media (max-width: 768px) {
            .choose-movie-title {
                font-size: 2em;
            }
        }
    </style>
</head>

<body>
<jsp:include page="header.jsp" />
<br><br><br>
<div class="container">
    <div class="text-center choose-movie-container">
        <h1 class="choose-movie-title">Chi Tiết Phim</h1>
    </div>

    <!-- Movie details -->
    <div class="row">
        <div class="col-md-4">
            <img src="${movie.smallImageURl}" alt="Poster của phim" class="img-fluid">
            <c:choose>
                <c:when test="${sessionScope.jwtResponse == null}">
                    <button class="btn btn-danger btn-block btn-buy-ticket-not-signed-in">Mua vé</button>
                </c:when>
                <c:otherwise>
                    <a href="branches?movieId=${movie.id}" class="btn btn-danger btn-block">Mua Vé</a>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-8">
            <table class="table">
                <tr><th>Tên Phim:</th><td>${movie.name}</td></tr>
                <tr><th>Đạo diễn:</th><td>${movie.director}</td></tr>
                <tr><th>Diễn viên:</th><td>${movie.actors}</td></tr>
                <tr><th>Ngày khởi chiếu:</th><td>${movie.releaseDate}</td></tr>
                <tr><th>Thể loại:</th><td>${movie.categories}</td></tr>
                <tr><th>Thời lượng:</th><td>${movie.duration} phút</td></tr>
                <tr><th>Ngôn ngữ:</th><td>${movie.language}</td></tr>
                <tr><th>Rated:</th><td>${movie.rated}</td></tr>
            </table>
        </div>
    </div>

    <div class="mt-4">
        <h2>Trailer:</h2>
        <iframe width="100%" height="400" src="${movie.trailerURL}" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
    </div>

    <div class="mt-4">
        <h2>Giới thiệu:</h2>
        <p>${movie.longDescription}</p>
    </div>

    <!-- Comments Section -->
    <div class="mt-5">
        <h2>Bình luận:</h2>
        <ul class="list-unstyled" id="comment-list">
            <c:forEach items="${comments}" var="comment">
                <li class="mb-3">
                    <strong>${comment.userFullName}:</strong>
                    <p>${comment.content}</p>
                    <small>${comment.createdAt}</small>
                </li>
            </c:forEach>
        </ul>

        <!-- Comment Form -->
        <c:if test="${sessionScope.jwtResponse != null}">
            <form action="/movie-details/add-comment" method="post">
                <input type="hidden" name="movieId" value="${movie.id}" />
                <textarea name="content" class="form-control" rows="3" placeholder="Nhap binh luan cua ban..." required></textarea>
                <button type="submit" class="btn btn-primary mt-2">Gửi</button>
            </form>
        </c:if>
        <c:if test="${sessionScope.jwtResponse == null}">
            <p><a href="/login">Đăng nhập</a> để thêm bình luận.</p>
        </c:if>
    </div>

    <a href="/" class="btn btn-secondary mt-3">Quay lại</a>
</div>

<!-- Modal Login -->
<c:if test="${sessionScope.jwtResponse == null}">
    <script>
        $(document).ready(function () {
            $('.btn-buy-ticket-not-signed-in').on('click', function () {
                $('#modalLoginForm').modal('show');
            });
        });
    </script>
</c:if>
</body>
</html>
