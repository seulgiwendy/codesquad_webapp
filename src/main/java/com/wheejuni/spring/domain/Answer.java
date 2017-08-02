package com.wheejuni.spring.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Answer {
	
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

	public  void setTime() {
		String currentDate = null;
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd kk:mm:ss");
		currentDate = sdf.format(d);
		this.time = currentDate;
	}
	

}
