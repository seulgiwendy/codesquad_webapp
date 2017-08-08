package com.wheejuni.spring;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wheejuni.spring.domain.*;
import com.wheejuni.spring.domain.User;
import com.wheejuni.spring.domain.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepo;
	ArrayList<User> users = new ArrayList<>();

	@PostMapping("/users")
	public ModelAndView create(User user) {

		//users.add(user);
		userRepo.save(user);
		//System.out.println(users.size());
		return new ModelAndView("redirect:/users");
	}

	@GetMapping("/users")
	public ModelAndView list() {
		ModelAndView userview = new ModelAndView("users/list");
		userview.addObject("users", userRepo.findAll());
		return userview;
	}

	@GetMapping("/users/{index}")
	public ModelAndView show(@PathVariable int index) {
		User user = userRepo.findOne((long) index);

		ModelAndView userpf = new ModelAndView("users/profile");
		userpf.addObject("userinfo", user);
		return userpf;

	}

	@GetMapping("/users/login")
	public String returnLoginForm() {
		return "/users/login";
	}

	@PostMapping("/users/login")
	public ModelAndView getLoginInfo(String id, String password, HttpSession session) {
		User user = userRepo.id(id);
		ModelAndView loginMav = new ModelAndView("/users/loginSuccess");
		if (user == null) {
			return new ModelAndView("redirect:/users/loginFailed");
		}
		if (!password.equals(user.getPassword())) {
			return new ModelAndView("redirect:/users/loginFailed");
		}
		session.setAttribute("user", user);
		loginMav.addObject("username", user.getUsername());
		System.out.print(user.toString());
		return loginMav;
	}
	
	@GetMapping("/users/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return("redirect:/");
	}

	@GetMapping("/users/join")
	public String returnJoinForm() {
		return "/users/form";
	}
	
	@GetMapping("/users/edit")
	public String returnEditForm(Model model, HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return "users/editFailMessage";
		}
		//model.addAttribute("user", user);
		return "/users/edit";
	}
	
	@PostMapping("/users/edit/{index}")
	public String returnCompleteForm(@PathVariable long index, User newUser) {
		User user = userRepo.findOne(index);
		user.update(newUser);
		userRepo.save(user);
		return "redirect:/users";
	}

	@GetMapping("/users/loginFailed")
	public ModelAndView returnLoginFailed() {
		return new ModelAndView("/users/loginfail");
	}

}
