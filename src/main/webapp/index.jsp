<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Test English | HGANY</title>
</head>
<body>
	<div id="page-title">
		<h2>Test English | HGANY</h2>
	</div>
	<div id="page-link">
		<div id="link-upload_test">
			<a href="uploadtest">Upload đề thi</a>
		</div>
		<div id="link-test_list">
			<a href="testlist">Danh sách đề thi</a>
		</div>
	</div>
	<div id="page-navigation">
		<a href="profile?id=${user.id}"><c:out value="${user.name}"/></a> | <a href="updateprofile">Sửa thông tin</a> | <a href="logout">Đăng xuất</a>
	</div>
</body>
</html>