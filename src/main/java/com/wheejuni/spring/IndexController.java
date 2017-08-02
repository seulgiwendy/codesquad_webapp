package com.wheejuni.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@GetMapping("/")
	public ModelAndView show() {
		ModelAndView indexpage = new ModelAndView("main/index");
		indexpage.addObject("articles", QuestionsController.questions);
		return indexpage;
	}

}
