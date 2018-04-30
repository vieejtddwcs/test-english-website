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
		<div id="listening_time">
			Thời gian phần nghe: <c:out value="${testType.listeningTime}"/> phút
		</div>
		<div id="reading_time">
			Thời gian phần đọc: <c:out value="${testType.readingTime}"/> phút
		</div>
		<div>
			Thời gian còn lại: 
			<span id="remaining_time"></span> phút
		</div>
		<span id="message"></span>
	</div>
	<div id="page-test_view">
		<table>
			<tr>
				<td>
					<div id="listening_file">
						File nghe:
						<br>
						<audio controls>
							<source src="https://ucarecdn.com/${test.listeningFileUrl}/" type="audio/mpeg">
						</audio>
					</div>
					<div id="test_file">
						File đề:
						<iframe src="https://drive.google.com/viewerng/viewer?embedded=true&url=https://ucarecdn.com/${test.url}/" style="width:950px; height: 540px"></iframe>
					</div>
				</td>
				<td>
					<div style="width:325px; height: 560px; overflow-y: scroll">
						<form method="POST" action="gradetest" id="take_test_form" onsubmit="return validate()">
							<input type="hidden" name="test_id" value="${test.id}">
							<c:forEach begin="1" end="${testType.totalListeningQuestions + testType.totalReadingQuestions}" varStatus="loop">
								<c:out value="Câu ${loop.count}: "/>
								<input type="radio" name="answer${loop.count}" value="A">A
								<input type="radio" name="answer${loop.count}" value="B">B
								<input type="radio" name="answer${loop.count}" value="C">C
								<input type="radio" name="answer${loop.count}" value="D">D
								<br>
							</c:forEach>
							<input type="submit" value="Nộp bài">
						</form>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div id="page-navigation">
		<a href="/testenglish">Trang chủ</a> | <a href="profile?id=${user.id}"><c:out value="${user.name}"/></a> | <a href="logout">Đăng xuất</a>
	</div>
	<script type="text/javascript">
		var totalQuestions = ${testType.totalListeningQuestions + testType.totalReadingQuestions};
		var listeningTime = ${testType.listeningTime};
		var readingTime = ${testType.readingTime};
	</script>
	<script type="text/javascript" src="/testenglish/js/taketestvalidate.js"></script>
	<script type="text/javascript">
		window.onbeforeunload = function() { return true; }
	</script>
</body>
</html>