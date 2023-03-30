package com.example.codefirst.demo.test;

import com.example.codefirst.demo.client.dto.Hello;
import com.example.codefirst.demo.configuration.TestConfiguration;
import com.example.codefirst.demo.controller.HelloWorldController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = {HelloWorldController.class, TestConfiguration.class})
class ConfigurationTest {

	@Autowired
	private HelloWorldController controller;

	@Test
	void shouldMapUser() {
		// given

		// when
		Hello response = controller.getHelloWorld();


		// then
		System.out.println();

	}

}
