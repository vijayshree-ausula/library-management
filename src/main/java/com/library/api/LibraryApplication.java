package com.library.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackageClasses = LibraryController.class)
@EnableJpaRepositories("com.library.api.*")
public class LibraryApplication {

	public static void main(String[] args) {
//		
		SpringApplication.run(LibraryApplication.class, args);
	}

}
