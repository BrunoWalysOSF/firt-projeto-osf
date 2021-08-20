package com.firtprojet.demo.repository;

import com.firtprojet.demo.entity.Staffs;
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
public class StaffRepositoryTest {
    @Autowired
    private StaffRepository staffRepository;

    @Test
    public void save_SaveStaff_WhenSucessuful(){
        Staffs staffs = createStaffs();
        Staffs staffSave = this.staffRepository.save(staffs);
        assertThat(staffSave).isNotNull();
        assertThat(staffSave.getId()).isNotNull();
        assertThat(staffSave.getFirtName()).isEqualTo(staffs.getFirtName());
    }
    @Test
    public void save_UpdateStaff_WhenSucessuful(){
        Staffs staffs = createStaffs();
        Staffs staffSave = this.staffRepository.save(staffs);
        staffSave.setFirtName("Walys");
        Staffs staffsUpdate = this.staffRepository.save(staffSave);
        assertThat(staffsUpdate).isNotNull();
        assertThat(staffsUpdate.getId()).isEqualTo(staffSave.getId());
        assertThat(staffsUpdate.getFirtName()).isEqualTo(staffSave.getFirtName());
    }
    @Test
    public void delete_RemoveStaff_WhenSucessuful(){
        Staffs staffs = createStaffs();
        Staffs staffSave = this.staffRepository.save(staffs);
        this.staffRepository.delete(staffSave);
        Optional<Staffs> staffList = this.staffRepository.findById(staffSave.getId());
        assertThat(staffList).isEmpty();
    }

    public Staffs createStaffs(){
        return new Staffs("Bruno","silva","bruno@gmail.com",
                "(99)988888888");
    }
}
