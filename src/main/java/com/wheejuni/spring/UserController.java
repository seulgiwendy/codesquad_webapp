package com.wheejuni.spring;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.wheejuni.spring.domain.User;

@Controller
public class UserController {

	ArrayList<User> userlist = new ArrayList<>();

	@PostMapping("/users")
	public ModelAndView create(User user) {
		
		userlist.add(user);
		ModelAndView userview = new ModelAndView("users/list");
		userview.addObject("users", userlist);
		System.out.println(userlist.size());
		return userview;
	}

}
