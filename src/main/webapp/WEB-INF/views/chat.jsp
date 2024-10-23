<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    com.AuctionSite.model.User user = (com.AuctionSite.model.User) session.getAttribute("loggedInUser");
    if (user == null) {
        response.sendRedirect("/auth/login");
        return;
    }
    String username = user.getUsername();  
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Auction Chat Room</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>


    <style>
        body { font-family: Arial, sans-serif; }
        #chat { max-width: 600px; margin: auto; }
        #messages { border: 1px solid #ccc; padding: 10px; height: 300px; overflow-y: scroll; }
        #input { margin-top: 10px; }
    </style>
</head>
<body>
    <div id="chat">
        <h2>Auction Chat Room</h2>
        <h3>Welcome, <%= username %>!</h3>  
        <div id="messages"></div>
        <div id="input">
            <input type="text" id="messageInput" placeholder="Type your message..." />
            <button onclick="sendMessage()">Send</button>
        </div>
    </div>

    <script>
        let stompClient = null;

        function connect() {
            const socket = new SockJS('/ws');
            stompClient = Stomp.over(socket);

            stompClient.connect({}, function (frame) {
                console.log('Connected to WebSocket: ' + frame);

                stompClient.subscribe('/topic/alert', function (message) {
                    console.log('Received auction alert:', message.body);
                    const itemId = message.body;
                    alert('Auction Start!');
                    window.location.href = `/rooms/${itemId}`;
                });
                stompClient.subscribe('/user/topic/room', function (message) {
                    const itemId = message.body;
                    alert(`Auction Start! Redirecting to room ${itemId}`);
                    window.location.href = `/rooms/${itemId}`;
                });

            }, function (error) {
                console.error('WebSocket connection error:', error);
            });
        }

        function sendMessage() {
            const input = document.getElementById('messageInput');
            const message = input.value;
            stompClient.send("/app/chat", {}, JSON.stringify({'username': '<%= username %>', 'message': message}));
            input.value = '';
        }

        connect();
    </script>
</body>
</html>
