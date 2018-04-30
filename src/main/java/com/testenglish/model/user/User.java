package com.testenglish.model.user;

import java.util.Objects;

public class User {
	
	public enum Gender {
		FEMALE, MALE
	}
	
	public enum Role {
		USER, ADMIN
	}
	
	private int id;
	private Role role;
	private String email;
	private String password;
	private String name;
	private Gender gender;
	
	public User() {}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public boolean isEmpty() {
		return id == 0
			&& role == null
			&& email == null
			&& password == null
			&& name == null
			&& gender == null;
	}
	
	@Override
	public String toString() {
		return "User "
				+ "[id = " + id + ", "
				+ "role = " + role + ", "
				+ "email = " + email + ", "
				+ "password = " + password + ", "
				+ "name = " + name + ", "
				+ "gender = " + gender + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof User)) return false;
		
		User user = (User)obj;
		return id == user.getId()
			&& role == user.getRole()
			&& (email == null ? user.getEmail() == null : email.equals(user.getEmail()))
			&& (password == null ? user.getPassword() == null : password.equals(user.getPassword()))
			&& (name == null ? user.getName() == null : name.equals(user.getName()))
			&& gender == user.getGender();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, role, email, password, name, gender);
	}
	
}