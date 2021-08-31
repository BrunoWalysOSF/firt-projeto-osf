package com.firtprojet.demo.service;

import com.firtprojet.demo.entity.Brands;
import com.firtprojet.demo.entity.Staffs;
import com.firtprojet.demo.entity.Stocks;
import com.firtprojet.demo.exception.ResourceNotFound;
import com.firtprojet.demo.repository.BrandsRepository;
import com.firtprojet.demo.repository.StaffRepository;
import com.firtprojet.demo.repository.StoksRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private StoksRespository stoksRespository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private BrandsRepository brandsRepository;

    /*Altera a quantidade de produtos e verifica se a alteraçao esta feita por um gerente da loja*/
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changeStock(Long staffId , Long storeId, Long productId,int quantity){
        Staffs staffs = this.staffRepository.findById(staffId).
                orElseThrow(()->new ResourceNotFound("Not foind Staff : "+staffId));
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
    /*Verifica qual a marca tem a maior quantidates de produtos*/
    public Brands brandsMostProduct(){
        List<Stocks> stocksList = this.stoksRespository.findAll();
        List<Brands> allBrands = this.brandsRepository.findAll();
        int bigQuantity=0;
        Brands brandsMost = null;
        for (Brands brands:allBrands) {
            int quantity = 0;
            for (int i=0;i<stocksList.size();i++){
                if(stocksList.get(i).getProduct().getBrands().equals(brands)){
                    quantity = stocksList.get(i).getQuantity()+quantity;
                }
            }
            if(bigQuantity<=quantity){
                bigQuantity=quantity;
                brandsMost = brands;
            }
        }
        return brandsMost;
    }
}
