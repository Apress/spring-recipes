<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<portlet:actionURL var="formAction" />

<form:form method="POST" action="${formAction}" commandName="bookingForm">
<table>
  <tr>
    <td>Tourist</td>
    <td><form:input path="tourist" /></td>
    <td><form:errors path="tourist" cssClass="portlet-msg-error" /></td>
  </tr>
  <tr>
    <td>Phone</td>
    <td><form:input path="phone" /></td>
    <td><form:errors path="phone" cssClass="portlet-msg-error" /></td>
  </tr>
  <tr>
    <td>Origin</td>
    <td><form:select path="origin" items="${locations}" /></td>
    <td><form:errors path="origin" cssClass="portlet-msg-error" /></td>
  </tr>
  <tr>
    <td>Destination</td>
    <td><form:select path="destination" items="${locations}" /></td>
    <td><form:errors path="destination" cssClass="portlet-msg-error" /></td>
  </tr>
  <tr>
    <td>Departure Date</td>
    <td><form:input path="departureDate" /></td>
    <td><form:errors path="departureDate" cssClass="portlet-msg-error" /></td>
  </tr>
  <tr>
    <td>Return Date</td>
    <td><form:input path="returnDate" /></td>
    <td><form:errors path="returnDate" cssClass="portlet-msg-error" /></td>
  </tr>
  <tr>
    <td colspan="3"><input type="submit" /></td>
  </tr>
</table>
</form:form>
