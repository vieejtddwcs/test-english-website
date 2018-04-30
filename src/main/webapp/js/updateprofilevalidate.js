function validateForm() {
	var spanNotice = document.getElementById("notice");
	var error = "";
	
	var inputName = document.getElementById("name");
	var name = inputName.value;
	
	if (name.replace(/\s/g, "") == "") {
		error = "Vui lòng nhập họ và tên.";
		inputName.focus();
	}
	else if (name.length > 50) {
		error = "Họ và tên không được dài quá 50 kí tự.";
		inputName.focus();
	}
	
	if (error == "")
		return true;
	else {
		spanNotice.innerHTML = error;
		return false;
	}
	
}