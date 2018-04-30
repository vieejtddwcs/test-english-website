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
import com.testenglish.model.user.User;
import com.testenglish.model.user.User.Gender;
import com.testenglish.model.user.User.Role;

public class UserDAOImpl implements AbstractDAO<User> {
	
	@Override
	public User select(int id) {
		User user = new User();
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE id = " + id + ";");
		) {
			while (rs.next()) {
				user.setId(id);
				user.setRole(Role.valueOf(rs.getString("role")));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setGender(Gender.valueOf(rs.getString("gender")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user.isEmpty() ? null : user;
	}
	
	public User select(String email, String password) {
		User user = new User();
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			ptmt = conn.prepareStatement("SELECT * FROM user "
					+ "WHERE email = ? AND password = ?;");
			ptmt.setString(1, email);
			ptmt.setString(2, password);
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setRole(Role.valueOf(rs.getString("role")));
				user.setEmail(email);
				user.setPassword(password);
				user.setName(rs.getString("name"));
				user.setGender(Gender.valueOf(rs.getString("gender")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {}
			if (ptmt != null) try { ptmt.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}
		
		return user.isEmpty() ? null : user;
	}

	@Override
	public List<User> selectAll() {
		List<User> users = new ArrayList<>();
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user;");
		) {
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setRole(Role.valueOf(rs.getString("role")));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setGender(Gender.valueOf(rs.getString("gender")));
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	@Override
	public int count() {
		int totalUsers = 0;
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM user;");
		) {
			while (rs.next()) totalUsers = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return totalUsers;
	}
	
	@Override
	public void insert(User user) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			ptmt = conn.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?, ?, ?);");
			ptmt.setInt(1, user.getId());
			ptmt.setString(2, user.getRole().name());
			ptmt.setString(3, user.getEmail());
			ptmt.setString(4, user.getPassword());
			ptmt.setString(5, user.getName());
			ptmt.setString(6, user.getGender().name());
			
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ptmt != null) try { ptmt.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}
	}

	@Override
	public void update(User user) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			ptmt = conn.prepareStatement("UPDATE user SET role = ?, email = ?, password = ?, "
					+ "name = ?, gender = ? WHERE id = ?;");
			ptmt.setString(1, user.getRole().name());
			ptmt.setString(2, user.getEmail());
			ptmt.setString(3, user.getPassword());
			ptmt.setString(4, user.getName());
			ptmt.setString(5, user.getGender().name());
			ptmt.setInt(6, user.getId());
			
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ptmt != null) try { ptmt.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}
	}

	@Override
	public void delete(int id) {
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
		) {
			stmt.executeUpdate("DELETE FROM user WHERE id = " + id + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean emailExists(String email) {
		boolean exists = false;
		try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT 1 FROM user WHERE email = '" + email + "';");
		) {
			while (rs.next()) exists = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exists;
	}
	
}