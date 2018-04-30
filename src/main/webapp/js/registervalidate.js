function validateForm() {
	var spanNotice = document.getElementById("notice");
	var error = "";
	
	var inputEmail = document.getElementById("email");
	var inputPassword = document.getElementById("password");
	var inputRePassword = document.getElementById("rePassword");
	var inputName = document.getElementById("name");
	
	var email = inputEmail.value;
	var password = inputPassword.value.replace(/\s/g, "");
	var rePassword = inputRePassword.value.replace(/\s/g, "");
	var name = inputName.value;
	
	if (!isEmailValid(email)) {
		error = "Vui lòng nhập email.";
		inputEmail.focus();
	}
	else if (email.length > 50) {
		error = "Email không được dài quá 50 kí tự.";
		inputEmail.focus();
	}
	else if (password.length < 6) {
		error = "Mật khẩu phải dài tối thiểu 6 kí tự.";
		inputPassword.focus();
	}
	else if (rePassword == "") {
		error = "Vui lòng nhập lại mật khẩu.";
		inputRePassword.focus();
	}
	else if (password != rePassword) {
		error = "Mật khẩu không trùng khớp.";
		inputRePassword.focus();
	}
	else if (name.replace(/\s/g, "") == "") {
		error = "Vui lòng nhập họ và tên.";
		inputName.focus();
	}
	else if (name.length > 50) {
		error = "Họ và tên không được dài quá 50 kí tự.";
		inputName.focus();
	}
	
	function isEmailValid(email) { 
		var regex = /(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))/;
		return regex.test(email);
	}
	
	if (error == "")
		return true;
	else {
		spanNotice.innerHTML = error;
		return false;
	}
	
}