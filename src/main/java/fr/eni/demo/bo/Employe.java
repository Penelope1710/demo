package fr.eni.demo.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"immatriculation"})
@SuperBuilder

@Entity
@Table(name = "EMPLOYEE")
//une table par classe avec une clé étrangère
@Inheritance(strategy = InheritanceType.JOINED)
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Integer id;

    @NotBlank(message = "{employee.ln.blank-error}")
    @Size(max = 90, message = "{employee.ln.size-error}")
    @Column(name = "LAST_NAME", length = 90, nullable = false)
    private String nom;

    @NotBlank(message = "{employee.ln.blank-error}")
    @Size(max = 150, message = "{employee.ln.size-error}")
    @Column(name = "FIRST_NAME", length = 150, nullable = false)
    private String prenom;

    @NotBlank(message = "{employee.ln.blank-error}")
    @Size(max = 255, message = "{employee.ln.size-error}")
    @Email
    @Column(name = "EMAIL", length = 255, nullable = false, unique = true)
    private String email;

    @NotBlank(message = "{employee.ln.blank-error}")
    @Size(max = 90, message = "{employee.ln.size-error}")
    @Column(name = "EMPLOYEE_REGISTRATION", length = 90, nullable = false, unique = true)
    private String immatriculation;

    @Size(max = 12)
    @Column(name = "HOME_PHONE_NUBLE", length = 12)
    private String numDom;

    @Size(max = 12)
    @Column(name = "CELL_PHONE_NUBLE", length = 12)
    private String numPort;

    @NotNull(message = "{employee.address.error}")
    //quand je vais créer ou supprimer un employe, son adresse sera automatiquement créée ou supprimée
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID")
    private Adresse adresse;

    @NotNull(message = "{employee.civilite.error}")
    //pas de cascade
    @ManyToOne
    @JoinColumn(name = "CIVILITY_ID")
    private Civilite civilite;
}
