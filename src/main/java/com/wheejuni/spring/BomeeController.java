package com.wheejuni.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BomeeController {
	
	@RequestMapping(value = "/snakegame", method = RequestMethod.GET)
	public String returnSnake() {
		return "snake.html";
	}
	
	
	
	
	

}
