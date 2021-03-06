<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<title>Message List <security:authentication property="name" />
</title>
</head>

<body>
<h2>Welcome! <security:authentication property="name" /></h2>

<security:authentication property="authorities" var="authorities" />
<ul>
<c:forEach items="${authorities}" var="authority">
  <li>${authority.authority}</li>
</c:forEach>
</ul>
<hr />

<a href="<c:url value="/login.jsp" />">Login</a> |
<a href="<c:url value="/j_spring_security_logout" />">Logout</a>

<c:forEach items="${messages}" var="message">
<table>
  <tr>
    <td>Author</td>
    <td>${message.author}</td>
  </tr>
  <tr>
    <td>Title</td>
    <td>${message.title}</td>
  </tr>
  <tr>
    <td>Body</td>
    <td>${message.body}</td>
  </tr>
  <tr>
    <td colspan="2">
      <a href="messageDelete?messageId=${message.id}">Delete</a>
    </td>
  </tr>
</table>
<hr />
</c:forEach>
<a href="messagePost">Post</a>
</body>
</html>