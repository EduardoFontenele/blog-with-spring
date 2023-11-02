package com.edublog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class EdublogApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdublogApplication.class, args);
	}

}
