package com.wheejuni.spring;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		System.out.println(users.size());
		return new ModelAndView("redirect:/users");
	}
	
	@GetMapping("/users")
	public ModelAndView list() {
		ModelAndView userview = new ModelAndView("users/list");
		userview.addObject("users", users);
		return userview;
	}
	
	@GetMapping("/users/{index}")
	public ModelAndView show(@PathVariable int index) {
		User user = users.get(index);
		
		ModelAndView userpf = new ModelAndView("users/profile");
		userpf.addObject("userinfo", user);
		return userpf;
		
	}
	
	@GetMapping("/users/login")
	public String returnLoginForm() {
		return "/users/login";
	}
	
	@GetMapping("/users/join")
	public String returnJoinForm() {
		return "/users/form";
	}

}
