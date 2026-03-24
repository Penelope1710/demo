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
}
