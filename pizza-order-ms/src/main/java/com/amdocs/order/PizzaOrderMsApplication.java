package com.amdocs.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication
public class PizzaOrderMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaOrderMsApplication.class, args);
	}

}
