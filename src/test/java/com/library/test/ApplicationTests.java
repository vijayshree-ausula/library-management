package com.library.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.library.api.LibraryController;
import com.library.api.repository.BooksRepository;
import com.library.api.repository.QuantityRepository;

@SpringBootTest(classes=LibraryController.class)
@ComponentScan(basePackageClasses = LibraryController.class)
@EnableJpaRepositories("com.library.api.repository.*")
class ApplicationTests {
	
	@Autowired 
	LibraryController libraryController;
	
	@Test
	void contextLoads() throws Exception {
		assertThat(libraryController).isNotNull();
	}

}
