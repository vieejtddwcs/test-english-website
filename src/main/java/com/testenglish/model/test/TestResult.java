package com.testenglish.model.test;

import java.util.List;
import java.util.Objects;

public class TestResult {

	private int listeningScore;
	private int readingScore;
	private List<Integer> wrongListeningAnswers;
	private List<Integer> wrongReadingAnswers;
	
	public TestResult() {}
	
	public TestResult(int listeningScore, int readingScore,
					List<Integer> wrongListeningAnswers, List<Integer> wrongReadingAnswers) {
		this.listeningScore = listeningScore;
		this.readingScore = readingScore;
		this.wrongListeningAnswers = wrongListeningAnswers;
		this.wrongReadingAnswers = wrongReadingAnswers;
	}

	public int getListeningScore() {
		return listeningScore;
	}

	public void setListeningScore(int listeningScore) {
		this.listeningScore = listeningScore;
	}

	public int getReadingScore() {
		return readingScore;
	}

	public void setReadingScore(int readingScore) {
		this.readingScore = readingScore;
	}

	public List<Integer> getWrongListeningAnswers() {
		return wrongListeningAnswers;
	}

	public void setWrongListeningAnswers(List<Integer> wrongListeningAnswers) {
		this.wrongListeningAnswers = wrongListeningAnswers;
	}

	public List<Integer> getWrongReadingAnswers() {
		return wrongReadingAnswers;
	}

	public void setWrongReadingAnswers(List<Integer> wrongReadingAnswers) {
		this.wrongReadingAnswers = wrongReadingAnswers;
	}

	@Override
	public String toString() {
		return "TestResult "
				+ "[listeningScore = " + listeningScore + ", "
				+ "readingScore = " + readingScore + ", "
				+ "wrongListeningAnswers = " + wrongListeningAnswers + ", "
				+ "wrongReadingAnswers = " + wrongReadingAnswers + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof TestResult)) return false;
		
		TestResult testResult = (TestResult)obj;
		return listeningScore == testResult.getListeningScore()
			&& readingScore == testResult.getReadingScore()
			&& (wrongListeningAnswers == null ?
					testResult.getWrongListeningAnswers() == null :
						wrongListeningAnswers.equals(testResult.getWrongListeningAnswers()))
			&& (wrongReadingAnswers == null ?
					testResult.getWrongReadingAnswers() == null :
						wrongReadingAnswers.equals(testResult.getWrongReadingAnswers()));
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(listeningScore, readingScore, wrongListeningAnswers, wrongReadingAnswers);
	}
	
}