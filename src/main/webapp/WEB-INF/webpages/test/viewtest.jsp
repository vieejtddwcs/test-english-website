<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><c:out value="${test.name}"/> | Test English</title>
</head>
<body>
	<div id="page-title">
		<h2><c:out value="${test.name}"/></h2>
	</div>
	<div id="page-test_info">
		<div id="test-type">
			Loại đề: <c:out value="${test.type}"/>
		</div>
		<div id="test-uploader">
			Người upload: <a href="profile?id=${test.uploaderId}"><c:out value="${uploaderName}"/></a>
		</div>
		<div id="test-upload_date">
			Ngày upload: <c:out value="${test.uploadDate}"/>
		</div>
		<div id="test-taken_count">
			Lượt làm đề: <c:out value="${test.takenCount}"/>
		</div>
	</div>
	<div id="test-action">
		<a href="taketest?id=${test.id}">Làm đề</a>
		<c:if test="${user.id == test.uploaderId}">
			 | <a href="edittest?id=${test.id}">Sửa đề</a> | <a id="delete_test" href="deletetest?id=${test.id}">Xóa đề</a>
		</c:if>
	</div>
	<div id="page-test_view">
		<iframe src="https://drive.google.com/viewerng/viewer?embedded=true&url=https://ucarecdn.com/${test.url}/" style="width:900px; height: 555px"></iframe>
	</div>
	<div id="page-navigation">
		<a href="/testenglish">Trang chủ</a> | <a href="profile?id=${user.id}"><c:out value="${user.name}"/></a> | <a href="logout">Đăng xuất</a>
	</div>
	<script type="text/javascript" src="/testenglish/js/deletetestconfirm.js"></script>
</body>
</html>