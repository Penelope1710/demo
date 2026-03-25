package fr.eni.demo.bo.stagiaire;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

@Entity
@Table(name = "ENI_STUDENT")
public class EtudiantEni {

    @Id
    private String email;

    @Column(name = "REGISTRATION", unique = true, nullable = false, length = 255)
    private String immatriculation;

    //classe propriétaire de la relation, je mets donc la FK ici
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "DATA_ID")
    private DonneesPerso donneesPerso;

    //pas de cascade (si je supprime un étudiant je ne veux pas supprimer sa promo)
    // mais je veux remonter sa promoton avec son nom
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLASS_ID")
    private Promo promo;
}
