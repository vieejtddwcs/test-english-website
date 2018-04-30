window.onload=counter;

var spanRemainingTime = document.getElementById("remaining_time");
var divListeningFile = document.getElementById("listening_file");
var divTestFile = document.getElementById("test_file");
var formTakeTest = document.getElementById("take_test_form");
var spanMessage = document.getElementById("message");

function counter() {
	var totalTime = listeningTime + readingTime;
	countDown();
	
	function countDown() {
		spanRemainingTime.innerHTML = totalTime;
		
		if (totalTime == 0) {
			divTestFile.style.visibility = "hidden";
			if (validate()) formTakeTest.submit();
		}
		
		if (totalTime == readingTime) {
			divListeningFile.style.visibility = "hidden";
		}
		
		if (totalTime > 0) {
			totalTime -= 1;
			setTimeout(countDown, 60000);
		}
	}
	
}

function isAllAnswersChecked() {
	for (var i = 1; i <= totalQuestions; i++) {
		var btnGroupAnswer = document.getElementsByName("answer" + i);
		var isAllChecked = false;
		
		for (var j = 0; j < btnGroupAnswer.length; j++) {
			if (btnGroupAnswer[j].checked) {
				isAllChecked = true;
				break;
			}
		}
		
		if (!isAllChecked) {
			spanMessage.innerHTML = "Bạn chưa tích hết các câu, hãy làm hết đề thi trước khi nộp nhé!";
			return false;
		}
	}
	
	return true;
}

function validate() {
	if (spanRemainingTime.value != 0) {
		return confirm("Bạn có chắc chắn muốn nộp đề ngay bây giờ?") ? isAllAnswersChecked() : false;
	}
	else {
		return isAllAnswersChecked();
	}
}