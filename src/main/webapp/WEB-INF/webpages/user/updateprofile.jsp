<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Cập nhật thông tin | Test English</title>
	<link rel="stylesheet" type="text/css" href="/testenglish/css/style.css">
</head>
<body>
	<div id="page" class="bg__page">
		<div class="register">
			<h1>Cập nhật thông tin</h1>
			<div class="register__form">
				<form method="post" action="updateprofile" id="FormRegister" onsubmit="return validateForm()">
					<span id="notice" class="notice">${message}</span>
					<div class="register__form--input">
						<label for="name">Họ và tên</label>
						<input type="text" name="name" id="name" placeholder="Nhập họ và tên của bạn" value="<c:out value="${user.name}"/>">
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
						<button id="btnSignup" class="btn-submit" type="submit">Cập nhật</button>
					</div>
				</form>
			</div>
			<div class="login__register">
				<a href="/testenglish" class="btn-signin">Trang chủ</a>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/testenglish/js/updateprofilevalidate.js"></script>
</body>
</html>