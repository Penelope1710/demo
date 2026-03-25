package fr.eni.demo.asso;

import fr.eni.demo.bo.Civilite;
import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.CiviliteRepository;
import fr.eni.demo.dal.EmployeRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
@Slf4j
public class TestManyToOne {

    @Autowired
    private CiviliteRepository civiliteRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void test_create(){

        Civilite madame = Civilite.builder()
                .cle("MME")
                .libelle("Madame")
                .build();

        Civilite madameDB = civiliteRepository.save(madame);

        Employe employe = Employe.builder()
                .nom("NomTest1")
                .prenom("PrenomTest1")
                .email("email1@campus-eni.fr")
                .immatriculation("TEST1")
                .numDom("0278541232")
                .numPort("067854122")
                .build();

        employe.setCivilite(madameDB);

        Employe employeDB = employeRepository.save(employe);

        log.info(employeDB.toString());

        Assertions.assertThat(employeDB.getId()).isGreaterThan(0);
    }
}
