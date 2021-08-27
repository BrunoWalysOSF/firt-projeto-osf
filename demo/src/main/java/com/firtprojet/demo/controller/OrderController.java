package com.firtprojet.demo.controller;

import com.firtprojet.demo.entity.Orders;
import com.firtprojet.demo.repository.OrdersRepository;
import com.firtprojet.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrdersRepository ordersRepository;


    @GetMapping
    public List<Orders> findAllOrders(){
        System.out.println(ordersService.checkStatus(388L));
    return this.ordersRepository.findAll();
    }
}
