package fr.eni.demo.dal;

import fr.eni.demo.bo.Employe;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

@Slf4j
@DataJpaTest
public class TestEmployeRepository {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void test_create(){
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
    void test_read(){
        Employe employe = Employe.builder()
                .nom("NomTest1")
                .prenom("PrenomTest1")
                .email("email1@campus-eni.fr")
                .immatriculation("TEST1")
                .numDom("0278541232")
                .numPort("067854122")
                .build();

        Employe employeDB = employeRepository.save(employe);
        entityManager.clear();

        log.info(employeDB.toString());

        Optional<Employe> optionalEmploye = employeRepository.findById(employeDB.getId());

        Assertions.assertThat(optionalEmploye.isPresent()).isTrue();



    }

    @Test
    void test_update(){
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

        entityManager.clear();

        employeDB.setNom("NomTest12");
        Employe employeDB2 = employeRepository.save(employeDB);
        employeRepository.flush();


        log.info(employeDB2.toString());

        Assertions.assertThat(employeDB2.getId()).isGreaterThan(0);
        Assertions.assertThat(employeDB2.getNom()).isEqualTo("NomTest12");
    }

    @Test
    void test_delete(){
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

        entityManager.clear();

        employeRepository.delete(employeDB);
        employeRepository.flush();

        Optional<Employe> optionalEmploye = employeRepository.findById(employeDB.getId());
        Assertions.assertThat(optionalEmploye.isEmpty()).isTrue();
    }


    @Test
    void test_findAll(){
        for (int i =0 ; i < 3 ; i++){
            Employe employe = Employe.builder()
                    .nom("NomTest" + i)
                    .prenom("PrenomTest" + i)
                    .email("email" + i + "@campus-eni.fr")
                    .immatriculation("TEST" + i)
                    .numDom("0278541232")
                    .numPort("067854122")
                    .build();
            employeRepository.save(employe);
        }
        employeRepository.flush();
        entityManager.clear();

        List<Employe> employeList = employeRepository.findAll();

        log.info(employeList.toString());

        Assertions.assertThat(employeList.size()).isEqualTo(3);



    }



}