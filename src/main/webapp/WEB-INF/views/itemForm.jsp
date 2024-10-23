<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Item</title>
</head>
<body>
<jsp:include page="nav.jsp" />
    <h2>Add New Item</h2>

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <form action="/items/add" method="post" enctype="multipart/form-data">
        <label>Item Name:</label><br>
        <input type="text" name="name" required><br>

        <label>Image:</label><br>
        <input type="file" name="image" required><br>

        <label>Max Participants:</label><br>
        <input type="number" name="maxParticipants" required><br>

        <button type="submit">Add Item</button>
    </form>

</body>
</html>
