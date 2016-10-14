<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>

<table>
  <tr>
    <td>Flight Number</td>
    <td>${flight.number}</td>
  </tr>
  <tr>
    <td>Origin</td>
    <td>${flight.origin}</td>
  </tr>
  <tr>
    <td>Destination</td>
    <td>${flight.destination}</td>
  </tr>
  <tr>
    <td>Departure Time</td>
    <td><fmt:formatDate value="${flight.departureTime}"
            pattern="yyyy-MM-dd HH:mm" timeZone="${timeZone}" /></td>
  </tr>
  <tr>
    <td colspan="2">
      <a href="<portlet:renderURL portletMode="view" />">Return</a>
    </td>
  </tr>
</table>
