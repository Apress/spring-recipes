<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<html>
<head>
    <title>City Distance</title>
</head>

<body>
<f:view>
    <h:form>
        <h:panelGrid columns="2">
            <h:outputLabel for="srcCity">Source City</h:outputLabel>
            <h:inputText id="srcCity" value="#{distanceBean.srcCity}"/>
            <h:outputLabel for="destCity">Destination City</h:outputLabel>
            <h:inputText id="destCity" value="#{distanceBean.destCity}"/>
            <h:outputLabel>Distance</h:outputLabel>
            <h:outputText value="#{distanceBean.distance}"/>
            <h:commandButton value="Find" action="#{distanceBean.find}"/>
        </h:panelGrid>
    </h:form>
</f:view>
</body>
</html> 
