package com.wheejuni.spring.domain;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class Question {
	
	String content, author, title, time;
	ArrayList<Answer> answers;

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
		return author;
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
	
	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}
	
	public ArrayList<Answer> getAnswers(){
		return this.answers;
	}

}
