package com.testenglish.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.testenglish.dao.ConnectionFactory;
import com.testenglish.dao.impl.TestTypeDAOImpl;
import com.testenglish.model.test.Test;
import com.testenglish.model.test.TestType;
import com.testenglish.service.GradingStrategy;

public final class TOEICGradingStrategy implements GradingStrategy {

	private TestType TOEIC;
	private Map<Integer, Integer> listeningScoreScale;
	private Map<Integer, Integer> readingScoreScale;
	
	private int totalListeningQuestions;
	private int totalReadingQuestions;
	private int totalQuestions;
	
	private TOEICGradingStrategy() {
		update();
	}
	
	private static class Holder {
		private static TOEICGradingStrategy strategy = new TOEICGradingStrategy();
	}
	
	public static TOEICGradingStrategy getInstance() {
		return Holder.strategy;
	}
	
	/*
	 * Always call this method after changes are made in
	 * tables 'test_type' and 'toeic_score_scale' in database.
	 */
	public void update() {
		TestTypeDAOImpl testTypeDAO = new TestTypeDAOImpl();
		TOEIC = testTypeDAO.selectByType(Test.Type.TOEIC);
		listeningScoreScale = selectScoreScale("listening_score");
		readingScoreScale = selectScoreScale("reading_score");
		
		totalListeningQuestions = TOEIC.getTotalListeningQuestions();
		totalReadingQuestions = TOEIC.getTotalReadingQuestions();
		totalQuestions = totalListeningQuestions + totalReadingQuestions;
	}
	
	@Override
	public TestType getTestType() {
		return TOEIC;
	}

	@Override
	public Map<Integer, Integer> getListeningScoreScale() {
		return listeningScoreScale;
	}

	@Override
	public Map<Integer, Integer> getReadingScoreScale() {
		return readingScoreScale;
	}

	/**
	 * Listening questions start from question #1.
	 */
	@Override
	public List<Integer> findWrongListeningAnswers(String userAnswer, String correctAnswer) {
		List<Integer> wrongListeningAnswers = new LinkedList<>();
		for (int i = 1; i <= totalListeningQuestions; i++) {
			if (userAnswer.charAt(i-1) != correctAnswer.charAt(i-1)) wrongListeningAnswers.add(i);
		}
		return wrongListeningAnswers;
	}
	
	/**
	 * Reading questions start after the last listening question.
	 */
	@Override
	public List<Integer> findWrongReadingAnswers(String userAnswer, String correctAnswer) {
		List<Integer> wrongReadingAnswers = new LinkedList<>();
		for (int i = totalListeningQuestions + 1; i <= totalQuestions; i++) {
			if (userAnswer.charAt(i-1) != correctAnswer.charAt(i-1)) wrongReadingAnswers.add(i);
		}
		return wrongReadingAnswers;
	}

	@Override
	public int calculateScore(Map<Integer, Integer> scoreScale, int totalCorrectAnswers) {
		return scoreScale.get(totalCorrectAnswers);
	}
	
	private Map<Integer, Integer> selectScoreScale(String sqlScoreColumn) {
		Map<Integer, Integer> scoreScale = new HashMap<>();
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT total_correct_answers, " + sqlScoreColumn
					+ " FROM toeic_score_scale;");
		) {
			while (rs.next()) scoreScale.put(rs.getInt(1), rs.getInt(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return scoreScale;
	}
	
}