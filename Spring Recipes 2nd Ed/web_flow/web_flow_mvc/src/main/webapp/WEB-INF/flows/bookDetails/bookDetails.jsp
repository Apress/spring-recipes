<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Book Details</title>
</head>

<body>
<table border="1">
    <tr>
        <td>ISBN</td>
        <td>${book.isbn}</td>
    </tr>
    <tr>
        <td>Book Name</td>
        <td>${book.name}</td>
    </tr>
    <tr>
        <td>Author</td>
        <td>${book.author}</td>
    </tr>
    <tr>
        <td>Publish Date</td>
        <td><fmt:formatDate value="${book.publishDate}" pattern="yyyy-MM-dd"/></td>
    </tr>
</table>
<a href="${flowExecutionUrl}&_eventId=newSearch">New Search</a>
</body>
</html>
