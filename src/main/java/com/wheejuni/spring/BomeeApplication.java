package com.wheejuni.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class BomeeApplication{
	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(BomeeApplication.class, args);
	}
	
}
