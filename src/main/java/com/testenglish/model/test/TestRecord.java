package com.testenglish.model.test;

import java.time.LocalDate;
import java.util.Objects;

public class TestRecord {

	private int id;
	private int userId;
	private int testId;
	private String userAnswer;
	private TestResult result;
	private LocalDate takenDate;
	
	public TestRecord() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public TestResult getResult() {
		return result;
	}

	public void setResult(TestResult result) {
		this.result = result;
	}

	public LocalDate getTakenDate() {
		return takenDate;
	}

	public void setTakenDate(LocalDate takenDate) {
		this.takenDate = takenDate;
	}
	
	public boolean isEmpty() {
		return id == 0
			&& userId == 0
			&& testId == 0
			&& userAnswer == null
			&& result == null
			&& takenDate == null;
	}

	@Override
	public String toString() {
		return "TestRecord "
				+ "[id = " + id + ", "
				+ "userId = " + userId + ", "
				+ "testId = " + testId + ", "
				+ "userAnswer = " + userAnswer + ", "
				+ "result = " + result + ", "
				+ "takenDate = " + takenDate + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof TestRecord)) return false;
		
		TestRecord testRecord = (TestRecord)obj;
		return id == testRecord.getId()
			&& userId == testRecord.getUserId()
			&& testId == testRecord.getTestId()
			&& (userAnswer == null ?
					testRecord.getUserAnswer() == null :
					userAnswer.equals(testRecord.getUserAnswer()))
			&& (result == null ?
					testRecord.getResult() == null :
					result.equals(testRecord.getResult()))
			&& (takenDate == null ?
					testRecord.getTakenDate() == null :
					takenDate.equals(testRecord.getTakenDate()));
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, userId, testId, userAnswer, result, takenDate);
	}
	
}