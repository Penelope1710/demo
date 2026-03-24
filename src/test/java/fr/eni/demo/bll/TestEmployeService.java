package fr.eni.demo.bll;

import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.EmployeDAO;
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

@SpringBootTest
public class TestEmployeService {

    @Autowired
    private EmployeService employeService;

    @MockitoBean
    private EmployeDAO employeDAO;

    //tester l'ajout
    @Test
    void test_lireTousLesEmployes(){

        List<Employe> employes = new ArrayList<>();
        employes.add(Employe.builder()
                                .id(1).nom("Prenomtest").prenom("PrenomTest").email("email@campus-eni.fr")
                                .immatriculation("TEST2")
                                .numDom("02030103001").numPort("0601020344").build()
        );

        employes.add(Employe.builder()
                .id(2).nom("Prenomtest2").prenom("PrenomTest2").email("email2@campus-eni.fr")
                .immatriculation("TEST3")
                .numDom("02030103001").numPort("0601020344").build()
        );


        Mockito.when(employeDAO.findAll()).thenReturn(employes);

        List<Employe> listeEmployes = employeService.lireToutLesEmployes();

        Assertions.assertThat(listeEmployes.size()).isEqualTo(2);

    }

    @Test
    void test_ajouter_employeNull(){

        assertThrows(RuntimeException.class,
                ()->employeService.ajouter(null));

    }

    @Test
    void test_employeImmatriculationExistante(){

        Employe employe = Employe.builder()
                .id(1).nom("Prenomtest").prenom("PrenomTest").email("email@campus-eni.fr")
                .immatriculation("TEST2")
                .numDom("02030103001").numPort("0601020344").build();

        Optional<Employe> optionalEmploye = Optional.of(employe);
        Mockito.when(employeDAO.findByImmatriculation("TEST2")).thenReturn(optionalEmploye);

        assertThrows(RuntimeException.class,
                ()->employeService.ajouter(employe));

    }

}
