package fr.eni.demo.dal;

import fr.eni.demo.bo.pk.Etudiant;
import fr.eni.demo.bo.pk.EtudiantPK;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class TestEtudiantRepository {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void test_save(){

        Etudiant etudiant = Etudiant.builder()
                .email("email1@campus-eni.fr")
                .immatriculation("TEST1")
                .nom("Nom1")
                .prenom("Prenom1")
                .numDom("0278985654")
                .numPortable("0678984565")
                .build();

        etudiantRepository.save(etudiant);
        etudiantRepository.flush();
        entityManager.clear();


        EtudiantPK pk = EtudiantPK.builder()
                .email("email1@campus-eni.fr")
                .immatriculation("TEST1")
                .build();
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(pk);

        Assertions.assertThat(optionalEtudiant).isPresent();

    }
}