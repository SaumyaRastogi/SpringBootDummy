package com.restServices.UdemyRestServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.restServices.UdemyRestServices.security.AppProperties;

@SpringBootApplication
public class UdemyRestServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemyRestServicesApplication.class, args);
	}

	@Bean 
	public BCryptPasswordEncoder BcryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
    
	@Bean
	public SpringApplicationContext springApplicationContext()
	{
		return new SpringApplicationContext();
	}
	
	@Bean(name="AppProperties")
	public AppProperties getAppPoperties()
	{
		return new AppProperties();
	}
}
