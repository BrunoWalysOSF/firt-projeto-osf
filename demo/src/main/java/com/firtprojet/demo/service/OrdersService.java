package com.firtprojet.demo.service;

import com.firtprojet.demo.entity.*;
import com.firtprojet.demo.exception.ResourceNotFound;
import com.firtprojet.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private StoresRepository storesRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StoksRespository stoksRespository;

    @Autowired
    private CustumersRepository custumersRepository;
    public OrdersService() {
    }

    public String checkStatus(Long orderId){
        String status =  null;
        Orders orders = this.ordersRepository.findById(orderId).
                orElseThrow(()->new ResourceNotFound("Not found order :"+orderId));
        switch(orders.getOrderStatus()){
            case 1:
                status = "Awaiting shipment";
                break;
            case 2:
                status = "In transit";
                break;
            case 3:
                status = "Delivered";
                break;
            case 4:
                status = "Canceled";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + status);
        }
        return status;
    }
    public Orders createOrder(Custumers custumers, Stores stores, Staffs staffs){
        Orders orders = new Orders();
        orders.setCustumers(custumers);
        orders.setStores(stores);
        orders.setStaffs(staffs);
        orders.setOrderStatus(1);
        return this.ordersRepository.save(orders);
    }
    /*Altera o status do pedido , caso pedido seja cancelado retona stocks para a loja */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changeStatus(Long orderId, int status,Long staffId){
        Orders ordersActual = this.ordersRepository.findById(orderId).
                orElseThrow(()->new ResourceNotFound("Not found order :"+orderId));
        Stores stores = ordersActual.getStores();
        List<Staffs> staffByStore = this.staffRepository.findStaffByStore(stores.getId(), staffId);
        if(!staffByStore.isEmpty()){
            List<OrderItems> orderItems = ordersActual.getOrderItems();
            if(ordersActual.getOrderStatus()!=4) {
                 if (status == 4) {
                      for (OrderItems oritem : orderItems) {
                            Stocks stockByProduct = this.stoksRespository.
                            findStockByProduct(oritem.getProduct().getId(), stores.getId());
                            stockByProduct.setQuantity(stockByProduct.getQuantity() + oritem.getQuantity());
                            this.stoksRespository.save(stockByProduct);
                      }
                 }
            }
             ordersActual.setOrderStatus(status);
             this.ordersRepository.save(ordersActual);
        }
    }
    /*Verificar e retorna o cliente com maior quantidade de pedidos*/
    public Custumers custumersOrderQuantity(){
        List<Custumers> custumersList = this.custumersRepository.findAll();
        int quantityOrders = 0;
        Custumers custumers = null;
        for (int i=0;i<custumersList.size();i++) {
            int cont=0;
            for (Orders orders : custumersList.get(i).getOrders()) {
                cont++;
            }
            if (quantityOrders <= cont){
                quantityOrders = cont;
                custumers = custumersList.get(i);
            }
        }
        return custumers;
    }

}
