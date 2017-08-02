package com.wheejuni.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wheejuni.spring.domain.Answer;

@Controller
public class AnswerController {
	@PostMapping("/answer")
	public ModelAndView setAnswer(Answer answer) {
		answer.setTime();
		return null;
	}
}
