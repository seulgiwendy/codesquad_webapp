package com.wheejuni.spring;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wheejuni.spring.domain.Answer;
import com.wheejuni.spring.domain.Question;

@Controller
public class QuestionsController {
	static ArrayList<Question>questions = new ArrayList<>();
	ArrayList <Answer> answers = new ArrayList<>();
	@PostMapping("/qna")
	public ModelAndView create(Question question) {
		
		question.setTime();
		question.setAnswers(AnswerController.answers);
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
		showDetailQuestion.addObject("answerinfo", question.getAnswers());
		//System.out.println(question.getAnswers().get(0).getContent());
		showDetailQuestion.addObject("answercount", question.getAnswers().size());
		
		return showDetailQuestion;
	}
	
	@PostMapping("/qna/{index}")
	public ModelAndView addAnswer(@PathVariable int index, Answer answer) {
		
		answer.setTime();
		answers.add(answer);
		Question temp = questions.get(index);
		System.out.println(answer.getContent());
		temp.setAnswers(answers);
		questions.remove(index);
		questions.add(index, temp);
		System.out.println(questions.get(index).getAnswers().get(0).getContent());
		return new ModelAndView("redirect:/qna");
	}

}
