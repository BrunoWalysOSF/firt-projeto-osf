package com.firtprojet.demo;


import com.firtprojet.demo.service.OrdersService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class FirtProjetOsfApplication {
	public static void main(String[] args) {
		SpringApplication.run(FirtProjetOsfApplication.class, args);
		OrdersService ordersService = new OrdersService();
		ordersService.changeStatus();

	}



}
