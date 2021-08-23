package com.firtprojet.demo.controller;

import com.firtprojet.demo.entity.Custumers;
import com.firtprojet.demo.repository.CustumersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = CustumersController.class)
class CustumersControllerTest {


    private MockMvc mockMvc;

    @Autowired
    private CustumersController custumersController;

    @MockBean
    private CustumersRepository custumersRepository;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(custumersController).build();
        Custumers  custumers = createCustumers();
        List<Custumers> custumersList = new ArrayList<>();
        custumersList.add(custumers);
        when(this.custumersRepository.findAll()).thenReturn(custumersList);
        when(this.custumersRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(custumers));
        when(this.custumersRepository.save(custumers)).thenReturn(custumers);
    }

    public Custumers createCustumers(){
        return new Custumers(1L,"Renata","Calvalcante","(85)98888-9999",
                "Sasas@sasa.com","fortaleza","sa","sas",null,null);
    }

    @Test
    void findAllCustumers() {
        List<Custumers> allCustumers = this.custumersController.findAllCustumers();
        assertThat(allCustumers).isNotEmpty();
        assertThat(allCustumers).contains(createCustumers());
    }

    @Test
    void findCustumersBydId() {
        Custumers custumersBydId = this.custumersController.findCustumersBydId(1L);
        Custumers custumersOptional = createCustumers();
        assertThat(custumersBydId).isEqualTo(custumersOptional);
    }

    @Test
    void  createCustumersSucessul(){
        Custumers custumers = this.custumersController.createCustumers(createCustumers());
        assertThat(custumers).isNotNull();
        assertThat(custumers.getId()).isNotNull();
    }

    @Test
    void updateCustumer() {
        Custumers custumers = createCustumers();
        Custumers custumersSave = this.custumersController.createCustumers(custumers);
        custumersSave.setFirtName("Jessica");
        Custumers custumersUpdate = this.custumersController.updateCustumer(1L, custumersSave);
        assertThat(custumersUpdate.getId()).isEqualTo(custumersSave.getId());
        assertThat(custumersUpdate.getFirtName()).isEqualTo(custumersSave.getFirtName());

    }

}