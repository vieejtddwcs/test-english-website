package com.testenglish.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;

import com.testenglish.dao.AbstractDAO;
import com.testenglish.dao.ConnectionFactory;
import com.testenglish.model.user.LoginRecord;

public class LoginRecordDAOImpl implements AbstractDAO<LoginRecord> {

	/**
	 * For selecting LoginRecord, use {@link LoginRecordDAOImpl#select(String uuid)}
	 */
	@Override
	public LoginRecord select(int id) {
		throw new UnsupportedOperationException();
	}
	
	public LoginRecord select(String uuid) {
		LoginRecord loginRecord = new LoginRecord();
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM login_record WHERE uuid = '" + uuid + "';");
		) {
			while (rs.next()) {
				loginRecord.setUuid(uuid);
				loginRecord.setUserId(rs.getInt("user_id"));
				loginRecord.setLastLogin(rs.getTimestamp("last_login").toLocalDateTime());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return loginRecord.isEmpty() ? null : loginRecord;
	}

	/**
	 * This method is not yet supported.
	 */
	@Override
	public Collection<LoginRecord> selectAll() {
		throw new UnsupportedOperationException();
	}

	/**
	 * This method is not yet supported.
	 */
	@Override
	public int count() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void insert(LoginRecord loginRecord) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			ptmt = conn.prepareStatement("INSERT INTO login_record VALUES (?, ?, ?);");
			ptmt.setString(1, loginRecord.getUuid());
			ptmt.setInt(2, loginRecord.getUserId());
			ptmt.setTimestamp(3, Timestamp.valueOf(loginRecord.getLastLogin()));
			
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ptmt != null) try { ptmt.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}
	}

	/**
	 * Updating login record is not allowed.
	 */
	@Override
	public void update(LoginRecord loginRecord) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void delete(int userId) {
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
		) {
			stmt.executeUpdate("DELETE FROM login_record WHERE user_id = " + userId + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}