package com.firtprojet.demo.service;

import com.firtprojet.demo.entity.*;
import com.firtprojet.demo.repository.OrdersRepository;
import com.firtprojet.demo.repository.StaffRepository;
import com.firtprojet.demo.repository.StoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class OrdersService {
    @Autowired
    private StoresRepository storesRepository;
    @Autowired
    private OrdersItemsService ordersItemsService;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private StaffRepository staffRepository;

    public Orders createOrder(Custumers custumers, Stores stores, Staffs staffs){
        Orders orders = new Orders();
        orders.setCustumers(custumers);
        orders.setStores(stores);
        orders.setStaffs(staffs);
        orders.setOrderStatus(1);
        return this.ordersRepository.save(orders);
    }
    public void changeStatus(){
        Scanner sc = new Scanner(System.in);
        System.out.println("|---------------------Alterar status pedido------------------------------|");
        System.out.println("Digite o codigo do funcionario ");
        Long staffId = sc.nextLong();

    }
}
