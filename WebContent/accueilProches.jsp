<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	${ sessionScope.listeUsersProche.size() }  utilisateurs habitent près de chez vous
	<br>
	${ sessionScope.listeConducteursPossibles.size() } conducteurs passe a moins de 5 km de l'adresse indiquée
	<br>
	${ sessionScope.listePassagersProche.size() } sont près de votre route pour le travail
	<br>
	<br>
	Pour obtenir plus d'infos sur ces utilisateurs et les contacter,
	<br>
	 merci de cliquer sur ici :
	<a href="<c:url value="/Register"/>">Créer votre compte</a>
	<br>
	<br>
	<a href="<c:url value="index.jsp"/>">Retour a la page d'accueil</a>
</body>
</html>