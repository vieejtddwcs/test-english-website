<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Sửa đề thi | Test English</title>
</head>
<body>
	<div id="page-title">
		<h2>Sửa đề thi</h2>
	</div>
	<div id="page-test_info">
		<div id="test-name">
			Tên đề: <c:out value="${test.name}"/>
		</div>
		<div id="test-upload_date">
			Ngày upload: <c:out value="${test.uploadDate}"/>
		</div>
	</div>
	<div id="test-action">
		<a href="viewtest?id=${test.id}">Xem đề</a> | <a href="deletetest?id=${test.id}">Xóa đề</a>
	</div>
	<div id="page-upload_form">
		<form method="POST" action="https://upload.uploadcare.com/submit/" enctype="multipart/form-data" onsubmit="return validate()">
			<input type="hidden" name="UPLOADCARE_PUB_KEY" value="22cf8481fcddf0b87a18">
			<input type="hidden" name="UPLOADCARE_ACTION" value="http://localhost:8080/uploadtest">
			<input type="hidden" name="UPLOADCARE_STORE" value="1">
			<input type="hidden" name="DB_OPERATION" value="UPDATE">
			<input type="hidden" name="test_id" value="${test.id}">
			<span id="error_message"></span>
			<div id="input-test_file">
				<span>Chọn file đề:</span>
				<input type="file" id="test_file" name="test_file">
			</div>
			<div id="input-listening_file">
				<span>Chọn file nghe:</span>
				<input type="file" id="listening_file" name="listening_file">
			</div>
			<div id="input-answer_file">
				<span>Chọn file đáp án:</span>
				<input type="file" id="answer_file" name="answer_file">
			</div>
			<div id="select-test_type">
				<span>Chọn loại đề:</span>
				<select name="test_type">
					<option value="TOEIC">TOEIC</option>
				</select>
			</div>
			<div id="input-test_name">
				<span>Tên đề:</span>
				<input type="text" id="test_name" name="test_name" value="<c:out value="${test.name}"/>">
			</div>
			<div id="input-submit">
				<input type="submit" value="Upload">
			</div>
		</form>
	</div>
	<div id="page-navigation">
		<a href="/testenglish">Trang chủ</a> | <a href="profile?id=${user.id}"><c:out value="${user.name}"/></a> | <a href="logout">Đăng xuất</a>
	</div>
	<script type="text/javascript" src="/testenglish/js/uploadvalidate.js"></script>
</body>
</html>