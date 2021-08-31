package com.firtprojet.demo.controller;

import com.firtprojet.demo.entity.Stores;
import com.firtprojet.demo.exception.ResourceNotFound;
import com.firtprojet.demo.repository.StoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoresController {
    @Autowired
    private StoresRepository storesRepository;

    @GetMapping
    public List<Stores> findAllStores(){ return this.storesRepository.findAll(); }
    @PostMapping
    public Stores createStores(@RequestBody Stores stores){
        return this.storesRepository.save(stores);
    }
    @PutMapping("/{id}")
    public Stores updateStores(@PathVariable("id")Long storesId,@RequestBody Stores storesUpdate){
        Stores storesAtual = this.storesRepository.findById(storesId).
                orElseThrow(()->new ResourceNotFound("Not found Store : "+storesId));
        storesAtual.setStoreName(storesUpdate.getStoreName());
        storesAtual.setCity(storesUpdate.getCity());
        storesAtual.setEmail(storesUpdate.getEmail());
        storesAtual.setStocks(storesUpdate.getStocks());
        storesAtual.setStaffs(storesUpdate.getStaffs());
        storesAtual.setOrders(storesUpdate.getOrders());
        storesAtual.setPhone(storesUpdate.getPhone());
        storesAtual.setZipCode(storesUpdate.getZipCode());
        storesAtual.setStreet(storesUpdate.getStreet());
        storesAtual.setState(storesUpdate.getState());
        return this.storesRepository.save(storesAtual);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Stores> deletStore(@PathVariable("id")Long storeId){
        Stores stores = this.storesRepository.findById(storeId).get();
        this.storesRepository.delete(stores);
        return ResponseEntity.ok().build();
    }
}
