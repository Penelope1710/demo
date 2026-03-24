package fr.eni.demo.bll;

import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.AdresseRepository;
import fr.eni.demo.dal.EmployeRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
public class TestEmployeService {

    @Autowired
    private EmployeService employeService;

    @Autowired
    @MockitoBean
    private EmployeRepository employeRepository;


    @Autowired
    @MockitoBean
    private AdresseRepository adresseRepository;

    @Test
    void test_lireTousLesEmployes(){

        List<Employe> employes = new ArrayList<>();
        employes.add(Employe.builder()
                .id(1)
                .nom("NomTest1")
                .prenom("PrenomTest1")
                .email("email1@campus-eni.fr").
                immatriculation("TEST1")
                .numDom("0278541232")
                .numPort("067854122")
                .build()
        );
        employes.add(Employe.builder()
                .id(2)
                .nom("NomTest2")
                .prenom("PrenomTest2")
                .email("email2@campus-eni.fr").
                immatriculation("TEST2")
                .numDom("0278541233")
                .numPort("067854123")
                .build()
        );

        Mockito.when(employeRepository.findAll()).thenReturn(employes);

        List<Employe> listeEmployes = employeService.lireTousLesEmployes();

        Assertions.assertThat(listeEmployes.size()).isEqualTo(2);

    }

    @Test
    void test_ajouter_employeNull(){

        assertThrows(
                RuntimeException.class,
                ()->employeService.ajouter(null));
    }


    @Test
    void test_ajouter_employeImmatriculationExistante(){
        Employe employe = Employe.builder()
                .id(1)
                .nom("NomTest1")
                .prenom("PrenomTest1")
                .email("email1@campus-eni.fr").
                immatriculation("TEST1")
                .numDom("0278541232")
                .numPort("067854122")
                .build();
        Optional<Employe> optionalEmploye = Optional.of(employe);

        //TODO
        //Mockito.when(employeDAO.findByImmatriculation("TEST1")).thenReturn(optionalEmploye);

        assertThrows(
                RuntimeException.class,
                ()->employeService.ajouter(employe));
    }







}