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

	@OneToMany(mappedBy = "writer")
	private List<Answer> writtenAnswers;

	public List<Answer> getWrittenAnswers() {
		return this.writtenAnswers;
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

	public boolean update(User newUser) {
		if (this.getPassword().equals(newUser.getPassword()) == false) {
			return false;
		}

		this.email = newUser.email;
		this.id = newUser.id;
		this.password = newUser.password;
		this.username = newUser.username;
		return true;
	}

	public boolean checkPwd(User newUser) {
		if (this.getPassword() != newUser.getPassword()) {
			return false;
		}
		return true;
	}

	public boolean isFalsePassword(String password) {
		if (password.equals(this.getPassword())) {
			return false;
		}
		return true;
	}

}
