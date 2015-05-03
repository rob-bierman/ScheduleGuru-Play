function ajaxCall(info,location){
	xmlhttp.open("POST",location,true);
	xmlhttp.send(info);
}


function build1button(){
	window.location.href = "/build2";
}

function build2button(){
	var tr='',mwf='',temp;
	var sT = $('input[name=radioStart]:checked').val();
	var eT = $('input[name=radioEnd]:checked').val();
	var arr = $('input[name=days]:checked');
	for(var a=0; a<arr.length; a++){
		temp = arr[a]['value'];
		if(temp === 'T' || temp === 'R')
			tr += temp;
		else
			mwf += temp;	
	}
	localStorage.setItem("mwf",mwf);
	localStorage.setItem("tr",tr);
	localStorage.setItem("sT",sT);
	localStorage.setItem("eT",eT);
	var majorlist = document.getElementById("selectMajor");
	var unparsedMajor = majorlist.options[majorlist.selectedIndex].value;
	var openSplit = unparsedMajor.split("(");
	var closedSplit = openSplit[openSplit.length-1].split(")");
	var splitLen = closedSplit.length;
	var major = closedSplit[splitLen-2];
	localStorage.setItem("major",major);
	localStorage.setItem("majorClassesSelected",null);
	window.location.href = "/build3/"+major;
}
function build3button(){
	localStorage.setItem("majorClassesSelected",JSON.stringify(calendarCourseArray));
	var xmlhttp;
	if (window.XMLHttpRequest)
  		xmlhttp=new XMLHttpRequest();
  	else
  		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	xmlhttp.open("POST",window.location.origin+"/build4/getPreferences",false);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send("mwf="+localStorage.getItem("mwf")+"&tr="+localStorage.getItem("tr")+"&sT="+localStorage.getItem("sT")+"&eT="+localStorage.getItem("eT"));
	window.location.href = "/build4";
}

function build3backbutton(){
	window.location.href = "/build2";
}

function getMajor(){
	alert("in here");
	var xmlhttp;
	if (window.XMLHttpRequest)
  		xmlhttp=new XMLHttpRequest();
  	else
  		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	xmlhttp.open("POST",window.location.origin+"/build3/getMajor",false);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send("major="+localStorage.getItem("major"));
}

function returnIDHelper(arr){
	var ret = new Array;
	for(var a =0; a<arr.length; ++a){
		ret.push(arr[a].id);
	}
	return ret;
}

function build4button(){
	localStorage.setItem("majorClassesSelected",JSON.stringify(calendarCourseArray));
	window.location.href = "/build5";
}

function build4backbutton(){
	build2button();
}

function build5backbutton(){
	build3button();
}
function testCheckBoxes(checkBox){
	//checkBox ID is in the form tableID:[tID]:row:[row_num]
	//so the last element of splitID will be the table row
	//Need to subtract 1 from rowNum because of the header
	var checkBoxID = checkBox.id;
	var splitID = checkBoxID.split(" ");
	var dep = splitID[0];
	var num = splitID[1];
	var days = splitID[2];
	var start = splitID[3];
	var end = splitID[4];
	var classData = {'StartTime':start,'EndTime':end,'Department':dep,'Number':num,'Days':days};
	classData['ClassID'] = classData['Department']+classData['Number']+classData['StartTime']+classData['EndTime'];
	if(checkBox.checked){
		if(!addCalendarClass(classData)) checkBox.checked = false;
	}
	else{
		removeCalendarClass(classData['ClassID']);
	}
}
