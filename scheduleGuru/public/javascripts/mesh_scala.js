var javascriptArr = new Array;
var javascriptString = "";

function setString(str){
	javascriptString = str;
}

function save(){
	var data = "is this writting or what?";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if (xmlhttp.readyState==4 && xmlhttp.status==200){
			javascriptString = xmlhttp.responseText;
			alert(javascriptString)
		}
	}
	xmlhttp.open("POST",window.location.origin+"/giveBackString",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   	xmlhttp.send(data);
}

function bleh(){
	javascriptString = 'woot';
	alert('yeah got here');
}
