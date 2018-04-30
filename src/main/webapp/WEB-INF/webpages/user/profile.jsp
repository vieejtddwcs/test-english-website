<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="utf-8">
	<title><c:out value="${viewingUser.name}"/> | Test English</title>
	<link rel="stylesheet" type="text/css" href="/testenglish/css/style.css">
</head>
<body>
	<div id="page" class="bg__page">
		<div class="login">
			<h1>Trang cá nhân</h1>
			<div class="login__form">
				<div class="login__partner">
					<div class="login__partner--title">
						<span>Họ và tên: <c:out value="${viewingUser.name}"/></span>
					</div>
					<div class="login__partner--title">
						<span>Giới tính: ${viewingUser.gender}</span>
					</div>
					<div class="login__partner--title">
						<span>Email: <c:out value="${viewingUser.email}"/></span>
					</div>
					<div class="login__partner--title">
						<span><a href="usersecurity">Bảo mật tài khoản</a></span>
					</div>
					<div class="login__partner--title">
						<span><a href="testhistory?user_id=${user.id}&test_id=all&score=highest&taken_date=newest">Lịch sử làm đề</a></span>
					</div>
				</div>
			</div>
			<div class="login__register">
				<a href="/testenglish" class="btn-signin">Trang chủ</a>
			</div>
		</div>
	</div>
</body>
</html>