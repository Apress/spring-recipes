<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Book List</title>
</head>

<body>
<table border="1">
    <tr>
        <th>ISBN</th>
        <th>Book Name</th>
        <th>Author</th>
        <th>Publish Date</th>
    </tr>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>
                <a href="${flowExecutionUrl}&_eventId=select&isbn=${book.isbn}">
                        ${book.isbn}
                </a>
            </td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td><fmt:formatDate value="${book.publishDate}" pattern="yyyy-MM-dd"/></td>
        </tr>
    </c:forEach>
</table>
<a href="${flowExecutionUrl}&_eventId=newSearch">New Search</a>
</body>
</html>
