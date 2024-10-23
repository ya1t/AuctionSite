<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Alert</title>
    <script>
        window.onload = function() {
            alert("The auction is starting! Only participants will be notified.");
        };
    </script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

    
</head>
<body>
    <h2>Auction Participants</h2>
    <ul>
        <c:forEach var="participant" items="${participants}">
            <li>${participant}</li>
        </c:forEach>
    </ul>
</body>
</html>
