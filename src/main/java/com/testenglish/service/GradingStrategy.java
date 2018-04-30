package com.testenglish.service;

import java.util.List;
import java.util.Map;

import com.testenglish.model.test.TestType;

public interface GradingStrategy {

	public TestType getTestType();
	public Map<Integer, Integer> getListeningScoreScale();
	public Map<Integer, Integer> getReadingScoreScale();
	public List<Integer> findWrongListeningAnswers(String userAnswer, String correctAnswer);
	public List<Integer> findWrongReadingAnswers(String userAnswer, String correctAnswer);
	public int calculateScore(Map<Integer, Integer> scoreScale, int totalCorrectAnswers);
	
}