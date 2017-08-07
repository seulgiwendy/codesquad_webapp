package com.wheejuni.spring.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

	User id (String id) ;
	
	User uniqueId(long uid);
}
