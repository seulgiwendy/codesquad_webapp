package com.wheejuni.spring.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long questionid;
	String content, author, title, time;
	// ArrayList<Answer> answers;
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_questions_writer"))
	private User writer;

	@OneToMany(mappedBy = "question")
	@OrderBy("time desc")
	@JsonIgnore
	private List<Answer> answersdb;
	
	@JsonIgnore
	public List<Answer> getAnswerDb() {
		return this.answersdb;
	}

	/*
	 * public List<Answer> getAnswersDb(){ return this.answersdb; }
	 */

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public long getQuestionid() {
		return questionid;
	}

	public void setQuestionid(long questionid) {
		this.questionid = questionid;
	}

	public String getTime() {
		return time;
	}

	public void setTime() {
		String currentDate = null;
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd kk:mm:ss");
		currentDate = sdf.format(d);
		this.time = currentDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return writer.getUsername();
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void update(Question question) {
		this.content = question.getContent();
		this.time = question.getTime();
		this.title = question.getTitle();
	}

}
