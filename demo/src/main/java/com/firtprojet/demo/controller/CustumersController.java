package com.firtprojet.demo.controller;

import com.firtprojet.demo.entity.Custumers;
import com.firtprojet.demo.exception.ResourceNotFound;
import com.firtprojet.demo.repository.CustumersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/custumers")
public class CustumersController {
    @Autowired
    private CustumersRepository custumersRepository;

    @GetMapping
    public List<Custumers>  findAllCustumers(){
        return this.custumersRepository.findAll();
    }
    @GetMapping("/{id}")
    public Custumers findCustumersBydId(@PathVariable("id")Long custumersId){
        return this.custumersRepository.findById(custumersId).
                orElseThrow(()->new ResourceNotFound("Custumer not found : " + custumersId));
    }

    @PostMapping
    public Custumers createCustumers(@RequestBody Custumers custumers){
        return this.custumersRepository.save(custumers);
    }
    @PutMapping("/{id}")
    public Custumers updateCustumer(@PathVariable("id")Long custumerId,@RequestBody Custumers custumersUpdate){
        Custumers custumersAlte = this.custumersRepository.findById(custumerId).
                orElseThrow(()->new ResourceNotFound("Custumer not found : " + custumerId));
        custumersAlte.setFirtName(custumersUpdate.getFirtName());
        custumersAlte.setLastName(custumersUpdate.getLastName());
        custumersAlte.setCity(custumersUpdate.getCity());
        custumersAlte.setEmail(custumersUpdate.getEmail());
        custumersAlte.setOrders(custumersUpdate.getOrders());
        custumersAlte.setPhone(custumersUpdate.getPhone());
        custumersAlte.setState(custumersUpdate.getState());
        custumersAlte.setStreet(custumersUpdate.getStreet());
        custumersAlte.setZipCode(custumersUpdate.getZipCode());
        return this.custumersRepository.save(custumersAlte);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Custumers> deletCustumerById(@PathVariable("id")Long custumerId){
        Custumers custumersDelet = this.custumersRepository.findById(custumerId).
                orElseThrow(() -> new ResourceNotFound("Custumers not found : " + custumerId));
        this.custumersRepository.delete(custumersDelet);
        return ResponseEntity.ok().build();
    }
}
