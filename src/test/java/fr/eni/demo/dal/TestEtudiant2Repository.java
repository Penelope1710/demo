package fr.eni.demo.dal;

import fr.eni.demo.bo.pk2.Etudiant2;
import fr.eni.demo.bo.pk2.EtudiantPK2;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class TestEtudiant2Repository {

    @Autowired
    private Etudiant2Repository etudiant2Repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void test_save(){

        EtudiantPK2 pk = EtudiantPK2.builder()
                .immatriculation("TEST1")
                .email("email1@campus-eni.fr")
                .build();

        Etudiant2 etudiant = Etudiant2.builder()
                .pk(pk)
                .nom("Nom1")
                .prenom("Prenom1")
                .numDom("0278985654")
                .numPortable("0678984565")
                .build();

        etudiant2Repository.save(etudiant);
        etudiant2Repository.flush();
        entityManager.clear();

        Optional<Etudiant2> optionalEtudiant = etudiant2Repository.findById(pk);

        Assertions.assertThat(optionalEtudiant).isPresent();

    }
}