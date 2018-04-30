package com.testenglish.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.testenglish.dao.AbstractDAO;
import com.testenglish.dao.ConnectionFactory;
import com.testenglish.model.test.Test.Type;
import com.testenglish.model.test.TestType;

public class TestTypeDAOImpl implements AbstractDAO<TestType> {

	/**
	 * For selecting TestType, use {@link TestTypeDAOImpl#selectByType(Type)}
	 */
	@Override
	public TestType select(int id) {
		throw new UnsupportedOperationException();
	}
	
	public TestType selectByType(Type type) {
		TestType testType = new TestType();
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM test_type "
					+ "WHERE type = '" + type.name() + "';");
		) {
			while (rs.next()) {
				testType.setType(type);
				testType.setListeningTime(rs.getInt("listening_time"));
				testType.setReadingTime(rs.getInt("reading_time"));
				testType.setTotalListeningQuestions(rs.getInt("total_listening_questions"));
				testType.setTotalReadingQuestions(rs.getInt("total_reading_questions"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return testType.isEmpty() ? null : testType;
	}

	@Override
	public List<TestType> selectAll() {
		List<TestType> testTypeList = new ArrayList<>();
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM test_type;");
		) {
			while (rs.next()) {
				TestType testType = new TestType();
				testType.setType(Type.valueOf(rs.getString("type")));
				testType.setListeningTime(rs.getInt("listening_time"));
				testType.setReadingTime(rs.getInt("reading_time"));
				testType.setTotalListeningQuestions(rs.getInt("total_listening_questions"));
				testType.setTotalReadingQuestions(rs.getInt("total_reading_questions"));
				
				testTypeList.add(testType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return testTypeList;
	}

	@Override
	public int count() {
		int totalTestTypes = 0;
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM test_type;");
		) {
			while (rs.next()) totalTestTypes = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return totalTestTypes;
	}

	@Override
	public void insert(TestType testType) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			ptmt = conn.prepareStatement("INSERT INTO test_type VALUES (?, ?, ?, ?, ?);");
			ptmt.setString(1, testType.getType().name());
			ptmt.setInt(2, testType.getListeningTime());
			ptmt.setInt(3, testType.getReadingTime());
			ptmt.setInt(4, testType.getTotalListeningQuestions());
			ptmt.setInt(5, testType.getTotalReadingQuestions());
			
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ptmt != null) try { ptmt.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}
	}

	@Override
	public void update(TestType testType) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			ptmt = conn.prepareStatement("UPDATE test_type SET listening_time = ?, reading_time = ?, "
					+ "total_listening_questions = ?, total_reading_questions = ? "
					+ "WHERE type = ?;");
			ptmt.setInt(1, testType.getListeningTime());
			ptmt.setInt(2, testType.getReadingTime());
			ptmt.setInt(3, testType.getTotalListeningQuestions());
			ptmt.setInt(4, testType.getTotalReadingQuestions());
			ptmt.setString(5, testType.getType().name());
			
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ptmt != null) try { ptmt.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}
	}
	
	/**
	 * For deleting TestType, use {@link TestTypeDAOImpl#delete(Type)}
	 */
	@Override
	public void delete(int id) {
		throw new UnsupportedOperationException();
	}

	public void delete(Type type) {
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
		) {
			stmt.executeUpdate("DELETE FROM test_type WHERE type = " + type.name() + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}