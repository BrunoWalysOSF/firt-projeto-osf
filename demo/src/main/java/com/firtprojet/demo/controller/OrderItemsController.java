package com.firtprojet.demo.controller;

import com.firtprojet.demo.entity.Stocks;
import com.firtprojet.demo.repository.OrderItemsRepository;
import com.firtprojet.demo.repository.StoksRespository;
import com.firtprojet.demo.service.OrdersItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderitems")
public class OrderItemsController {
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private StoksRespository stoksRespository;
    @Autowired
    private OrdersItemsService ordersItemsService;

    @GetMapping
    @ResponseBody
    public List<Stocks> findProductDisponibleByStore(@RequestParam("id") Long productId,
                                                     @RequestParam("quantity") int quantity) {
        List<Stocks> productDisponibleByStore = this.ordersItemsService.findProductDisponibleByStore(productId, quantity);
        return productDisponibleByStore;
    }
}
