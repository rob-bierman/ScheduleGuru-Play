/**
 * 
 */

var currentUser = "none";

function reroute(isMatch) {
	if(isMatch == true) {
		alert("Successfully updated User Information");
		window.location.href = "/updateUser";
	} else {
		alert("Passwords do not match, please re-enter them.");
		window.location.href = "/updateUser";
	}
}

function updateLocalStorage(updateTo){
    var split = updateTo.split(",");
	localStorage.setItem("userID",split[0]);
	localStorage.setItem("userEmail",split[1]);
	localStorage.setItem("userFirst",split[2]);
	localStorage.setItem("userLast",split[3]);
}

function getCurrentUser(){
	var data = "is this writing or what?";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if (xmlhttp.readyState==4 && xmlhttp.status==200){
			currentUser = xmlhttp.responseText;
		}
	}
	xmlhttp.open("POST",window.location.origin+"/login/currentUser",false);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   	xmlhttp.send(data);
}
