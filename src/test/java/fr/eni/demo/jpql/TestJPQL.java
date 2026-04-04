package fr.eni.demo.jpql;


import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.EmployeRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;


@Slf4j
@DataJpaTest
public class TestJPQL {

    @Autowired
    private EmployeRepository employeRepository;

    @BeforeEach
    void insert_employe(){
        Employe employe = Employe.builder()
                .nom("NomTest1")
                .prenom("PrenomTest1")
                .email("email1@campus-eni.fr")
                .immatriculation("TEST1")
                .numDom("0278541232")
                .numPort("067854122")
                .build();

        Employe employeDB = employeRepository.save(employe);

        log.info(employeDB.toString());

        Assertions.assertThat(employeDB.getId()).isGreaterThan(0);
    }


    @Test
    void test_findByEmailJPQL(){

        Optional<Employe> optionalEmploye = employeRepository.findByEmailJPQL("email1@campus-eni.fr");

        log.info(optionalEmploye.get().toString());

        Assertions.assertThat(optionalEmploye).isPresent();
    }

}