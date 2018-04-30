<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Đăng ký | Test English</title>
	<link rel="stylesheet" type="text/css" href="/testenglish/css/style.css">
</head>
<body>
	<div id="page" class="bg__page">
		<div class="register">
			<h1>Đăng ký</h1>
			<div class="register__form">
				<form method="post" action="register" id="FormRegister" onsubmit="return validateForm()">
					<span id="notice" class="notice">${error}</span>
					<div class="register__form--input">
						<label for="email">Email</label>
						<input type="text" name="email" id="email" placeholder="Nhập email của bạn" value="">
					</div>
					<div class="register__form--input">
						<label for="password">Mật khẩu</label>
						<input type="password" name="password" id="password" placeholder="Mật khẩu tối thiểu 6 ký tự" value="">
					</div>
					<div class="register__form--input">
						<label for="rePassword">Nhập lại mật khẩu</label>
						<input type="password" name="rePassword" id="rePassword" placeholder="Mật khẩu tối thiểu 6 ký tự" value="">
					</div>
					<div class="register__form--input">
						<label for="name">Họ và tên</label>
						<input type="text" name="name" id="name" placeholder="Nhập họ và tên của bạn" value="">
					</div>
					<div class="register__form--gender">
						<label>Giới tính:</label>
						<div>
							<select name="gender">
								<option value="FEMALE">Nữ</option>
								<option value="MALE">Nam</option>
							</select>
						</div>
					</div>
					<br>
					<div class="register__form--action">
						<button id="btnSignup" class="btn-submit" type="submit">Đăng ký</button>
					</div>
				</form>
			</div>
			<div class="login__register">
				Bạn đã có tài khoản?
				<a href="login" class="btn-signin">Đăng nhập</a>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/testenglish/js/registervalidate.js"></script>
</body>
</html>