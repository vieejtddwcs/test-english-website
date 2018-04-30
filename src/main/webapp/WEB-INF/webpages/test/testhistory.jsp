<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Lịch sử làm đề thi | Test English</title>
</head>
<body>
	<div id="page-title">
		<h2>Lịch sử làm đề thi của <c:out value="${userName}"/></h2>
	</div>
	<div id="page-test_history">
		<div id="test_history-filter">
			<div>Lọc lịch sử làm đề:</div>
			<form method="GET" action="testhistory">
				<input type="hidden" name="user_id" value="${user.id}">
				<input type="hidden" name="test_id" value="all">
				<span>Điểm thi: </span>
				<select id="score" name="score" onchange="this.form.submit()">
					<option value="highest">Cao nhất</option>
					<option value="lowest">Thấp nhất</option>
				</select>
				<span>Ngày làm đề: </span>
				<select id="taken_date" name="taken_date" onchange="this.form.submit()">
					<option value="newest">Mới nhất</option>
					<option value="oldest">Cũ nhất</option>
				</select>
			</form>
		</div>
		<div id="test_history-list">
			<c:forEach var="testRecord" items="${testHistory}" varStatus="loop">
				<div>
					<a href="viewresult?id=${testRecord.id}">${loop.count}. <c:out value="${testRecord.takenDate}"/></a>
				</div>
			</c:forEach>
		</div>
	</div>
	<div id="page-navigation">
		<a href="/testenglish">Trang chủ</a> | <a href="profile?id=${user.id}"><c:out value="${user.name}"/></a> | <a href="logout">Đăng xuất</a>
	</div>
	<script type="text/javascript">
		var score = "${scoreOrder}";
		var takenDate = "${takenDateOrder}";
	</script>
	<script type="text/javascript" src="/testenglish/js/testhistoryfilter.js"></script>
</body>
</html>