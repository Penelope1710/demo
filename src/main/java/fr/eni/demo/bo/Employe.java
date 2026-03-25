package fr.eni.demo.bo;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"immatriculation"})
@Builder

@Entity
@Table(name = "EMPLOYEE")
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Integer id;

    @Column(name = "LAST_NAME", length = 90, nullable = false)
    private String nom;

    @Column(name = "FIRST_NAME", length = 150, nullable = false)
    private String prenom;

    @Column(name = "EMAIL", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "EMPLOYEE_REGISTRATION", length = 90, nullable = false, unique = true)
    private String immatriculation;

    @Column(name = "HOME_PHONE_NUBLE", length = 12)
    private String numDom;

    @Column(name = "CELL_PHONE_NUBLE", length = 12)
    private String numPort;

    //quand je vais créer ou supprimer un employe, son adresse sera automatiquement créée ou supprimée
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID")
    private Adresse adresse;

    //pas de cascade
    @ManyToOne
    @JoinColumn(name = "CIVILITY_ID")
    private Civilite civilite;

}
