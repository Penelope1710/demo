package fr.eni.demo.dal;

import fr.eni.demo.bo.Employe;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Slf4j
public class TestEmployeRepository {

    //on demande à Spring de nous l'injecter
    @Autowired
    private EmployeRepository employeRepository;

    @Test
    void test_save() {
        Employe employe = Employe.builder()
                .nom("Prenomtest").prenom("PrenomTest").email("email@campus-eni.fr")
                .immatriculation("TEST1")
                .numDom("02030103001").numPort("0601020344").build();

        Employe employeDB = employeRepository.save(employe);

        log.info(employeDB.toString());

        Assertions.assertThat(employeDB.getId()).isGreaterThan(0);
    }

    @Test
    void test_read() {
        Employe employe = Employe.builder()
                .nom("Prenomtest").prenom("PrenomTest").email("email@campus-eni.fr")
                .immatriculation("TEST1")
                .numDom("02030103001").numPort("0601020344").build();

        Employe employeDB = employeRepository.save(employe);
        employeRepository.flush();

        log.info(employeDB.toString());

        Optional<Employe> optionalEmploye = employeRepository.findById(employeDB.getId());

        Assertions.assertThat(optionalEmploye.isPresent()).isTrue();
    }

}
