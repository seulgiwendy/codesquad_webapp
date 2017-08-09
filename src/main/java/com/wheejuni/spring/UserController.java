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

		// users.add(user);
		userRepo.save(user);
		// System.out.println(users.size());
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
		User user = userRepo.findById(id);
		ModelAndView loginMav = new ModelAndView("/users/loginSuccess");
		if (user == null) {
			return new ModelAndView("redirect:/users/loginFailed");
		}
		if (user.isFalsePassword(password)) {
			return new ModelAndView("redirect:/users/loginFailed");
		}
		session.setAttribute("loginuser", user);
		loginMav.addObject("username", user.getUsername());
		System.out.print(user.toString());
		return loginMav;
	}

	@GetMapping("/users/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginuser");
		return ("redirect:/");
	}

	@GetMapping("/users/join")
	public String returnJoinForm() {
		return "/users/form";
	}

	@GetMapping("/users/modify/{index}")
	public String returnEditForm(@PathVariable long index, HttpSession session) {
		User user = (User) session.getAttribute("loginuser");
		User originalUser = userRepo.findOne(index);
		if (user == null) {
			return "users/editFailMessage";
		}
		if (user.getUniqueId() != originalUser.getUniqueId()) {
			return "users/editUserFailMessage";
		}
		// model.addAttribute("user", user);
		else {
			return "users/edit";
		}

	}

	@PostMapping("/users/modify/{index}")
	public ModelAndView returnCompleteForm(@PathVariable long index, User newUser) {
		User olduser = userRepo.findOne(index);
		newUser.setId(olduser.getId());
		if (olduser.update(newUser) == true) {

			userRepo.save(olduser);
			return new ModelAndView("redirect:/users");
		}

		return new ModelAndView("users/pwFailMessage");
	}

	@GetMapping("/users/loginFailed")
	public ModelAndView returnLoginFailed() {
		return new ModelAndView("/users/loginfail");
	}

}
