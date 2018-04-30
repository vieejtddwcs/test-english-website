package com.testenglish.service;

import java.util.List;
import java.util.Map;

import com.testenglish.model.test.TestResult;
import com.testenglish.model.test.TestType;

public class TestGrader {

	private TestGrader() {
		throw new AssertionError();
	}
	
	public static TestResult grade(String userAnswer, String correctAnswer, GradingStrategy strategy) {
		TestType testType = strategy.getTestType();
		Map<Integer, Integer> listeningScoreScale = strategy
				.getListeningScoreScale();
		Map<Integer, Integer> readingScoreScale = strategy
				.getReadingScoreScale();
		
		List<Integer> wrongListeningAnswers = strategy
				.findWrongListeningAnswers(userAnswer, correctAnswer);
		List<Integer> wrongReadingAnswers = strategy
				.findWrongReadingAnswers(userAnswer, correctAnswer);
		int listeningScore = strategy
				.calculateScore(listeningScoreScale,
								testType.getTotalListeningQuestions() - wrongListeningAnswers.size());
		int readingScore = strategy
				.calculateScore(readingScoreScale,
								testType.getTotalReadingQuestions() - wrongReadingAnswers.size());
		
		return new TestResult(listeningScore, readingScore, wrongListeningAnswers, wrongReadingAnswers);
	}
	
}