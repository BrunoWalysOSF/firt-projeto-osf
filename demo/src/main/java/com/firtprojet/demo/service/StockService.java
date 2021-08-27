package com.firtprojet.demo.service;

import com.firtprojet.demo.entity.Staffs;
import com.firtprojet.demo.entity.Stocks;
import com.firtprojet.demo.repository.StaffRepository;
import com.firtprojet.demo.repository.StoksRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {
    @Autowired
    private StoksRespository stoksRespository;
    @Autowired
    private StaffRepository staffRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changeStock(Long staffId , Long storeId, Long productId,int quantity){
        Staffs staffs = this.staffRepository.findById(staffId).get();
        Long storeStaffId = staffs.getStores().getId();
        if(!staffs.getStaffsList().isEmpty() && storeStaffId.equals(storeId)){
            Stocks stockByProduct = this.stoksRespository.findStockByProduct(productId,storeId );
            stockByProduct.setQuantity(stockByProduct.getQuantity()+quantity);
            if(stockByProduct.getQuantity()>=0){
                this.stoksRespository.save(stockByProduct);
            }else {
                new Exception("stock cannot be less than zero");
            }
        }else {
            new Exception("staff is not manager");
        }
    }


}
