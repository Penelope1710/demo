package fr.eni.demo.bll;

import fr.eni.demo.bo.Adresse;
import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.EmployeRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class TestTransaction {

    @Autowired
    private EmployeService employeService;
    @Autowired
    private EmployeRepository employeRepository;

    @Test
    void test_ajouter_employeOk(){
        Employe employe = Employe.builder()
                .nom("NomTest1")
                .prenom("PrenomTest1")
                .email("email1@campus-eni.fr")
                .immatriculation("TEST1")
                .numDom("0278541232")
                .numPort("067854122")
                .build();



        employeService.ajouter(employe);

        log.info(employe.toString());

        Assertions.assertThat(employe.getId()).isGreaterThan(0);
    }

    @Test
    void test_ajouter_employe_et_adresse(){
        Employe employe = Employe.builder()
                .nom("NomTest2")
                .prenom("PrenomTest2")
                .email("email2@campus-eni.fr")
                .immatriculation("TEST2")
                .numDom("0278541232")
                .numPort("067854122")
                .build();

        Adresse adresse = Adresse.builder()
                .rue("rueTest")
                .codePostal("44000")
                .ville("Nantes")
                .build();

        employeService.ajouter(employe, adresse);

        log.info(employe.toString());
        log.info(adresse.toString());
        Assertions.assertThat(employe.getId()).isGreaterThan(0);
        Assertions.assertThat(adresse.getId()).isGreaterThan(0);
    }

    @Test
    void test_ajouter_employe_et_adresseKo(){
        Employe employe = Employe.builder()
                .nom("NomTest3")
                .prenom("PrenomTest3")
                .email("email3@campus-eni.fr")
                .immatriculation("TEST3")
                .numDom("0278541232")
                .numPort("067854122")
                .build();

        Adresse adresse = Adresse.builder()
                .codePostal("44000")
                .ville("Nantes")
                .build();

       org.junit.jupiter.api.Assertions.assertThrows(
               RuntimeException.class,
               () -> employeService.ajouter(employe, adresse)
       );

       Optional<Employe> optionalEmploye = employeRepository.findAll().stream().filter(e->e.getImmatriculation().equals("TEST3")).findAny();

       Assertions.assertThat(optionalEmploye.isEmpty()).isTrue();

    }
}