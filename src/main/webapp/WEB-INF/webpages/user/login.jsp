<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Đăng nhập | Test English</title>
	<link rel="stylesheet" type="text/css" href="/testenglish/css/style.css">
</head>
<body>
	<div id="page" class="bg__page">
		<div class="login">
			<h1>Đăng nhập</h1>
			<div class="login__form">
				<form method="post" action="login" id="FormLogin">
					<span id="notice" class="notice">${message}</span>
					<div class="login__form--input">
						<label for="email">Email</label>
						<input type="text" name="email" id="email" placeholder="Nhập email của bạn" value="">
					</div>
					<div class="login__form--input">
						<label for="password">Mật khẩu</label>
						<input type="password" name="password" id="password" placeholder="Nhập mật khẩu của bạn" value="">
					</div>
					<div class="login__form--redirect">
						<div class="checkbox">
							<label for="rememberLogin">
								Ghi nhớ đăng nhập
								<input type="checkbox" name="remember_login" id="rememberLogin" value="true">
								<span class="checkmark"></span>
							</label>
						</div>
						<a href="#FormRecovery" class="recovery changeform">Quên mật khẩu?</a>
					</div>
					<div class="login__form--action">
						<button type="submit" class="btn-submit" id="btnLogin">Đăng nhập</button>
					</div>
				</form>
			</div>
			<div class="login__partner">
				<div class="login__partner--title">
					<span>Hoặc đăng nhập qua đối tác</span>
				</div>
				<div class="login__partner--list">
					<a href="#" title="Đăng nhập qua Facebook">
						<img src="/testenglish/css/images/icon-facebook.png" alt="Đăng nhập qua Facebook">
					</a>
					<a href="#" title="Đăng nhập qua Google+">
						<img src="/testenglish/css/images/icon-google-plus.png" alt="Đăng nhập qua Google+">
					</a>
				</div>
			</div>
			<div class="login__register">
				Bạn chưa có tài khoản?
				<a href="register" class="btn-signin">Đăng ký</a>
			</div>
		</div>
	</div>
</body>
</html>