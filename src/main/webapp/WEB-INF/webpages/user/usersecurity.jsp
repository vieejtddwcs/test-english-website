<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Bảo mật tài khoản | Test English</title>
</head>
<body>
	<div id="page-title">
		<h2>Bảo mật tài khoản</h2>
	</div>
	<div id="page-message">
		<span id="message"><c:out value="${message}"/></span>
	</div>
	<div id="change_email-form">
		<p>Đổi email</p>
		<form method="POST" action="usersecurity" onsubmit="return validateEmail()">
		<div id="email-old">
			<span>Email cũ: </span>
			<input type="text" id="old_email" name="old_email" value="<c:out value="${user.email}"/>" disabled>
		</div>
		<div id="email-new">
			<span>Email mới: </span>
			<input type="text" id="new_email" name="new_email" value="">
		</div>
		<div id="confirm_password">
			<span>Xác nhận mật khẩu: </span>
			<input type="password" id="confirm_password" name="confirm_password" value="">
		</div>
		<input type="submit" value="OK">
		</form>
	</div>
	<div id="change_password-form">
		<p>Đổi mật khẩu</p>
		<form method="POST" action="usersecurity" onsubmit="return validatePassword()">
		<div id="password-old">
			<span>Mật khẩu cũ: </span>
			<input type="password" id="old_password" name="old_password" value="">
		</div>
		<div id="password-new">
			<span>Mật khẩu mới: </span>
			<input type="password" id="new_password" name="new_password" value="">
		</div>
		<div id="password-re_new">
			<span>Nhập lại mật khẩu mới: </span>
			<input type="password" id="re_new_password" name="re_new_password" value="">
		</div>
		<input type="submit" value="OK">
		</form>
	</div>
	<div id="page-navigation">
		<a href="/testenglish">Trang chủ</a> | <a href="profile?id=${user.id}"><c:out value="${user.name}"/></a> | <a href="logout">Đăng xuất</a>
	</div>
	<script type="text/javascript" src="/testenglish/js/usersecurityvalidate.js"></script>
</body>
</html>