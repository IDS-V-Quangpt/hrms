package com.hr.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan("com.hr")
public class HrApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

}
