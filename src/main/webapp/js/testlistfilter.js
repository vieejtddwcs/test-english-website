var selectUploaderId = document.getElementById("uploader_id");
var selectUploadDate = document.getElementById("upload_date");
var selectTakenCount = document.getElementById("taken_count");

for (var i=0; i<selectUploaderId.options.length; i++) {
	if (uploaderId == selectUploaderId.options[i].value) {
		selectUploaderId.selectedIndex = i;
	}
}

for (var i=0; i<selectUploadDate.options.length; i++) {
	if (uploadDate == selectUploadDate.options[i].value) {
		selectUploadDate.selectedIndex = i;
	}
}

for (var i=0; i<selectTakenCount.options.length; i++) {
	if (takenCount == selectTakenCount.options[i].value) {
		selectTakenCount.selectedIndex = i;
	}
}