package com.wheejuni.spring.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

	User findById (String id) ;
	
	User findByUniqueId(long uid);
}
