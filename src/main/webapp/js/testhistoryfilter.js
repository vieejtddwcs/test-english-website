var selectScore = document.getElementById("score");
var selectTakenDate = document.getElementById("taken_date");

for (var i=0; i<selectScore.options.length; i++) {
	if (score == selectScore.options[i].value) {
		selectScore.selectedIndex = i;
	}
}

for (var i=0; i<selectTakenDate.options.length; i++) {
	if (takenDate == selectTakenDate.options[i].value) {
		selectTakenDate.selectedIndex = i;
	}
}