package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.ecommerce.common.ActionAspect;

@ComponentScan
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	public ActionAspect actionAspect() {
	  return new ActionAspect();
	}

}
