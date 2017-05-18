/**
 * 
 */

/* gestion d'un formulaire */

function testOnChangeAd() {
	alert('On change adresse compte');
	document.getElementById("CoordGPS").value = "1968";
}

function validForm(unObj)  {
	
	
	if(unObj.pwd1.value!= unObj.pwd2.value)  {
		alert("Vous devez avoir 2 mots de passe identiques");
		unObj.pwd1.focus();
		unObj.pwd1.select()
		return false;
		
	}
	
	
	return true;
}