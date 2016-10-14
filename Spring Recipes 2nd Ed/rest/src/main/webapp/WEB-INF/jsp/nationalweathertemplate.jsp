<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>National weather feed by The Weather Channel</title>
</head>

<body>
<h2>National weather feed by The Weather Channel</h2>
<table>
  <c:forEach items="${feedcontent}" var="item">
  <tr>
    <td><a href="${item.link}"> ${item.title} </a> </td>
  </tr>
  </c:forEach>
</table>
<h4> Information obtained through ${feedtype} ${feedversion} </h4> 
</body>
</html>
