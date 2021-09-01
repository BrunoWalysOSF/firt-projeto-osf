package com.firtprojet.demo.controller;

import com.firtprojet.demo.entity.*;
import com.firtprojet.demo.exception.ResourceNotFound;
import com.firtprojet.demo.repository.*;
import com.firtprojet.demo.service.OrdersItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    CustumersRepository custumersRepository;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<?> findAllOrdersItems(Pageable pageable){
        return new ResponseEntity<>(this.orderItemsRepository.findAll(pageable), HttpStatus.OK);
    }
    @ResponseBody
    public List<Stocks> findProductDisponibleByStore(@RequestParam("id") Long productId,
                                                     @RequestParam("quantity") int quantity) {
        List<Stocks> productDisponibleByStore =
                this.ordersItemsService.findProductDisponibleByStore(productId, quantity);
        if(productDisponibleByStore.isEmpty())
             throw new ResourceNotFound("No stock available for order");
        return productDisponibleByStore;
    }
    @GetMapping("/items")
    public List<OrderItems> findByOrderItems(){

        return this.orderItemsRepository.findAll();
    }
    @GetMapping("/mostpopularproduct")
    public Product mostPopularProduct(){
        return ordersItemsService.mostPopularProduct();
    }
}
