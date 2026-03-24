package fr.eni.demo.asso;

import fr.eni.demo.bo.Adresse;
import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.AdresseRepository;
import fr.eni.demo.dal.EmployeRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@Slf4j
@DataJpaTest
public class TestOneToOne {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private AdresseRepository adresseRepository;

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

        Adresse adresse = Adresse.builder()
                .rue("rue1")
                .codePostal("44000")
                .ville("Ville1")
                .build();
        employe.setAdresse(adresse);

        Employe employeDB = employeRepository.save(employe);

        log.info(employeDB.toString());

        Assertions.assertThat(employeDB.getId()).isGreaterThan(0);
        Assertions.assertThat(employeDB.getAdresse().getId()).isGreaterThan(0);
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

        Adresse adresse = Adresse.builder()
                .rue("rue1")
                .codePostal("44000")
                .ville("Ville1")
                .build();
        employe.setAdresse(adresse);

        Employe employeDB = employeRepository.save(employe);
        employeRepository.flush();
        entityManager.clear();

        log.info(employeDB.toString());

        Integer idEmploye = employeDB.getId();
        Integer idAdresse = employeDB.getAdresse().getId();

        employeRepository.delete(employeDB);
        employeRepository.flush();
        entityManager.clear();

        Optional<Employe> optionalEmploye = employeRepository.findById(idEmploye);

        Assertions.assertThat(optionalEmploye).isEmpty();

        Optional<Adresse> optionalAdresse = adresseRepository.findById(idAdresse);

        Assertions.assertThat(optionalAdresse).isEmpty();

    }

    @Test
    void test_orphanRemoval(){
        Employe employe = Employe.builder()
                .nom("NomTest1")
                .prenom("PrenomTest1")
                .email("email1@campus-eni.fr")
                .immatriculation("TEST1")
                .numDom("0278541232")
                .numPort("067854122")
                .build();

        Adresse adresse = Adresse.builder()
                .rue("rue1")
                .codePostal("44000")
                .ville("Ville1")
                .build();
        employe.setAdresse(adresse);

        Employe employeDB = employeRepository.save(employe);
        employeRepository.flush();
        entityManager.clear();

        log.info(employeDB.toString());

        Integer idEmploye = employeDB.getId();
        Integer idAdresse = employeDB.getAdresse().getId();

        employeDB.setAdresse(null);

        employeRepository.delete(employeDB);
        employeRepository.flush();
        entityManager.clear();

        Optional<Employe> optionalEmploye = employeRepository.findById(idEmploye);

        Assertions.assertThat(optionalEmploye).isEmpty();

        Optional<Adresse> optionalAdresse = adresseRepository.findById(idAdresse);

        Assertions.assertThat(optionalAdresse).isEmpty();

    }



}