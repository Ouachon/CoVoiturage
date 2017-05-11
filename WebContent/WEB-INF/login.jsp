<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mon espace</title>
<link type="text/css" rel="stylesheet" href="styles.css" />
</head>
<body>
Include MENU
    
    Demander login mot de passe et type user pour revenir sur cet page
    si login correct
    
    Afficher la liste des passagers interéssé si conducteur
    
    Ou la liste des conducteurs passant pres de chez moi si passager
    
    Voir s'il faut faire 2 pages différentes car pas grand chose en commun
	<c:import url="/WEB-INF/menu/menu.jsp" />
	<br>
	<c:import url="/WEB-INF/login/form.jsp" />
	<br />
</body>
</html>