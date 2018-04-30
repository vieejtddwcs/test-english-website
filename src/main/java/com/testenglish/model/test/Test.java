package com.testenglish.model.test;

import java.time.LocalDate;
import java.util.Objects;

public class Test {
	
	public enum Type {
		TOEIC
	}

	private int id;
	private Type type;
	private String name;
	private String url;
	private String listeningFileUrl;
	private String answer;
	private int uploaderId;
	private LocalDate uploadDate;
	private int takenCount;
	
	public Test() {}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getListeningFileUrl() {
		return listeningFileUrl;
	}

	public void setListeningFileUrl(String listeningFileUrl) {
		this.listeningFileUrl = listeningFileUrl;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public int getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(int uploaderId) {
		this.uploaderId = uploaderId;
	}
	
	public LocalDate getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDate uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getTakenCount() {
		return takenCount;
	}

	public void setTakenCount(int takenCount) {
		this.takenCount = takenCount;
	}

	public boolean isEmpty() {
		return id == 0
			&& type == null
			&& name == null
			&& url == null
			&& listeningFileUrl == null
			&& answer == null
			&& uploaderId == 0
			&& uploadDate == null
			&& takenCount == 0;
	}
	
	@Override
	public String toString() {
		return "Test "
				+ "[id = " + id + ", "
				+ "type = " + type + ", "
				+ "name = " + name + ", "
				+ "url = " + url + ", "
				+ "listeningFileUrl = " + listeningFileUrl + ", "
				+ "answer = " + answer + ", "
				+ "uploaderId = " + uploaderId + ", "
				+ "uploadDate = " + uploadDate + ", "
				+ "takenCount = " + takenCount + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof Test)) return false;
		
		Test test = (Test)obj;
		return id == test.getId()
			&& type == test.getType()
			&& (name == null ? test.getName() == null : name.equals(test.getName()))
			&& (url == null ? test.getUrl() == null : url.equals(test.getUrl()))
			&& (listeningFileUrl == null ?
					test.getListeningFileUrl() == null :
					listeningFileUrl.equals(test.getListeningFileUrl()))
			&& uploaderId == test.getUploaderId()
			&& (uploadDate == null ?
					test.getUploadDate() == null :
					uploadDate.equals(test.getUploadDate()))
			&& takenCount == test.getTakenCount();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, type, name, url, listeningFileUrl, answer,
				uploaderId, uploadDate, takenCount);
	}
	
}