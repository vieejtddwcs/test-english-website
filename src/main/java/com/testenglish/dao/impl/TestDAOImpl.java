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
import com.testenglish.model.test.Test;
import com.testenglish.model.test.Test.Type;

public class TestDAOImpl implements AbstractDAO<Test> {
	
	@Override
	public Test select(int testId) {
		Test test = new Test();
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM test WHERE id = " + testId + ";");
		) {
			while (rs.next()) {
				test.setId(testId);
				test.setType(Type.valueOf(rs.getString("type")));
				test.setName(rs.getString("name"));
				test.setUrl(rs.getString("url"));
				test.setListeningFileUrl(rs.getString("listening_file_url"));
				test.setAnswer(rs.getString("answer"));
				test.setUploaderId(rs.getInt("uploader_id"));
				test.setUploadDate(rs.getDate("upload_date").toLocalDate());
				test.setTakenCount(rs.getInt("taken_count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return test.isEmpty() ? null : test;
	}

	public List<Test> selectByUploader(int uploaderId) {
		List<Test> testList = new ArrayList<>();
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM test WHERE uploader_id = " + uploaderId + ";")
		) {
			while (rs.next()) {
				Test test = new Test();
				test.setId(rs.getInt("id"));
				test.setType(Type.valueOf(rs.getString("type")));
				test.setName(rs.getString("name"));
				test.setUrl(rs.getString("url"));
				test.setListeningFileUrl(rs.getString("listening_file_url"));
				test.setAnswer(rs.getString("answer"));
				test.setUploaderId(uploaderId);
				test.setUploadDate(rs.getDate("upload_date").toLocalDate());
				test.setTakenCount(rs.getInt("taken_count"));
				
				testList.add(test);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return testList;
	}
	
	@Override
	public List<Test> selectAll() {
		List<Test> testList = new ArrayList<>();
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM test;");
		) {
			while (rs.next()) {
				Test test = new Test();
				test.setId(rs.getInt("id"));
				test.setType(Type.valueOf(rs.getString("type")));
				test.setName(rs.getString("name"));
				test.setUrl(rs.getString("url"));
				test.setListeningFileUrl(rs.getString("listening_file_url"));
				test.setAnswer(rs.getString("answer"));
				test.setUploaderId(rs.getInt("uploader_id"));
				test.setUploadDate(rs.getDate("upload_date").toLocalDate());
				test.setTakenCount(rs.getInt("taken_count"));
				
				testList.add(test);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return testList;
	}
	
	@Override
	public int count() {
		int totalTests = 0;
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM test;");
		) {
			while (rs.next()) totalTests = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return totalTests;
	}

	@Override
	public void insert(Test test) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			ptmt = conn.prepareStatement("INSERT INTO test VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
			ptmt.setInt(1, test.getId());
			ptmt.setString(2, test.getType().name());
			ptmt.setString(3, test.getName());
			ptmt.setString(4, test.getUrl());
			ptmt.setString(5, test.getListeningFileUrl());
			ptmt.setString(6, test.getAnswer());
			ptmt.setInt(7, test.getUploaderId());
			ptmt.setDate(8, Date.valueOf(test.getUploadDate()));
			ptmt.setInt(9, test.getTakenCount());
			
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ptmt != null) try { ptmt.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}
	}

	@Override
	public void update(Test test) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			ptmt = conn.prepareStatement("UPDATE test SET type = ?, name = ?, "
					+ "url = ?, listening_file_url = ?, answer = ?, "
					+ "uploader_id = ?, upload_date = ?, taken_count = ? WHERE id = ?;");
			ptmt.setString(1, test.getType().name());
			ptmt.setString(2, test.getName());
			ptmt.setString(3, test.getUrl());
			ptmt.setString(4, test.getListeningFileUrl());
			ptmt.setString(5, test.getAnswer());
			ptmt.setInt(6, test.getUploaderId());
			ptmt.setDate(7, Date.valueOf(test.getUploadDate()));
			ptmt.setInt(8, test.getTakenCount());
			ptmt.setInt(9, test.getId());
			
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ptmt != null) try { ptmt.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}
	}

	@Override
	public void delete(int testId) {
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
		) {
			stmt.executeUpdate("DELETE FROM test WHERE id = " + testId + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}