package com.wheejuni.spring.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long answerid;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
	private Question question;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_user"))
	private User writer;

	String author, content, time;

	public Answer() {

	}

	public Answer(User writer, Question question2, String contents) {

		this.question = question2;
		this.content = contents;
		this.author = writer.getUsername();
		this.setTime();
		this.setWriter(writer);

	}

	/*public User getWriter() {
		return writer;
	}*/

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public String getAuthor() {
		return writer.getUsername();
	}

	public void setAuthor() {
		this.author = writer.getUsername();
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
