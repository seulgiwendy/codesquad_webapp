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
	@RequestMapping(value = "/redvelvet", method = RequestMethod.GET)
	public String rv() {
		
		return "내가 제일 좋아하는 건 여름 그 맛~ 예!" ;
	}
	

}
