package com.testenglish.model.user;

import java.time.LocalDateTime;
import java.util.Objects;

public class LoginRecord {

	private String uuid;
	private int userId;
	private LocalDateTime lastLogin;
	
	public LoginRecord() {}
	
	public LoginRecord(String uuid, int userId, LocalDateTime lastLogin) {
		this.uuid = uuid;
		this.userId = userId;
		this.lastLogin = lastLogin;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public boolean isEmpty() {
		return uuid == null
			&& userId == 0
			&& lastLogin == null;
	}
	
	public String toString() {
		return "LoginRecord "
				+ "[uuid = " + uuid + ", "
				+ "userId = " + userId + ", "
				+ "lastLogin = " + lastLogin + "]";
	}
	
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof LoginRecord)) return false;
		
		LoginRecord loginRecord = (LoginRecord)obj;
		return (uuid == null ? loginRecord.getUuid() == null : uuid.equals(loginRecord.getUuid()))
			&& userId == loginRecord.getUserId()
			&& (lastLogin == null ?
					loginRecord.getLastLogin() == null :
					lastLogin.equals(loginRecord.getLastLogin()));
	}
	
	public int hashCode() {
		return Objects.hash(uuid, userId, lastLogin);
	}
	
}