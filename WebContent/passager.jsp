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
	<table name="conducteursProche" id="conducteursProche" style="width:100%">
		 <tr>
	    	<th>email</th>
	     	<th>Score si Passager</th> 
	     	<th>Score si Conducteurs</th> 
	     	<th>Km vers point rencontre</th> 
	     	<th>% parcours</th> 
	    	
	   	</tr>
		<c:forEach items="${sessionScope.listeConducteursProche}" var="map" >
			<tr class="impair" >
				<td> ${ map.key.getEmail() } </td>
				<td> ${ map.value }	</td>

			</tr>	
		</c:forEach>
	</table>
	
	<form method="post" action="PassagerCovoit">
		<fieldset>
			<a href="<c:url value="prefPassager.jsp"/>">preference passager</a>
			 </fieldset>
    </form>
</body>
</html>