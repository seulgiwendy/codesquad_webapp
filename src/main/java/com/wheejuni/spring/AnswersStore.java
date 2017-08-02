package com.wheejuni.spring;
import java.util.ArrayList;

import com.wheejuni.spring.domain.Answer;


public class AnswersStore {
	
	ArrayList<ArrayList<Answer>> answerstore = new ArrayList<>();

	public ArrayList<Answer> getAnswerstore(int index) {
		if (index >= answerstore.size()) {
			return null;
		}
		return answerstore.get(index);
	}

	public void setAnswerstore(int index, ArrayList<Answer> answer) {
		answerstore.add(index, answer);;
	}
	
}
