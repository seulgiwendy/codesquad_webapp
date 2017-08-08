package com.wheejuni.spring.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long uniqueId;

	@Column(nullable = false, unique = true, length = 25)
	String id;

	@Column(nullable = false, unique = true, length = 25)

	String username;
	String password;
	@Column(nullable = true)
	String email;
	
	@OneToMany
	@JoinColumn(name = "answer_id") 
	private List <Answer> writtenAnswers; 
	
	public List getWrittenAnswers() {
		return this.writtenAnswers;
	}
	
	public void setWrittenAnswers(List answerlist) {
		this.writtenAnswers = answerlist;
	}
	
	public long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(long uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		String userInfo = "user info: " + this.id + "\n name: " + this.username;
		return userInfo;
	}

	public void update(User newUser) {
		this.email = newUser.email;
		this.id = newUser.id;
		this.password = newUser.password;
		this.username = newUser.username;
	}

}
