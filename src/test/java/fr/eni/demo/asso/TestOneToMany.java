package fr.eni.demo.asso;

import fr.eni.demo.bo.stagiaire.Promo;
import fr.eni.demo.bo.stagiaire.DonneesPerso;
import fr.eni.demo.bo.stagiaire.EtudiantEni;
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

@DataJpaTest
@Slf4j
public class TestOneToMany {

    @Autowired
    private PromoRepository promoRepository;

    @Autowired
    private EtudiantEniRepository etudiantEniRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void test_save() {
        Promo promo = Promo.builder()
                .nom("TEST1")
                .build();

        for (int i = 0; i < 3; i++) {
            EtudiantEni etudiantEni = EtudiantEni.builder()
                    .email("email" + i + "@campus-eni.fr")
                    .immatriculation("TEST" + i)
                    .build();

            DonneesPerso donneesPerso = DonneesPerso.builder()
                    .nom("Nom1" + i)
                    .prenom("prenom1" + i)
                    .numDom("0278985621")
                    .build();

       //ça va dans les 2 sens car on a une relation bi-directionnelle
        etudiantEni.setDonneesPerso(donneesPerso);
        donneesPerso.setEtudiantEni(etudiantEni);

        promo.getEtudiantEnis().add(etudiantEni);
        }

        Promo promoDB = promoRepository.save(promo);
        promoRepository.flush();

        Assertions.assertThat(promoDB.getId()).isGreaterThan(0);

        List<EtudiantEni> etudiantEniList = etudiantEniRepository.findAll();

        Assertions.assertThat(etudiantEniList.size()).isEqualTo(3);

    }

    @Test
    void delete() {

        Promo promo = Promo.builder()
                .nom("TEST1")
                .build();

        for (int i = 0; i < 3; i++) {
            EtudiantEni etudiantEni = EtudiantEni.builder()
                    .email("email" + i + "@campus-eni.fr")
                    .immatriculation("TEST" + i)
                    .build();

            DonneesPerso donneesPerso = DonneesPerso.builder()
                    .nom("Nom1" + i)
                    .prenom("prenom1" + i)
                    .numDom("0278985621")
                    .build();

            etudiantEni.setDonneesPerso(donneesPerso);
            donneesPerso.setEtudiantEni(etudiantEni);
        }
            Promo promoDB = promoRepository.save(promo);
            promoRepository.flush();
            Assertions.assertThat(promoDB.getId()).isGreaterThan(0);

            Integer idPromo = promoDB.getId();
            promoRepository.delete(promoDB);
            promoRepository.flush();

            Optional<Promo> optionalPromo = promoRepository.findById(idPromo);

            Assertions.assertThat(optionalPromo).isEmpty();
        }

}
