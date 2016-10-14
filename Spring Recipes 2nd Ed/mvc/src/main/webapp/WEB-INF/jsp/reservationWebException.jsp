<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Reservation Web Exception</title>
</head>

<body>
<p>The following error has occurred:</p> 
<ul>
<li>Message: <c:out value="${exception.message}"/></li>
<li>Date: <c:out value="${exception.date}"/> </li>
</ul>
<p>Please contact our administrator for details</p>
<p>As a reference you can add the following information:</p> 
<textarea rows="15" cols="80">
<c:out value="${exception.stack}"/>
</textarea>
</body>
</html>
