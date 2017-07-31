package com.wheejuni.spring.asdf;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetReq {
	
	@RequestMapping(value = "/bye", method = RequestMethod.GET)
	public String bye() {
		return "꺼져!" ;
	}

}
