<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

    
</head>
<body>
<jsp:include page="nav.jsp" />
    <h2>User Registration</h2>
    <form action="/auth/register" method="post">
        <label>Username:</label><br>
        <input type="text" name="username"><br>
        
        <label>Password:</label><br>
        <input type="password" name="password"><br>

        <label>Email:</label><br>
        <input type="email" name="email"><br>

        <button type="submit">Register</button>
    </form>
</body>
</html>
