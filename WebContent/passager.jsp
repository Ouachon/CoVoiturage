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
	${conducteursProche.size()}  utilisateurs connect�s
	${sessionScope.conducteursProche.size()}  utilisateurs connect�s
	
	<br><br>
	<table name="condProche" id="condProche" style="width:100%">
		 <tr>
	    	<th>email</th>
	     	<th>Score si Passager</th> 
	     	<th>Score si Conducteurs</th> 
	     	<th>Km vers point rencontre</th> 
	     	<th>% parcours</th> 
	    	
	   	</tr>
		<c:forEach items="${conducteursProche}" var="map" >
			<tr class="impair" >
				<td> ${ map.key.getEmail() } </td>
				<td> ${ map.value.getScoreUser1ConduitParUser2() } </td>
				<td> ${ map.value.getScoreUser1ConduitUser2() }</td>
				<td> ${ map.value.getEloignementPointRencontre() }	</td>
				<td> ${ map.value.getPourcUser1ConduitParUser2() } </td>
				
			
			</tr>	
		</c:forEach>
	</table>
	
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