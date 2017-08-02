package com.wheejuni.spring;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.wheejuni.spring.domain.User;

@Controller
public class UserController {

	ArrayList<User> users = new ArrayList<>();

	@PostMapping("/users")
	public ModelAndView create(User user) {
		
		users.add(user);
		ModelAndView userview = new ModelAndView("users/list");
		userview.addObject("users", users);
		System.out.println(users.size());
		return userview;
	}

}
