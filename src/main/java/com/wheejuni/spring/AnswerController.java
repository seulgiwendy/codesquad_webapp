package com.wheejuni.spring;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wheejuni.spring.domain.Answer;

@Controller
public class AnswerController {
	
	static ArrayList <Answer> answers = new ArrayList<>();
	@PostMapping("/answer/{index}")
	public ModelAndView setAnswer(Answer answer, @PathVariable int index) {
		answer.setTime();
		answers.add(answer);
		return new ModelAndView("redirect:/qna/{index}") ;
	}
	
	public ArrayList<Answer> getAnswer(){
		
		return answers;
	}
}
