<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Member List</title>
<style>
.error {
  color: #ff0000;
  font-weight: bold;
}
</style>
</head>

<body>
<form:form method="post" modelAttribute="member">
<form:errors path="*" cssClass="error" />
<table>
<tr>
<td>Name <form:input path="name"/></td>
<td>Phone <form:input path="phone"/></td>
<td>Email <form:input path="email"/></td>
<td><input type="submit" /></td>
</tr>
</table>
</form:form>
<table border="1">
  <tr>
    <th>Name</th>
    <th>Phone</th>
    <th>Email</th>
    <th></th>
  </tr>
  <c:forEach items="${guests}" var="guest">
  <tr>
    <td>${guest.name}</td>
    <td>${guest.phone}</td>
    <td>${guest.email}</td>
    <td><a href="remove?memberName=${guest.name}">Remove</a></td>
  </tr>
  </c:forEach>
</table>
</body>
</html>
