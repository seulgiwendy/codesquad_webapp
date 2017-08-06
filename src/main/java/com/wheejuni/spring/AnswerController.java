package com.wheejuni.spring;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wheejuni.spring.domain.Answer;
import com.wheejuni.spring.domain.AnswerRepository;

@Controller
public class AnswerController {
	
	@Autowired
	AnswerRepository answerRepo;
	
	static ArrayList <Answer> answers = new ArrayList<>();
	@PostMapping("/answer/{index}")
	public ModelAndView setAnswer(Answer answer, @PathVariable int index) {
		answer.setTime();
		answers.add(answer);
		answerRepo.save(answer);
		
		return new ModelAndView("redirect:/qna/{index}") ;
	}
	
	public ArrayList<Answer> getAnswer(){
		
		return answers;
	}
}
