package com.firtprojet.demo.service;

import com.firtprojet.demo.entity.*;
import com.firtprojet.demo.exception.ResourceNotFound;
import com.firtprojet.demo.repository.OrderItemsRepository;
import com.firtprojet.demo.repository.StoksRespository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Service
public class OrdersItemsService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private StoksRespository  stoksRespository;
    @Autowired
    private OrdersService ordersService;

    public List<Stocks> findProductDisponibleByStore(Long productId, int quantity) {
        List<Stocks> storeByProductDisponible = this.stoksRespository.findStoreByProductDisponible(productId, quantity);
        if(storeByProductDisponible.isEmpty())
           throw new ResourceNotFound("no stock found for this product");
        return storeByProductDisponible;
    }

    public OrderItems createOrder(Custumers custumers, Staffs staffs,Product product,int quantity){
        List<Stocks> productDisponibleByStore = this.findProductDisponibleByStore(product.getId(), quantity);
        Stocks stocksAtualizar = this.stoksRespository.findById(productDisponibleByStore.get(0).getId()).get();
        stocksAtualizar.setQuantity(stocksAtualizar.getQuantity()-quantity);
        this.stoksRespository.save(stocksAtualizar);
        Stores stores = productDisponibleByStore.get(0).getStores();
        Orders orderSave = ordersService.createOrder(custumers, stores, staffs);
        OrderItems orderItems = new OrderItems();
        orderItems.setOrders(orderSave);
        orderItems.setProduct(product);
        orderItems.setQuantity(quantity);
        orderItems.setListPrice(product.getPrice()*quantity);

        return orderItemsRepository.save(orderItems);
    }

}
