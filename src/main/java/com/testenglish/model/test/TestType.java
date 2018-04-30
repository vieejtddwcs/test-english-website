package com.testenglish.model.test;

import java.util.Objects;

import com.testenglish.model.test.Test.Type;

public class TestType {

	private Type type;
	private int listeningTime;
	private int readingTime;
	private int totalListeningQuestions;
	private int totalReadingQuestions;
	
	public TestType() {}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getListeningTime() {
		return listeningTime;
	}

	public void setListeningTime(int listeningTime) {
		this.listeningTime = listeningTime;
	}

	public int getReadingTime() {
		return readingTime;
	}

	public void setReadingTime(int readingTime) {
		this.readingTime = readingTime;
	}

	public int getTotalListeningQuestions() {
		return totalListeningQuestions;
	}

	public void setTotalListeningQuestions(int totalListeningQuestions) {
		this.totalListeningQuestions = totalListeningQuestions;
	}
	
	public int getTotalReadingQuestions() {
		return totalReadingQuestions;
	}

	public void setTotalReadingQuestions(int totalReadingQuestions) {
		this.totalReadingQuestions = totalReadingQuestions;
	}

	public boolean isEmpty() {
		return type == null
			&& listeningTime == 0
			&& readingTime == 0
			&& totalListeningQuestions == 0;
	}

	@Override
	public String toString() {
		return "TestType "
				+ "[type = " + type + ", "
				+ "listeningTime = " + listeningTime + ", "
				+ "readingTime = " + readingTime + ", "
				+ "totalListeningQuestions = " + totalListeningQuestions + ", "
				+ "totalReadingQuestions = " + totalReadingQuestions + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof TestType)) return false;
		
		TestType testType = (TestType)obj;
		return type == testType.getType()
			&& listeningTime == testType.getListeningTime()
			&& readingTime == testType.getReadingTime()
			&& totalListeningQuestions == testType.getTotalListeningQuestions()
			&& totalReadingQuestions == testType.getTotalReadingQuestions();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(type, listeningTime, readingTime,
				totalListeningQuestions, totalReadingQuestions);
	}
	
}