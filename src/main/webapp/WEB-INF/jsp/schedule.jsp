<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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

    <title>Lịch xem phim</title>
    <style>
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

    <form action="room-selection" method="post">
        <div class="text-center choose-movie-container">
                        <h1 class="choose-movie-title">
                            <span class="select-icon"></span> <!-- Biểu tượng chọn phim -->
                            <span class="title-text">Chọn lịch xem phim</span>
                            <span class="select-icon"></span> <!-- Biểu tượng chọn phim -->
                        </h1>
                        <div class="movie-explore-lights">
                            <span class="explore-light-left"></span>
                            <span class="explore-light-right"></span>
                        </div>
                    </div>
        <br>
        <p>Chọn ngày xem phim (yyyy-MM-dd)</p>
        <select id="listDate" class="form-control form-control-lg" name="startDate" >
            <c:forEach items="${listDates}" var="date">
                <option value="${date}">
                        ${date}
                </option>
            </c:forEach>
        </select>
        <br>
        <p>Chọn giờ xem phim (HH:mm)</p>
        <select id="listTimes" class="form-control form-control-lg" name="startTime">
            <c:forEach items="${listStartTimes}" var="startTime">
                <option value="${startTime}">
                        ${startTime}
                </option>
            </c:forEach>
        </select>
        <br><br>
        <input type="submit" class="btn btn-outline-danger btn-block">
    </form>
</div>
<br><br><br><br><br><br><br>
<jsp:include page="footer.jsp"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    function callAPI() {
        console.log("da goi duoc api!")
        $.ajax({
            url: "http://localhost:8080/api/schedule/start-times?movieId=${sessionScope.movieId}&branchId=${sessionScope.branchId}&startDate=" + $("#listDate").find(":selected").text().trim(),
            type: 'GET',
            headers: {"Authorization": "Bearer " +'${sessionScope.jwtResponse.accessToken}'},
            success: function (data) {
                console.log("data: "+data)
                $("#listTimes").html("");
                data.forEach(startTime => {
                    $("#listTimes").append("<option value=" + '"' + startTime + '"' + ">" + startTime + "</option>")
                });
            },
            error: function(error){
                alert(error)
            }
        })
    }
    $(document).ready(function() {
        $('#listDate').on('change', function() {
            // alert( this.value ); // or $(this).val()
            callAPI();
        });
    });
</script>

</body>

</html>