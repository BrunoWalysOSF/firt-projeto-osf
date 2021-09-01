package com.firtprojet.demo.controller;

import com.firtprojet.demo.entity.Stocks;
import com.firtprojet.demo.exception.ResourceNotFound;
import com.firtprojet.demo.repository.ProductRepository;
import com.firtprojet.demo.repository.StoksRespository;
import com.firtprojet.demo.repository.StoresRepository;
import com.firtprojet.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    private StockService stockService;

    @GetMapping
    public ResponseEntity<?> findAllStocks(Pageable pageable){
        return new ResponseEntity<>(this.stoksRespository.findAll(pageable),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAllStocksByStores(@PathVariable("id")Long storesId){
        List<Stocks> stockByStores = this.stoksRespository.findStockByStores(storesId);
        if(stockByStores.isEmpty())
            throw new ResourceNotFound("No stocks found for this store");
        return new ResponseEntity<>(stockByStores,HttpStatus.OK);
    }
    @GetMapping("/categorymost")
    public ResponseEntity<?> findBrandMostProduct(){
        return new ResponseEntity<>(this.stockService.brandsMostProduct(),HttpStatus.OK);
    }

}
