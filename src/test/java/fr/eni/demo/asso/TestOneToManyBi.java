package fr.eni.demo.asso;

import fr.eni.demo.bo.stagiaire.DonneesPerso;
import fr.eni.demo.bo.stagiaire.EtudiantEni;
import fr.eni.demo.bo.stagiaire.Promo;
import fr.eni.demo.dal.EtudiantEniRepository;
import fr.eni.demo.dal.PromoRepository;
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
public class TestOneToManyBi {

    @Autowired
    private PromoRepository promoRepository;

    @Autowired
    private EtudiantEniRepository etudiantEniRepository;

    @Autowired
    private EntityManager entityManager;


    @Test
    void test_save(){

        Promo promo = Promo.builder()
                .nom("TEST1")
                .build();

        for (int i = 0 ; i <3 ; i++) {
            EtudiantEni etudiantEni = EtudiantEni.builder()
                    .email("email" + i + "@campus-eni.fr")
                    .immatriculation("TEST" + i )
                    .build();

            DonneesPerso donneesPerso = DonneesPerso.builder()
                    .nom("Nom" + i )
                    .prenom("prenom" + i )
                    .numDom("0278985621")
                    .build();

            etudiantEni.setDonneesPerso(donneesPerso);
            donneesPerso.setEtudiantEni(etudiantEni);

            etudiantEni.setPromo(promo);
            promo.getEtudiantEnis().add(etudiantEni);
        }

        Promo promoDB = promoRepository.save(promo);
        promoRepository.flush();

        log.info(promo.toString());


        Assertions.assertThat(promoDB.getId()).isGreaterThan(0);

        List<EtudiantEni> etudiantEniList = etudiantEniRepository.findAll();

        etudiantEniList.forEach(e->log.info(e.toString()));


        Assertions.assertThat(etudiantEniList.size()).isEqualTo(3);

    }


    @Test
    void test_delete(){

        // etape 1 : création de la promotion et de ses étudiants
        Promo promo = Promo.builder()
                .nom("TEST1")
                .build();

        for (int i = 0 ; i <3 ; i++) {
            EtudiantEni etudiantEni = EtudiantEni.builder()
                    .email("email" + i + "@campus-eni.fr")
                    .immatriculation("TEST" + i )
                    .build();

            DonneesPerso donneesPerso = DonneesPerso.builder()
                    .nom("Nom" + i )
                    .prenom("prenom" + i )
                    .numDom("0278985621")
                    .build();

            etudiantEni.setDonneesPerso(donneesPerso);
            donneesPerso.setEtudiantEni(etudiantEni);


            etudiantEni.setPromo(promo);
            promo.getEtudiantEnis().add(etudiantEni);
        }

        Promo promoDB = promoRepository.save(promo);
        promoRepository.flush();
        entityManager.clear();

        // etape 2 : rechercher de la promo en BDD
        Optional<Promo> optionalPromo = promoRepository.findById(promoDB.getId());
        Assertions.assertThat(optionalPromo).isPresent();
        log.info(optionalPromo.get().toString());

        // étape 3 : rechercher de tous les étudiants
        List<EtudiantEni> listeEtudiantEnis = etudiantEniRepository.findAll();

        Assertions.assertThat(listeEtudiantEnis.size()).isEqualTo(3);

        listeEtudiantEnis.forEach(e->log.info(e.toString()));

        // étape 4 : suppression de la promo
        Integer idPromo = promoDB.getId();

        // On détache la promotion de chacun de nos étudiants avant de supprimer la promo
        //listeEtudiantEnis.forEach(e->e.setPromo(null));

        promoRepository.delete(optionalPromo.get());
        promoRepository.flush();
        entityManager.clear();

        // étape 5 : recherche de la promo
        Optional<Promo> optionalPromo2 = promoRepository.findById(idPromo);
        Assertions.assertThat(optionalPromo2).isEmpty();

        // étape 6 : recherche de tous les étudiants
        List<EtudiantEni> etudiantEniList = etudiantEniRepository.findAll();
        Assertions.assertThat(etudiantEniList.size()).isEqualTo(3);

    }

}