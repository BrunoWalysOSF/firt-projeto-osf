package com.firtprojet.demo;


import com.firtprojet.demo.service.OrdersService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(servers = {@Server(url = "/",description = "Default Server Url")})
public class FirtProjetOsfApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirtProjetOsfApplication.class, args);
	}

}
