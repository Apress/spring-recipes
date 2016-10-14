<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>

<form method="post" action="<portlet:actionURL />">
  Time Zone
  <input type="text" name="timeZone" value="${timeZone}" />
  <input type="submit" value="Modify" />
</form>
