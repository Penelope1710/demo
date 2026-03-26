package fr.eni.demo.heritage;

import fr.eni.demo.bo.Employe;
import fr.eni.demo.bo.formation.Cours;
import fr.eni.demo.bo.formation.Formateur;
import fr.eni.demo.dal.ChargeReeRepository;
import fr.eni.demo.dal.CoursRepository;
import fr.eni.demo.dal.EmployeRepository;
import fr.eni.demo.dal.FormateurRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@Slf4j
public class TestHeritage {

    @Autowired
    private FormateurRepository formateurRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private ChargeReeRepository chargeReeRepository;

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    EntityManager entityManager;

    private List<Cours> listeCours = new ArrayList<>();

    @BeforeEach
    void init_cours() {
        for (int i = 0; i < 3; i++) {
            Cours cours = Cours.builder()
                    .titre("TITRE" + i)
                    .reference("REF" + i)
                    .filiere("FIL" + i)
                    .build();

            Cours coursDB = coursRepository.save(cours);
            listeCours.add(coursDB);
            log.info(coursDB.toString());

            Assertions.assertThat(coursDB.getId()).isGreaterThan(0);
        }
    }

    @Test
    void test_save() {

        Formateur formateur = Formateur.builder()
                .nom("NOM")
                .prenom("PRENOM")
                .immatriculation("IMMAT")
                .email("email@campus-eni.fr")
                .build();

        formateur.setListeCours(listeCours);

        Formateur formateurDB = formateurRepository.save(formateur);

        log.info(formateurDB.toString());

        Assertions.assertThat(formateurDB.getId()).isGreaterThan(0);

        Assertions.assertThat(formateurDB.getListeCours().size()).isEqualTo(3);
    }
    @Test
    void test_create_employe(){
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



}
