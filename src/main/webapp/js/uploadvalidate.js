function validate() {
	var elementError = document.getElementById("error_message");
	var error = "";
	
	var inputTestFile = document.getElementById("test_file");
	var inputListeningFile = document.getElementById("listening_file");
	var inputAnswerFile = document.getElementById("answer_file");
	var inputTestName = document.getElementById("test_name");
	
	var testFile = inputTestFile.value;
	var listeningFile = inputListeningFile.value;
	var answerFile = inputAnswerFile.value;
	var testName = inputTestName.value;
	
	if (testFile == "") {
		error = "Vui lòng chọn file đề.";
		inputTestFile.focus();
	}
	else if (listeningFile == "") {
		error = "Vui lòng chọn file nghe.";
		inputListeningFile.focus();
	}
	else if (answerFile == "") {
		error = "Vui lòng chọn file đáp án.";
		inputAnswerFile.focus();
	}
	else if (testName == "") {
		error = "Vui lòng nhập tên đề.";
		inputTestName.focus();
	}
	
	if (error == "")
		return true;
	else {
		elementError.innerHTML = error;
		return false;
	}
	
}