<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<html>
<head>
    <title>City Distance</title>
</head>

<body>
<html:form method="POST" action="/distance.do">
    <table>
        <tr>
            <td>Source City</td>
            <td><html:text property="srcCity"/></td>
        </tr>
        <tr>
            <td>Destination City</td>
            <td><html:text property="destCity"/></td>
        </tr>
        <tr>
            <td>Distance</td>
            <td>${distance}</td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Find"/></td>
        </tr>
    </table>
</html:form>
</body>
</html> 
