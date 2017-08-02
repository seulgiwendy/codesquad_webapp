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
	public ModelAndView create(String userId, String password, String name, String email) {
		

		User user = new User();
		user.setId(userId);
		user.setEmail(email);
		user.setPassword(password);
		user.setUsername(name);

		userlist.add(user);
		ModelAndView userview = new ModelAndView("users/list");
		userview.addObject("id", userId);
		userview.addObject("key", password);
		userview.addObject("name", name);
		userview.addObject("email", email);
		
		System.out.println(userlist.size());
		return userview;
	}

}
