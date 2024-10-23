<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Items List</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>


    <style>
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        img {
            width: 100px;
            height: auto;
        }
        .join-button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
        }
        .join-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<jsp:include page="nav.jsp" />
    <h2 style="text-align: center;">Items List</h2>

    <div id="itemTableContainer">
        <table>
            <thead>
                <tr>
                    <th>Item</th>
                    <th>Image</th>
                    <th>Max Participants</th>
                    <th>Current Participants</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody id="itemTableBody">
                <c:forEach var="item" items="${items}">
                    <tr>
                        <td>${item.name}</td>
                        <td><img src="${item.imagePath}" alt="${item.name}"></td>
                        <td>${item.maxParticipants}</td>
                        <td>${item.participants.size()} / ${item.maxParticipants}</td>
                        <td>
                            <form action="/items/participate" method="post">
                                <input type="hidden" name="itemId" value="${item.id}"/>
                                <input type="hidden" name="username" value="${sessionScope.loggedInUser.username}"/>
                                <button type="submit" class="join-button">Join</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script>
        let stompClient = null;

        function connect() {
            const socket = new SockJS('/ws');
            stompClient = Stomp.over(socket);

            stompClient.connect({}, function () {
                console.log("WebSocket 연결 성공");

              
                stompClient.subscribe('/topic/items', function (message) {
                    console.log("아이템 갱신 메시지 수신:", message.body);
                    alert(message.body); 
                    refreshItemList();
                });

                stompClient.subscribe('/topic/room/', function (message) {
                    console.log("채팅방 메시지 수신:", message.body);
                    alert(message.body);
                    const itemId = message.body.split(' ')[0];
                    window.location.href = `/rooms/${itemId}`;
                });
            }, function (error) {
                console.error("WebSocket 연결 실패:", error);
            });
        }

        function refreshItemList() {
            fetch('/items')
                .then(response => response.text())
                .then(html => {
                    const parser = new DOMParser();
                    const doc = parser.parseFromString(html, 'text/html');
                    const newTableBody = doc.getElementById('itemTableBody');
                    document.getElementById('itemTableBody').innerHTML = newTableBody.innerHTML;
                })
                .catch(error => console.error('Error refreshing item list:', error));
        }

        connect();
    </script>
</body>
</html>
