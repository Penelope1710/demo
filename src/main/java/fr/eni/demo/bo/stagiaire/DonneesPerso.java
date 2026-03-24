package fr.eni.demo.bo.stagiaire;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
//pour éviter lesboucles infinies
@ToString(exclude = {"etudiantEni"})
@EqualsAndHashCode(exclude = {"etudiantEni"})

@Entity
@Table(name = "STUDENT_DATA")
public class DonneesPerso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LAST_NAME", nullable = false, length = 255)
    private String nom;

    @Column(name = "FIRST_NAME", nullable = false, length = 250)
    private String prenom;

    @Column(name = "PHONE_NUMBER", nullable = false, length = 10)
    private String numDom;

    //classe non propriétaire de la relation
    @OneToOne(mappedBy = "donneesPerso")
    private EtudiantEni etudiantEni;
}
