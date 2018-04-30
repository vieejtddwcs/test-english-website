<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Kết quả làm đề | Test English</title>
</head>
<body>
	<div id="page-title">
		<h2>Kết quả làm đề</h2>
	</div>
	<div id="page-result_info">
		<div id="info-test_name">
			Tên đề: <a href="viewtest?id=${test.id}"><c:out value="${test.name}"/></a>
		</div>
		<div id="info-user_name">
			Người làm: <a href="profile?id=${testRecord.userId}"><c:out value="${userName}"/></a>
		</div>
		<div id="info-taken_date">
			Ngày làm đề: <c:out value="${testRecord.takenDate}"/>
		</div>
		<div id="info-total_score">
			Tổng điểm: <c:out value="${testRecord.result.listeningScore + testRecord.result.readingScore}"/>
		</div>
		<div id="info-listening_score">
			Điểm phần nghe: <c:out value="${testRecord.result.listeningScore}"/>
		</div>
		<div id="info-reading_score">
			Điểm phần đọc: <c:out value="${testRecord.result.readingScore}"/>
		</div>
	</div>
	<div id="page-navigation">
		<a href="/testenglish">Trang chủ</a> | <a href="profile?id=${user.id}"><c:out value="${user.name}"/></a> | <a href="logout">Đăng xuất</a>
	</div>
</body>
</html>