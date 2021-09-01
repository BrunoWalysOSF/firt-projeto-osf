package com.firtprojet.demo.controller;

import com.firtprojet.demo.entity.Custumers;
import com.firtprojet.demo.entity.Orders;
import com.firtprojet.demo.repository.OrdersRepository;
import com.firtprojet.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return this.ordersRepository.findAll();
    }
    @GetMapping("/custumers")
    public Custumers custumerOrderQuantity(){
        return this.ordersService.custumersOrderQuantity();
    }
    @GetMapping("/{id}")
    public List<Orders> findOrdersByCustumers(@PathVariable("id")Long custumerId){
        return this.ordersRepository.findOrdersByCustuers(custumerId);
    }

}
