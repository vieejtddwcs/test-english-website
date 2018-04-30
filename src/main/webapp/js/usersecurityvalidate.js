var inputNewEmail = document.getElementById("new_email");
var inputConfirmPassword = document.getElementById("confirm_password");
var inputOldPassword = document.getElementById("old_password");
var inputNewPassword = document.getElementById("new_password");
var inputReNewPassword = document.getElementById("re_new_password");
var spanMessage = document.getElementById("message");

function validateEmail() {
	var error = "";
	var email = inputNewEmail.value;
	var confirmPassword = inputConfirmPassword.value.replace(/\s/g, "");
	
	if (!isEmailValid(email)) {
		error = "Vui lòng nhập email.";
		inputNewEmail.focus();
	}
	else if (confirmPassword.length < 6) {
		error = "Mật khẩu phải dài tối thiểu 6 kí tự.";
		inputConfirmPassword.focus();
	}
	
	function isEmailValid(email) { 
		var regex = /(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))/;
		return regex.test(email);
	}
	
	if (error == "")
		return true;
	else {
		spanMessage.innerHTML = error;
		return false;
	}
	
}

function validatePassword() {
	var error = "";
	var oldPassword = inputOldPassword.value;
	var newPassword = inputNewPassword.value;
	var reNewPassword = inputReNewPassword.value;
	
	if (oldPassword.length == 0) {
		error = "Vui lòng nhập mật khẩu cũ.";
		inputOldPassword.focus();
	}
	else if (newPassword.length < 6) {
		error = "Mật khẩu phải dài tối thiểu 6 kí tự.";
		inputNewPassword.focus();
	}
	else if (reNewPassword.length == 0) {
		error = "Vui lòng nhập lại mật khẩu.";
		inputReNewPassword.focus();
	}
	else if (newPassword != reNewPassword) {
		error = "Mật khẩu không trùng khớp.";
		inputReNewPassword.focus();
	}
	
	if (error == "")
		return true;
	else {
		spanMessage.innerHTML = error;
		return false;
	}
	
}