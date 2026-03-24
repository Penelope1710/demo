package fr.eni.demo.asso;

import fr.eni.demo.bo.stagiaire.DonneesPerso;
import fr.eni.demo.bo.stagiaire.EtudiantEni;
import fr.eni.demo.dal.DonneesPersoRepository;
import fr.eni.demo.dal.EtudiantEniRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@Slf4j
@DataJpaTest
public class TestOneToOneBi {

    @Autowired
    private EtudiantEniRepository etudiantEniRepository;

    @Autowired
    private DonneesPersoRepository donneesPersoRepository;

    @Test
    void test_save(){

        EtudiantEni etudiantEni = EtudiantEni.builder()
                .email("email1@campus-eni.fr")
                .immatriculation("TEST1")
                .build();

        DonneesPerso donneesPerso = DonneesPerso.builder()
                .nom("Nom1")
                .prenom("prenom1")
                .numDom("0278985621")
                .build();

        etudiantEni.setDonneesPerso(donneesPerso);
        donneesPerso.setEtudiantEni(etudiantEni);

        EtudiantEni etudiantEniDB = etudiantEniRepository.save(etudiantEni);

        log.info(etudiantEniDB.toString());

        Assertions.assertThat(etudiantEniDB.getDonneesPerso().getId()).isGreaterThan(0);
    }

}