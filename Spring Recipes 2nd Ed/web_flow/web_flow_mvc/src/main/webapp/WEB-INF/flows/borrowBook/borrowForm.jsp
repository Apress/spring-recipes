<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Borrow Form</title>
</head>

<body>
<form:form commandName="borrowingRecord">
    <table>
        <tr>
            <td>ISBN</td>
            <td><form:input path="isbn"/></td>
        </tr>
        <tr>
            <td>Borrow Date</td>
            <td><form:input path="borrowDate"/></td>
        </tr>
        <tr>
            <td>Return Date</td>
            <td><form:input path="returnDate"/></td>
        </tr>
        <tr>
            <td>Reader</td>
            <td><form:input path="reader"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" name="_eventId_proceed" value="Proceed"/>
                <input type="submit" name="_eventId_cancel" value="Cancel"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
