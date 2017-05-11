/**
 * 
 */

/* gestion d'un formulaire */

function validForm(unObj)  {
	
	
	if(unObj.pwd1.value!= unObj.pwd2.value)  {
		alert("Vous devez avoir 2 mots de passe identiques");
		unObj.pwd1.focus();
		unObj.pwd1.select()
		return false;
		
	}
	
	
	return true;
}