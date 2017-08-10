package com.wheejuni.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wheejuni.spring.domain.User;
import com.wheejuni.spring.domain.UserRepository;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
	
	@Autowired
	private UserRepository userRepo;
	@GetMapping("/{id}")
	public User show(@PathVariable long id) {
		return userRepo.findOne(id);
	}
}
