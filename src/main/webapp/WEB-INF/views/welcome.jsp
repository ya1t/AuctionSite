<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

    
</head>
<body>
<jsp:include page="nav.jsp" />
    <h1>Welcome, ${sessionScope.loggedInUser.username}!</h1>
    <a href="/auth/logout">Logout</a>
</body>
</html>
