<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Borrow Review</title>
</head>

<body>
<form method="POST">
    <table>
        <tr>
            <td>ISBN</td>
            <td>${borrowingRecord.isbn}</td>
        </tr>
        <tr>
            <td>Borrow Date</td>
            <td>
                <fmt:formatDate value="${borrowingRecord.borrowDate}" pattern="yyyy-MM-dd"/>
            </td>
        </tr>
        <tr>
            <td>Return Date</td>
            <td>
                <fmt:formatDate value="${borrowingRecord.returnDate}" pattern="yyyy-MM-dd"/>
            </td>
        </tr>
        <tr>
            <td>Reader</td>
            <td>${borrowingRecord.reader}</td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" name="_eventId_confirm" value="Confirm"/>
                <input type="submit" name="_eventId_revise" value="Revise"/>
                <input type="submit" name="_eventId_cancel" value="Cancel"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
