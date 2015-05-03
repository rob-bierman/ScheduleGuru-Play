var userEmail = "none";
var userID = "";
var userFirst = "";
var userLast = "";
var userMajor = "";
var userYear = "";

localStorage.setItem("userEmail","none");
localStorage.setItem("userID","");
localStorage.setItem("userFirst","");
localStorage.setItem("userLast","");
localStorage.setItem("userMajor","");
localStorage.setItem("userYear","");

getCurrentUser();

localStorage.setItem("userEmail",userEmail);
localStorage.setItem("userID",userID);
localStorage.setItem("userFirst",userFirst);
localStorage.setItem("userLast",userLast);
localStorage.setItem("userMajor",userMajor);
localStorage.setItem("userYear",userYear);

if(userEmail != "none"){
	window.location.href = "/welcome";
}
else{
 	window.location.href = "/login";
}
function getCurrentUser(){
	var data = "is this writing or what?";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if (xmlhttp.readyState==4 && xmlhttp.status==200){
			var toSplit = xmlhttp.responseText;
			var split = toSplit.split(",");
			userEmail = split[0];
			userID = split[1];
			userMajor = split[2];
			userYear = split[3];
			userFirst = split[4];
			userLast = split[5];
		}
	}
	xmlhttp.open("POST",window.location.origin+"/login/currentUser",false);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   	xmlhttp.send(data);
}
