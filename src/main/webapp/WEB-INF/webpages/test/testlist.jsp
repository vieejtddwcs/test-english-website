<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Danh sách đề thi | Test English</title>
</head>
<body>
	<div id="page-title">
		<h2>Danh sách đề thi</h2>
	</div>
	<div id="page-test_list">
		<div id="test_list-filter">
			<div>Lọc đề thi:</div>
			<form method="GET" action="testlist">
				<span>Upload bởi: </span>
				<select id="uploader_id" name="uploader_id" onchange="this.form.submit()">
					<option value="all">Tất cả</option>
					<option value="${user.id}">Bạn</option>
				</select>
				<span>Ngày upload: </span>
				<select id="upload_date" name="upload_date" onchange="this.form.submit()">
					<option value="newest">Mới nhất</option>
					<option value="oldest">Cũ nhất</option>
				</select>
				<span>Lượt làm đề: </span>
				<select id="taken_count" name="taken_count" onchange="this.form.submit()">
					<option value="most">Nhiều nhất</option>
					<option value="least">Ít nhất</option>
				</select>
			</form>
		</div>
		<div id="test_list-list">
			<c:forEach var="test" items="${testList}" varStatus="testLoopCount">
				<div>
					<a href="viewtest?id=${test.id}">${testLoopCount.count}. <c:out value="${test.name}"/></a>
				</div>
			</c:forEach>
		</div>
	</div>
	<div id="page-navigation">
		<a href="/testenglish">Trang chủ</a> | <a href="profile?id=${user.id}"><c:out value="${user.name}"/></a> | <a href="logout">Đăng xuất</a>
	</div>
	<script type="text/javascript">
		var uploaderId = "${uploaderId}";
		var uploadDate = "${uploadDateOrder}";
		var takenCount = "${takenCountOrder}";
	</script>
	<script type="text/javascript" src="/testenglish/js/testlistfilter.js"></script>
</body>
</html>