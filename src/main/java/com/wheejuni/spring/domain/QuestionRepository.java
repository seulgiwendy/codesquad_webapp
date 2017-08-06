package com.wheejuni.spring.domain;

import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long>{
	
	Question questionid(String id);
}
