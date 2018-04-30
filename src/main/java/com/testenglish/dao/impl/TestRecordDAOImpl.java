package com.testenglish.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.testenglish.dao.AbstractDAO;
import com.testenglish.dao.ConnectionFactory;
import com.testenglish.model.test.TestRecord;
import com.testenglish.model.test.TestResult;
import com.testenglish.util.MyStringUtils;

public class TestRecordDAOImpl implements AbstractDAO<TestRecord> {

	@Override
	public TestRecord select(int id) {
		TestRecord testRecord = new TestRecord();
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM test_record WHERE id = " + id + ";");
		) {
			while (rs.next()) {
				testRecord.setId(id);
				testRecord.setUserId(rs.getInt("user_id"));
				testRecord.setTestId(rs.getInt("test_id"));
				testRecord.setUserAnswer(rs.getString("user_answer"));
				testRecord.setResult(new TestResult(
						rs.getInt("listening_score"),
						rs.getInt("reading_score"),
						MyStringUtils.asIntegerList(rs.getString("wrong_listening_answers")),
						MyStringUtils.asIntegerList(rs.getString("wrong_reading_answers"))
						));
				testRecord.setTakenDate(rs.getDate("taken_date").toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return testRecord.isEmpty() ? null : testRecord;
	}
	
	public List<TestRecord> selectByUser(int userId) {
		List<TestRecord> testHistory = new ArrayList<>();
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM test_record "
					+ "WHERE user_id = " + userId + ";");
		) {
			while (rs.next()) {
				TestRecord testRecord = new TestRecord();
				testRecord.setId(rs.getInt("id"));
				testRecord.setUserId(userId);
				testRecord.setTestId(rs.getInt("test_id"));
				testRecord.setUserAnswer(rs.getString("user_answer"));
				testRecord.setResult(new TestResult(
						rs.getInt("listening_score"),
						rs.getInt("reading_score"),
						MyStringUtils.asIntegerList(rs.getString("wrong_listening_answers")),
						MyStringUtils.asIntegerList(rs.getString("wrong_reading_answers"))
						));
				testRecord.setTakenDate(rs.getDate("taken_date").toLocalDate());
				
				testHistory.add(testRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return testHistory;
	}
	
	public List<TestRecord> selectByTest(int testId) {
		List<TestRecord> testHistory = new ArrayList<>();
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM test_record "
					+ "WHERE test_id = " + testId + ";");
		) {
			while (rs.next()) {
				TestRecord testRecord = new TestRecord();
				testRecord.setId(rs.getInt("id"));
				testRecord.setUserId(rs.getInt("user_id"));
				testRecord.setTestId(testId);
				testRecord.setUserAnswer(rs.getString("user_answer"));
				testRecord.setResult(new TestResult(
						rs.getInt("listening_score"),
						rs.getInt("reading_score"),
						MyStringUtils.asIntegerList(rs.getString("wrong_listening_answers")),
						MyStringUtils.asIntegerList(rs.getString("wrong_reading_answers"))
						));
				testRecord.setTakenDate(rs.getDate("taken_date").toLocalDate());
				
				testHistory.add(testRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return testHistory;
	}
	
	public List<TestRecord> selectByUserAndTest(int userId, int testId) {
		List<TestRecord> testHistory = new ArrayList<>();
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM test_record "
					+ "WHERE user_id = " + userId + " AND test_id = " + testId + ";");
		) {
			while (rs.next()) {
				TestRecord testRecord = new TestRecord();
				testRecord.setId(rs.getInt("id"));
				testRecord.setUserId(userId);
				testRecord.setTestId(testId);
				testRecord.setUserAnswer(rs.getString("user_answer"));
				testRecord.setResult(new TestResult(
						rs.getInt("listening_score"),
						rs.getInt("reading_score"),
						MyStringUtils.asIntegerList(rs.getString("wrong_listening_answers")),
						MyStringUtils.asIntegerList(rs.getString("wrong_reading_answers"))
						));
				testRecord.setTakenDate(rs.getDate("taken_date").toLocalDate());
				
				testHistory.add(testRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return testHistory;
	}

	@Override
	public List<TestRecord> selectAll() {
		List<TestRecord> testHistory = new ArrayList<>();
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM test_record;");
		) {
			while (rs.next()) {
				TestRecord testRecord = new TestRecord();
				testRecord.setId(rs.getInt("id"));
				testRecord.setUserId(rs.getInt("user_id"));
				testRecord.setTestId(rs.getInt("test_id"));
				testRecord.setUserAnswer(rs.getString("user_answer"));
				testRecord.setResult(new TestResult(
						rs.getInt("listening_score"),
						rs.getInt("reading_score"),
						MyStringUtils.asIntegerList(rs.getString("wrong_listening_answers")),
						MyStringUtils.asIntegerList(rs.getString("wrong_reading_answers"))
						));
				testRecord.setTakenDate(rs.getDate("taken_date").toLocalDate());
				
				testHistory.add(testRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return testHistory;
	}

	@Override
	public int count() {
		int totalRecords = 0;
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM test_record;");
		) {
			while (rs.next()) totalRecords = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return totalRecords;
	}

	@Override
	public void insert(TestRecord testRecord) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			ptmt = conn.prepareStatement("INSERT INTO test_record VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
			ptmt.setInt(1, testRecord.getId());
			ptmt.setInt(2, testRecord.getUserId());
			ptmt.setInt(3, testRecord.getTestId());
			ptmt.setString(4, testRecord.getUserAnswer());
			ptmt.setInt(5, testRecord.getResult().getListeningScore());
			ptmt.setInt(6, testRecord.getResult().getReadingScore());
			ptmt.setString(7, MyStringUtils.toSeperatedString(testRecord.getResult()
											.getWrongListeningAnswers()));
			ptmt.setString(8, MyStringUtils.toSeperatedString(testRecord.getResult()
											.getWrongReadingAnswers()));
			ptmt.setDate(9, Date.valueOf(testRecord.getTakenDate()));
			
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ptmt != null) try { ptmt.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}
	}

	/**
	 * Updating test record is not allowed.
	 */
	@Override
	public void update(TestRecord testRecord) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Deleting test record is not allowed.
	 */
	@Override
	public void delete(int id) {
		throw new UnsupportedOperationException();
	}
	
}