<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        nav {
            background-color: #333;
            overflow: hidden;
        }

        nav a {
            float: left;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        nav a:hover {
            background-color: #ddd;
            color: black;
        }

        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .content {
            padding: 20px;
        }
        
        #welcome {
        	color: yellow;
        }
    </style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

    
</head>
<body>
    <nav>
        <a href="/">Home</a>
    <c:choose>
        <c:when test="${not empty sessionScope.loggedInUser}">
            <a href="/auth/logout">Logout</a>
            <span id="welcome">Welcome, ${sessionScope.loggedInUser.username}!</span>
        </c:when>
        <c:otherwise>
            <a href="/auth/login">Login</a>
            <a href="/auth/register">Register</a>
        </c:otherwise>
    </c:choose>
    <a href="/items">Items List</a>
    <a href="/items/add">Add Items</a>
    </nav>
</body>
</html>
