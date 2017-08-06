package com.wheejuni.spring.domain;

import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository <Answer, Long>{
	
	Answer answerid(String id);

}
