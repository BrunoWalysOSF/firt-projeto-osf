package com.firtprojet.demo.controller;

import com.firtprojet.demo.entity.Staffs;
import com.firtprojet.demo.exception.ResourceNotFound;
import com.firtprojet.demo.repository.StaffRepository;
import com.firtprojet.demo.repository.StoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StoresRepository storesRepository;

    @GetMapping
    public List<Staffs> findAllStaff(){
        return this.staffRepository.findAll();
    }
    @PostMapping
    public Staffs createStaffs(@RequestBody Staffs staffs){
        return this.staffRepository.save(staffs);
    }
    @PutMapping("/{id}")
    public Staffs updateStaffs(@PathVariable("id")Long staffId,@RequestBody Staffs staffsUpdate){
        Staffs staffAtual = this.staffRepository.findById(staffId).
                orElseThrow(()->new ResourceNotFound("Not found staff :"+staffId));
        staffAtual.setFirtName(staffsUpdate.getFirtName());
        staffAtual.setActive(staffsUpdate.getActive());
        staffAtual.setStaffManagers(staffsUpdate.getStaffManagers());
        staffAtual.setStaffsList(staffsUpdate.getStaffsList());
        staffAtual.setEmail(staffsUpdate.getEmail());
        staffAtual.setStores(staffsUpdate.getStores());
        staffAtual.setLastName(staffsUpdate.getLastName());
        staffAtual.setPhone(staffsUpdate.getPhone());
        staffAtual.setOrdersList(staffsUpdate.getOrdersList());
        return this.staffRepository.save(staffAtual);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Staffs> deleteStaffsResponseEntity(@PathVariable("id")Long staffId){
        Staffs staffAtual = this.staffRepository.findById(staffId).
                orElseThrow(()->new ResourceNotFound("Not found staff :"+staffId));
        staffAtual.setStaffManagers(null);
        staffAtual.setStores(null);
        staffAtual = this.staffRepository.save(staffAtual);
        this.staffRepository.delete(staffAtual);
        return ResponseEntity.ok().build();
    }

}
