<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh Toán</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <style type="text/css">
        th {
            padding: 0px 20px 5px 0px;
        }
        .choose-movie-container {
                    position: relative;
                    margin-top: 30px;
                    margin-bottom: 30px;
                }

                .choose-movie-title {
                    font-family: 'Roboto', sans-serif;
                    font-size: 2.5em;
                    font-weight: bold;
                    color: #FADA5E;
                    text-transform: uppercase;
                    display: inline-flex;
                    align-items: center;
                    justify-content: center;
                    position: relative;
                    text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.8);
                }

                .title-text {
                    padding: 0 20px;
                    z-index: 1;
                }

                /* Biểu tượng chọn phim */
                .select-icon {
                    font-size: 2em;
                    color: #f39c12; /* Màu vàng sáng cho sự thu hút */
                    margin: 0 15px;
                    animation: rotate 3s infinite linear;
                }

                @keyframes rotate {
                    0% {
                        transform: rotate(0deg);
                    }
                    100% {
                        transform: rotate(360deg);
                    }
                }

                /* Ánh sáng thư viện phim */
                .movie-explore-lights {
                    position: absolute;
                    top: 10px;
                    left: 50%;
                    transform: translateX(-50%);
                    display: flex;
                    justify-content: space-between;
                    width: 70%;
                    opacity: 0.6;
                }

                .explore-light-left, .explore-light-right {
                    position: relative;
                    width: 40%;
                    height: 10px;
                    background-color: #f39c12;
                    border-radius: 10px;
                }

                .explore-light-left:before, .explore-light-right:before {
                    content: '';
                    position: absolute;
                    top: -5px;
                    left: 50%;
                    width: 10px;
                    height: 10px;
                    border-radius: 50%;
                    background-color: #f39c12;
                    animation: blink 1s infinite alternate;
                }

                @keyframes blink {
                    0% {
                        opacity: 0.7;
                    }
                    100% {
                        opacity: 1;
                    }
                }

                /* Responsive */
                @media (max-width: 768px) {
                    .choose-movie-title {
                        font-size: 2em;
                    }
                    .select-icon {
                        font-size: 1.8em;
                    }
                    .explore-light-left, .explore-light-right {
                        width: 35%;
                    }
                }
    </style>
</head>

<body>
<!-- nav bar -->
<jsp:include page="header.jsp"/>
<!-- end of navbar -->

<br><br><br>
<div class="container">
        <div class="text-center choose-movie-container">
                        <h1 class="choose-movie-title">
                            <span class="select-icon"></span> <!-- Biểu tượng chọn phim -->
                            <span class="title-text">Thanh toán Hóa đơn</span>
                            <span class="select-icon"></span> <!-- Biểu tượng chọn phim -->
                        </h1>
                        <div class="movie-explore-lights">
                            <span class="explore-light-left"></span>
                            <span class="explore-light-right"></span>
                        </div>
                    </div>
        <br>
        <div style="display:flex">
            <table>
                <tr>
                    <th><b>Tên Phim: </b></th>
                    <th>${sessionScope.schedule.movie.name}</th>
                </tr>
                <tr>
                    <th><b>Tên Chi Nhánh:</b></th>
                    <th>${sessionScope.schedule.branch.name}</th>
                </tr>
                <tr>
                    <th><b>Giờ Chiếu:</b></th>
                    <th>${sessionScope.schedule.startTime}</th>
                </tr>
                <tr>
                    <th><b>Ngày Chiếu:</b></th>
                    <th>${formattedDate}</th>
                </tr>
                <tr>
                    <th><b>Tên Phòng:</b></th>
                    <th>${sessionScope.schedule.room.name}</th>
                </tr>
                <tr>
                    <th><b>Số Vé:</b></th>
                    <th>${numberOfSelectedSeats}</th>
                </tr>
                <tr>
                    <th><b>Tiền Vé Đơn:</b></th>
                    <th>${sessionScope.schedule.price}</th>
                </tr>
                <tr>
                    <th><b>Tổng:</b></th>
                    <th>${totalAmount}</th>
                </tr>
            </table>
            <div style="margin-left:50px">
                <img src="${sessionScope.schedule.movie.smallImageURl}" alt="">
            </div>
            <div style="margin-left:50px; height: 300px;">
                <img style="height: 100%;" src="${sessionScope.schedule.branch.imgURL}" alt="">
            </div>
        </div>
        <br>
        <a href="bill" class="btn btn-outline-danger btn-block">Thanh Toán</a>
</div>
<br>
<br>
<br>
<jsp:include page="footer.jsp"/>
</body>

</html>