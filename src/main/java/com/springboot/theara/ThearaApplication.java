package com.springboot.theara;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@SecurityScheme(name = "Endpoint Security",scheme = "bearer",type = SecuritySchemeType.HTTP,in = SecuritySchemeIn.HEADER)
public class ThearaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThearaApplication.class, args);
	}

	@Bean
	public ModelMapper mapper()
	{
		return new ModelMapper();
	}


}
