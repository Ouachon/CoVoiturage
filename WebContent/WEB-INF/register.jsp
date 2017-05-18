<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="styesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js">
<link type="text/css" rel="stylesheet" href="styles.css" />
<title>Insert title here</title>
</head>
<body>
	<ul id="menu">
            <li> <a href="<c:url value="index.jsp"/>">Accueil</a>   
    </ul>
    <br>
	<c:import url="/WEB-INF/user/form.jsp" />
	<br/>	
	<c:if test="${ !errorStatus }">
	<c:import url="/WEB-INF/user/card.jsp" />
	</c:if>
	

	
	

</body>
</html>