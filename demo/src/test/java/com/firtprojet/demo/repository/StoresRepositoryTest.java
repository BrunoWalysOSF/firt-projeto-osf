package com.firtprojet.demo.repository;

import com.firtprojet.demo.entity.Stores;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StoresRepositoryTest {

    @Autowired
    private StoresRepository storesRepository;



    @Test
    public void save_PersisteStore_WhenSucessuful(){
        Stores stores = createStore();
        Stores storesSave = this.storesRepository.save(stores);
        assertThat(storesSave).isNotNull();
        assertThat(storesSave.getId()).isNotNull();
        assertThat(storesSave.getStoreName()).isEqualTo(stores.getStoreName());

    }

    @Test
    public void save_UpdateStore_WhenSucessuful(){
        Stores stores = createStore();
        Stores storesSave = this.storesRepository.save(stores);
        storesSave.setStoreName("Aliexpress");
        Stores storesUpdate = this.storesRepository.save(storesSave);

        assertThat(storesUpdate).isNotNull();
        assertThat(storesUpdate.getId()).isNotNull();
        assertThat(storesUpdate.getId()).isEqualTo(storesSave.getId());
        assertThat(storesUpdate.getStoreName()).isEqualTo(storesSave.getStoreName());

    }
    @Test
    public void delete_RemoveStore_WhenSucessuful(){
        Stores stores = createStore();
        Stores storesSave = this.storesRepository.save(stores);
        this.storesRepository.delete(storesSave);
        Optional<Stores> storesList = this.storesRepository.findById(storesSave.getId());
        assertThat(storesList).isEmpty();
    }




    public Stores createStore(){
        return  new Stores(null,"Amazon","+55(88)88888888",
                "amazon@amazon.com","r:23","Arizona","xxx","77",
                null,null,null);
    }

}