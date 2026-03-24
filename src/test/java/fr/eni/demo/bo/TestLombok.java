package fr.eni.demo.bo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class TestLombok {

    @Test
    public void test_employe_tousLesAttributs() {
        Employe e1 = Employe.builder()
                .nom("NomTest")
                .prenom("PrenomTest")
                .email("emailTest@campus-eni.fr")
                .numPort("0606060606")
                .numDom("0201030506")
                .immatriculation("TEST01")
                .build();

        log.info(e1.toString());

        assertThat(e1.getNom()).isEqualTo("NomTest");
        assertThat(e1.getPrenom()).isEqualTo("PrenomTest");
    }

}
