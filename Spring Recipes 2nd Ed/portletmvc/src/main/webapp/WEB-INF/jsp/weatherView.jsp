<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<table border="1">
  <tr>
    <th>City</th>
    <th>Temperature</th>
  </tr>
  <c:forEach items="${temperatures}" var="temperature">
  <tr>
    <td>${temperature.key}</td>
    <td>${temperature.value}</td>
  </tr>
  </c:forEach>
</table>
