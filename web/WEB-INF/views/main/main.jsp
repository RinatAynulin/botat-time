<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <title>Is it botat time?</title>
</head>
<body>
    <c:forEach var="forecast" items="${forecasts}">
        ${forecast}
        <c:out value="     "/>
    </c:forEach>
</body>
</html>
