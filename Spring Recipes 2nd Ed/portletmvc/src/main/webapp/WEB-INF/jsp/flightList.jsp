<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>

<table border="1">
  <tr>
    <th>Flight Number</th>
    <th>Origin</th>
    <th>Destination</th>
    <th>Departure Time</th>
  </tr>
  <c:forEach items="${flights}" var="flight">
  <tr>
    <td>
      <portlet:renderURL var="detailUrl">
        <portlet:param name="action" value="flightDetail" />
        <portlet:param name="flightNo" value="${flight.number}" />
      </portlet:renderURL>
      <a href="${detailUrl}">${flight.number}</a>
    </td>
    <td>${flight.origin}</td>
    <td>${flight.destination}</td>
    <td><fmt:formatDate value="${flight.departureTime}"
            pattern="yyyy-MM-dd HH:mm" timeZone="${timeZone}" /></td>
  </tr>
  </c:forEach>
</table>
