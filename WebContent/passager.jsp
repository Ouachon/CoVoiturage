<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Passager</title>
</head>
<body>
	Bienvenue ${formLogin['nomUser']}
	<br><br>
	ON AFFICHE ICI LA LISTE DES CONDUCTEURS PASSANT PRES de CE PASSAGER
	et trié par préférence,
	(et une carte java script)
	<form method="post" action="PassagerCovoit">
	<input type="text" name="userCourant" id="userCourant" value="${formLogin['email']}"  style="width: 300px;">
		<fieldset>
			<a href="<c:url value="prefPassager.jsp"/>">preference passager</a>
		</fieldset>
		
		${ sessionScope.listeDesUsers.size() }  utilisateurs au total </br>
		<a><input type="submit" class="btn btn-primary" value="Supprimer le compte" name ="suppression">
			 
    </form>
</body>
</html>