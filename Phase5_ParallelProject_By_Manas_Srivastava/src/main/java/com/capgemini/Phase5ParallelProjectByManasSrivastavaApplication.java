package com.capgemini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.capgemini.service")
@ComponentScan("com.capgemini.repo")
@EntityScan("com.capgemini.beans")
public class Phase5ParallelProjectByManasSrivastavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Phase5ParallelProjectByManasSrivastavaApplication.class, args);
	}

}
