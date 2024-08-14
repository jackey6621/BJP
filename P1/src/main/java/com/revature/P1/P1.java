package com.revature.P1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;


import javax.persistence.Entity;

@SpringBootApplication
@Configuration
public class P1 {

	public static void main(String[] args) {
		SpringApplication.run(P1.class, args);
	}

}
