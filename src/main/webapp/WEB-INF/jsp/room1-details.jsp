<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Phòng Phim</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Phòng: ${room1.name}</h1>
    <h3>Phim: ${room1.movie1.name}</h3>
    <p>${room1.movie1.description}</p>

    <iframe width="100%" height="400" src="${room1.movie1.url}" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
    <h4>Thành viên tham gia:</h4>
    <ul>
        <c:forEach items="${room1.users}" var="user">
            <li>${user}</li>
        </c:forEach>
    </ul>
</div>
<div class="chat-container">
    <h3>Khung Chat</h3>
    <div id="chat-box" class="border p-3" style="height: 300px; overflow-y: scroll;">
        <!-- Tin nhắn sẽ được thêm tại đây -->
    </div>
    <form id="chat-form">
        <div class="form-group">
            <textarea id="chat-input" class="form-control" rows="2" placeholder="Nhập tin nhắn..."></textarea>
        </div>
        <button type="submit" class="btn btn-primary mt-2">Gửi</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/sockjs-client/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs/lib/stomp.min.js"></script>
<script>
    let stompClient = null;
    const roomId = ${room1.id};

    function connect() {
        const socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function () {
            stompClient.subscribe('/topic/room1/' + roomId, function (message) {
                showMessage(JSON.parse(message.body));
            });

            // Load lịch sử tin nhắn
            fetch(`/api/messages/${roomId}`)
                .then(response => response.json())
                .then(messages => {
                    messages.forEach(showMessage);
                });
        });
    }

    function sendMessage() {
        const content = document.getElementById('chat-input').value;
        const sender = 'User'; // Thay bằng thông tin user từ session

        if (content.trim()) {
            const message = { content, sender, room1Id: roomId };
            stompClient.send('/app/chat/' + roomId, {}, JSON.stringify(message));
            document.getElementById('chat-input').value = '';
        }
    }

    function showMessage(message) {
        const chatBox = document.getElementById('chat-box');
        const messageElement = document.createElement('div');
        messageElement.innerHTML = `<strong>${message.sender}:</strong> ${message.content}`;
        chatBox.appendChild(messageElement);
        chatBox.scrollTop = chatBox.scrollHeight;
    }

    document.getElementById('send-btn').addEventListener('click', sendMessage);

    connect();
</script>

</body>
</html>
