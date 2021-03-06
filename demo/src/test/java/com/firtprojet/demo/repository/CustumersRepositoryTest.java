package com.firtprojet.demo.repository;

import com.firtprojet.demo.entity.Custumers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustumersRepositoryTest {

    @Autowired
    private CustumersRepository custumersRepository;

    @Test
    public void save_SaveCustumers_WhenSucessoFul(){
        Custumers custumers = createCustumers();
        Custumers custumersSave = this.custumersRepository.save(custumers);
        assertThat(custumersSave).isNotNull();
        assertThat(custumersSave.getId()).isNotNull();
        assertThat(custumersSave.getFirtName()).isEqualTo(custumers.getFirtName());
    }

    @Test
    public void save_UpdateCustumers_WhenSucessoFul(){
        Custumers custumers = createCustumers();
        Custumers custumersSave = this.custumersRepository.save(custumers);
        custumersSave.setFirtName("Paula");
        Custumers custumersUpdate = this.custumersRepository.save(custumersSave);
        assertThat(custumersUpdate.getId()).isEqualTo(custumersSave.getId());
        assertThat(custumersUpdate.getFirtName()).isEqualTo(custumersSave.getFirtName());
    }
    @Test
    public void delete_RemoverCustumers_WhenSucessoFul(){
        Custumers custumers = createCustumers();
        Custumers custumersSave = this.custumersRepository.save(custumers);
        this.custumersRepository.delete(custumersSave);
        Optional<Custumers> custumersList = this.custumersRepository.findById(custumersSave.getId());
        assertThat(custumersList).isEmpty();
    }

    public Custumers createCustumers(){
        return new Custumers(null,"Renata","Calvalcante","(85)98888-9999",
                "Sasas@sasa.com","fortaleza","sa","sas",null,null);
    }
}
