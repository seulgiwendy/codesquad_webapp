package com.wheejuni.spring;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wheejuni.spring.domain.Question;

@Controller
public class QuestionsController {
	static ArrayList<Question>questions = new ArrayList<>();
	
	@PostMapping("/qna")
	public ModelAndView create(Question question) {
		
		question.setTime();
		questions.add(question);
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping("/qna")
	public ModelAndView list() {
		
		ModelAndView mav = new ModelAndView("qna/list");
		mav.addObject("questions", questions);
		return mav;
	}
	
	@GetMapping("/qna/{index}")
	
	public ModelAndView show(@PathVariable int index) {
		
		Question question = questions.get(index);
		ModelAndView showDetailQuestion = new ModelAndView("qna/show");
		showDetailQuestion.addObject("qnainfo", question);
		return showDetailQuestion;
	}

}
