package com.Revshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Revshop {

	public static void main(String[] args) {
		SpringApplication.run(Revshop.class, args);
	}

}
