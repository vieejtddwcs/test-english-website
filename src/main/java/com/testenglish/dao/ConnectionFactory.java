package com.testenglish.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {
	
	private static DataSource dataSource;
	
	private ConnectionFactory() {
		throw new AssertionError();
	}
	
	/**
	 * Initialize the DataSource object using this lookup name.
	 * <p>
	 * This method must be called before any call
	 * to the {@link ConnectionFactory#getConnection()} method.
	 * 
	 * @param lookupName - the name of the resource to look up
	 */
	public static void init(String lookupName) {
		try {
			Context initContext = new InitialContext();
			dataSource = (DataSource)initContext.lookup(lookupName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get a Connection from the DataSource object.
	 * <p>
	 * The Connection object is taken from the connection pool
	 * that the DataSource object represents.
	 * Calling close() on the Connection object will return it
	 * to the connection pool. 
	 * 
	 * @return a Connection object
	 * @throws SQLException if a database access error occurs
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
}