<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Conducteur</title>
</head>
<body>
	Bienvenue ${formLogin['nomUser']}
	<br><br>
	ON AFFICHE ICI LA LISTE DES PASSAGERS 
	AYANT essayé de me contacter et le status pour chacun (accepté refusé)
	<form method="post" action="ConducteurCovoit">
		<fieldset>
			 <a
				href="<c:url value="prefConducteur.jsp"/>">preference Conducteur</a>
		</fieldset>
	</form>
</body>
</html>