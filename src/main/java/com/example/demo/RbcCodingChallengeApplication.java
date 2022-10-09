package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example"})  // use this to scan all packages with com.example
public class RbcCodingChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbcCodingChallengeApplication.class, args);
	}

}
