<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<title>Insert title here</title>
</head>
<body>
	<ul id="menu">
		<li> <a href="<c:url value="WelcomeServlet"/>">Connexion a mon espace</a> </li> 
		<li> <a href="<c:url value="/Register"/>">Cr�er un nouvel utilisateur</a></li> 
		<li> <a href="<c:url value="/Users"/>">Afficher liste des utilisateurs</a></li> 
		<li> <a href="<c:url value="index.jsp"/>">Accueil</a>	
	</ul>

</body>
</html>