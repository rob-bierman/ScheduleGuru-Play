function loadAccountSettings() {
	document.getElementById("newEmail").value = localStorage.getItem("userEmail");
	document.getElementById("newFirst").value = localStorage.getItem("userFirst");
	document.getElementById("newLast").value = localStorage.getItem("userLast");
	document.getElementById("userID").value = localStorage.getItem("userID");
}
