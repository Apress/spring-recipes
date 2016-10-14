<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Book Criteria</title>
</head>

<body>
<form:form commandName="bookCriteria">
    <table>
        <tr>
            <td>Keyword</td>
            <td><form:input path="keyword"/></td>
        </tr>
        <tr>
            <td>Author</td>
            <td><form:input path="author"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" name="_eventId_search" value="Search"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
