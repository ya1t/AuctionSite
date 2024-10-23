<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Login</title>
    <script>
        function showAlert(message) {
            alert(message);
        }
    </script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

    
</head>
<body>
<jsp:include page="nav.jsp" />
    <h2>User Login</h2>
    <form action="/auth/login" method="post">
        <label>Username:</label><br>
        <input type="text" name="username" required><br>

        <label>Password:</label><br>
        <input type="password" name="password" required><br>

        <button type="submit">Login</button>
    </form>

  
    <c:if test="${not empty errorMessage}">
        <script>
            showAlert("${errorMessage}");
        </script>
    </c:if>
</body>
</html>
