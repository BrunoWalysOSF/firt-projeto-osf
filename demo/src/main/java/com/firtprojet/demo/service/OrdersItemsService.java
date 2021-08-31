package com.firtprojet.demo.service;

import com.firtprojet.demo.entity.*;
import com.firtprojet.demo.exception.ResourceNotFound;
import com.firtprojet.demo.repository.OrderItemsRepository;
import com.firtprojet.demo.repository.ProductRepository;
import com.firtprojet.demo.repository.StoksRespository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoArgsConstructor
@Service
public class OrdersItemsService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private StoksRespository  stoksRespository;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private ProductRepository productRepository;

    /*Verifica a loja que tem stock disponivel*/
    public List<Stocks> findProductDisponibleByStore(Long productId, int quantity) {
        List<Stocks> storeByProductDisponible = this.stoksRespository.
                findStoreByProductDisponible(productId, quantity);
        if(storeByProductDisponible.isEmpty())
           throw new ResourceNotFound("no stock found for this product");
        return storeByProductDisponible;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OrderItems createOrderItem(Custumers custumers, Staffs staffs,Product product,int quantity){
        List<Stocks> productDisponibleByStore = this.findProductDisponibleByStore(product.getId(), quantity);
        Stocks stocksAtualizar = this.stoksRespository.findById(productDisponibleByStore.get(0).getId()).
                orElseThrow(()->new ResourceNotFound("Not found Stocks : "+productDisponibleByStore.get(0).getId()));
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

    /*Calcula a quantidate de vendas de um produto e retorna o produto mais vendido*/
    public Product mostPopularProduct(){
       List<Product> allProductList = this.productRepository.findAll();
       int bigQuantity = 0;
       Product pupularProduct = null;
       for (int i=0;i<allProductList.size();i++) {
           int quantity = 0;
           for (OrderItems orderItems : allProductList.get(i).getOrderItems()) {
               quantity = orderItems.getQuantity() + quantity;
           }
           if(bigQuantity<quantity){
               pupularProduct=allProductList.get(i);
                bigQuantity = quantity;
           }
       }
        return pupularProduct;
    }
}
