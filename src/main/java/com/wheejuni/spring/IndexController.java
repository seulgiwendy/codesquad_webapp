package com.wheejuni.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wheejuni.spring.domain.QuestionRepository;

@Controller
public class IndexController {
	@Autowired
	QuestionRepository questionRepo;
	
	@GetMapping("/")
	public ModelAndView show() {
		ModelAndView indexpage = new ModelAndView("main/index");
		indexpage.addObject("articles", questionRepo.findAll());
		return indexpage;
	}

}
