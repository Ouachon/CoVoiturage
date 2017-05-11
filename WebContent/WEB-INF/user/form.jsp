<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="panel panel-default"> 
	    <div class="panel-heading">
	    <b> Inscription </b>
	    <br/>
		Formulaire d'inscription , les champs <span class="requis">(*)</span> sont requis:
		</div>
		
		<form class="form-group" onsubmit="return validForm(this)" method="POST" action="DispatchServlet" >
					<label for="email">Adresse email: <span class="requis">*</span></label>
					<input class="form-control" type="text" name="email" id="email" value="${form['email']}" placeholder="email" required="required" />
					<span class="error">${errors['email']}</span>
					</br>
					
					
					
					<label for="pwd1">Mot de passe <span class="requis">*</span></label>
					<input class="form-control" type="password" name="pwd1" id="pwd1" value="" placeholder="Mot de passe" required="required" />
					<span class="error">${errors['pwd1']}</span>
					</br>
					
					
					<label for="pwd1">Confirmation mot de passe <span class="requis">*</span></label>
					<input class="form-control" type="password" name="pwd2" id="pwd2" value="" placeholder="confirmation" required="required" />
					<span class="error">${errors['pwd2']}</span>
					</br>
					
					<label for="name">Nom</label>
					<input class="form-control" type="text" name="name" id="name" value="${form['name']}" />
					<span class="error">${errors['name']}</span>
					</br>
					
					
					<input class="btn-danger" type="submit" value="Enregistrement" />
						
		
		</form>
		<p class="info">${actionMessage}</p>

	</div>

</body>
</html>