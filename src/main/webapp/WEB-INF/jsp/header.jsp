<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css" integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog==" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <title>CINEMA</title>
    <style>
        /* Style cơ bản cho dropdown */
        .dropdown-menu {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 1;
            padding: 5px 0;
            border-radius: 5px; /* Bo tròn góc */
            transition: all 0.3s ease; /* Thêm hiệu ứng chuyển động */
        }

        .dropdown-menu li {
            position: relative;
            padding: 8px 12px;
            transition: background-color 0.3s ease; /* Thêm hiệu ứng hover */
        }

        .dropdown-menu li a {
            text-decoration: none;
            color: gray;
            display: block;
            padding: 8px;
            font-size: 1rem;
            border-radius: 5px; /* Bo tròn góc */
        }

        .dropdown-menu li a:hover {
            background-color: #f39c12; /* Màu nền khi hover */
            color: white; /* Chuyển màu chữ khi hover */
        }

        /* Toggle dropdown */
        .toggle-dropdown {
            cursor: pointer;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 12px;
        }

        .toggle-dropdown span {
            color: gray;
        }

        .toggle-dropdown i {
            margin-left: 5px;
        }

        /* Hiển thị menu con khi hover vào */
        .dropdown:hover > .dropdown-menu,
        .dropdown.active > .dropdown-menu {
            display: block;
        }

        /* Hiển thị menu con ở bên phải */
        .dropdown-menu .dropdown-menu {
            left: 100%;
            top: 0;
        }

        /* Đảo chiều mũi tên khi mở dropdown */
        .dropdown.active > a > i {
            transform: rotate(180deg);
            transition: transform 0.3s ease; /* Thêm hiệu ứng xoay */
        }

        /* Cải tiến Navbar với gradient nền và hiệu ứng hover */
        .navbar {
            background: linear-gradient(45deg, #1a1a1a, #333);
            transition: background-color 0.3s ease;
        }

        .navbar .navbar-brand {
            font-size: 1.5rem;
            font-weight: bold;
            color: #fff;
        }

        .navbar .navbar-nav .nav-link {
            color: white;
            font-size: 1.1rem;
            padding: 12px 20px;
            transition: background-color 0.3s ease;
        }

        .navbar .navbar-nav .nav-link:hover {
            background-color: #f39c12;
            color: white;
        }

        .navbar-nav .nav-item {
            margin-right: 15px;
        }

        .navbar-nav .nav-item:last-child {
            margin-right: 0;
        }

        .form-inline .nav-item {
            margin-left: 10px;
        }

        /* Modal button và hiệu ứng cho các nút */
        .btn-primary, .btn-info, .btn-danger {
            transition: background-color 0.3s ease;
        }

        .btn-primary:hover, .btn-info:hover, .btn-danger:hover {
            background-color: #f39c12;
            color: white;
        }

        /* Cải tiến modal */
        .modal-content {
            border-radius: 8px;
        }

        .modal-header {
            background-color: #f39c12;
            color: white;
            padding: 15px;
            border-top-left-radius: 8px;
            border-top-right-radius: 8px;
        }

        .modal-header .close {
            color: white;
        }

        .modal-body {
            padding: 25px;
        }

        .modal-footer {
            padding: 15px;
        }

        .modal-body .md-form input, .modal-body .md-form textarea {
            border-radius: 8px;
        }

        /* Responsive thiết kế */
        @media (max-width: 768px) {
            .navbar .navbar-nav {
                flex-direction: column;
                align-items: center;
            }

            .navbar .navbar-nav .nav-link {
                font-size: 1rem;
                padding: 8px 16px;
            }

            .navbar .navbar-nav .nav-item {
                margin-bottom: 10px;
            }
        }

    </style>
</head>
<!-- nav bar -->
<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
    <a class="navbar-brand" href="/"><i class="fas fa-film"></i> CINEMA</a>

    <div class="collapse navbar-collapse" id="navb">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Trang chủ</a>
            </li>
            <c:choose>
                <c:when test="${sessionScope.jwtResponse eq null}">

                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="/tickets/history">Lịch sử mua vé</a>
                    </li>



                </c:otherwise>
            </c:choose>
            <li class="dropdown">
                <a href="#" class="toggle-dropdown">
                    <span>Thể loại</span>
                    <i class="bi bi-chevron-down"></i>
                </a>
                <ul class="dropdown-menu">
                    <li class="dropdown">
                        <a href="#" class="toggle-dropdown">
                            <span>Hành động</span>
                            <i class="bi bi-chevron-right"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/movie-details?movieId=2">VENOM: KÈO CUỐI</a></li>
                            <li><a href="/movie-details?movieId=4">MẬT MÃ ĐỎ</a></li>

                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="toggle-dropdown">
                            <span>Kinh dị</span>
                            <i class="bi bi-chevron-right"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/movie-details?movieId=5">CÁM</a></li>
                            <li><a href="/movie-details?movieId=1">AI OÁN TRONG VƯỜN XUÂN</a></li>
                            <li><a href="/movie-details?movieId=3">TEE YOD: QUỶ ĂN TẠNG PHẦN 2</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="toggle-dropdown">
                            <span>Tình cảm</span>
                            <i class="bi bi-chevron-right"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/movie-details?movieId=6">NGÀY XƯA CÓ MỘT CHUYỆN TÌNH</a></li>

                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="toggle-dropdown">
                            <span>Hoạt hình</span>
                            <i class="bi bi-chevron-right"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/movie-details?movieId=7">ROBOT HOANG DÃ</a></li>

                        </ul>
                    </li>
                </ul>
            </li>



        </ul>
        <div class="form-inline my-2 my-lg-0">
            <c:choose>
                <c:when test="${sessionScope.jwtResponse eq null}">
                    <li class="nav-item">
                        <a href="" class="btn btn-primary" data-toggle="modal" data-target="#modalLoginForm">Đăng nhập</a>
                    </li>
                    <li class="nav-item">
                        <a href="" class="btn btn-info" data-toggle="modal" data-target="#modalRegisterForm">Đăng ký</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a style="color: gold">${sessionScope.jwtResponse.name}</a>
                    </li>
                    <li class="nav-item">
                        <a href="/account/sign-out" class="btn btn-danger">Đăng xuất</a>
                    </li>
                </c:otherwise>
            </c:choose>


        </div>
    </div>
</nav>
<body>
<!-- login modal -->
<div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="/account/login" method="post">
                <div class="modal-header text-center">
                    <h4 class="modal-title w-100 font-weight-bold">Đăng nhập</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <div class="modal-body mx-3">
                    <c:choose>
                        <c:when test="${loginError eq null}">

                        </c:when>
                        <c:otherwise>
                            <p style="color: red"><i>${loginError}</i></p>
                            <br>
                        </c:otherwise>
                    </c:choose>
                    <div class="md-form mb-5">
                        <label data-error="wrong" data-success="right" for="defaultForm-email">Email</label>
                        <i class="fas fa-envelope prefix grey-text"></i>
                        <input name="username" type="text" id="defaultForm-email" class="form-control validate"
                               value="${un}"/>
                    </div>

                    <div class="md-form mb-4">
                        <label data-error="wrong" data-success="right" for="defaultForm-pass">Mật khẩu</label>
                        <i class="fas fa-lock prefix grey-text"></i>
                        <input name="password" type="password" id="defaultForm-pass" class="form-control validate"
                               value="${pw}"/>
                    </div>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary btn-block" >Đăng Nhập</button>
                </div>
            </form>
            <div class=" d-flex justify-content-center">
                Chưa có tải khoản?
            </div>
            <div class="modal-footer d-flex justify-content-center">
                <button id="btn-register" class="btn btn-info btn-block">Đăng Ký</button>
            </div>

        </div>
    </div>
</div>

<div class="text-center">

</div>
<!-- end of login modal -->

<!-- sign up modal -->
<div class="modal fade" id="modalRegisterForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form:form action="/account/register" method="post" modelAttribute="user">
                <div class="modal-header text-center">
                    <h4 class="modal-title w-100 font-weight-bold">Đăng ký</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body mx-3">
                    <div class="md-form mb-5">
                        <p><i style="color: red">${registerError}</i></p>
                        <label data-error="wrong" data-success="right" for="orangeForm-name">Họ và tên</label>
                        <i class="fas fa-user prefix grey-text"></i>
                        <form:input path="fullName" name="fullname" type="text" id="orangeForm-name"
                                    class="form-control validate" value="${fn}"/>
                        <form:errors path="fullName" cssClass="error" cssStyle="color: red"/>
                    </div>
                    <div class="md-form mb-5">
                        <label data-error="wrong" data-success="right" for="orangeForm-email">Email</label>
                        <i class="fas fa-envelope prefix grey-text"></i>
                        <form:input path="username" name="username" type="text" id="orangeForm-email"
                                    class="form-control validate" value="${un}"/>
                        <form:errors path="username" cssClass="error" cssStyle="color: red"/>
                    </div>

                    <div class="md-form mb-4">
                        <label data-error="wrong" data-success="right" for="orangeForm-pass">Mật khẩu</label>
                        <i class="fas fa-lock prefix grey-text"></i>
                        <form:input path="password" name="password" type="password" id="orangeForm-pass"
                                    class="form-control validate" value="${pw}"></form:input>
                        <form:errors path="password" cssClass="error" cssStyle="color: red"/>
                    </div>

                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <form:button class="btn btn-primary btn-block">Đăng ký</form:button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<!-- end of sign up modal -->

<script>
    $("#btn-register").on('click', function () {
        $("#modalLoginForm").modal("hide")
        $(".modal-backdrop").css("display","none")
        $("#modalRegisterForm").modal("show")
    })
</script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const dropdowns = document.querySelectorAll('.dropdown > a');

        dropdowns.forEach(function (dropdown) {
            dropdown.addEventListener('click', function (e) {
                e.preventDefault(); // Ngăn hành động mặc định

                // Đóng tất cả các menu khác
                dropdowns.forEach(d => d.parentElement.classList.remove('active'));

                // Mở menu con của dropdown hiện tại
                dropdown.parentElement.classList.toggle('active');
            });
        });
    });

</script>
</body>