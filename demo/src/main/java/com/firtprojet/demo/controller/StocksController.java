package com.firtprojet.demo.controller;

import com.firtprojet.demo.entity.Stocks;
import com.firtprojet.demo.repository.ProductRepository;
import com.firtprojet.demo.repository.StoksRespository;
import com.firtprojet.demo.repository.StoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StocksController {

    @Autowired
    private StoksRespository stoksRespository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StoresRepository storesRepository;


    @GetMapping
    public List<Stocks> findAllStocks(){
        return this.stoksRespository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Stocks>> findAllStocksByStores(@PathVariable("id")Long storesId){
        List<Stocks> stockByStores = this.stoksRespository.findStockByStores(storesId);
        return ResponseEntity.ok(stockByStores);
    }

}
