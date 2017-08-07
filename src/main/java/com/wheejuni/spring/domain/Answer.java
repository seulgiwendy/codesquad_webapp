package com.wheejuni.spring.domain;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long answerid;
	
	@ManyToOne
	@JoinColumn (foreignKey = @ForeignKey(name = "fk_answer_to_question"))
	private Question question;

	String author, content, time;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public Question getQuestion() {
		return this.question;
	}

	public void setTime() {
		String currentDate = null;
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd kk:mm:ss");
		currentDate = sdf.format(d);
		this.time = currentDate;
	}

}
