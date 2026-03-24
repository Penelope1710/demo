package fr.eni.demo.bo.pk;


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
@Table(name = "STUDENT")
@IdClass(EtudiantPK.class)
public class Etudiant {

    @Id
    @Column(name = "EMAIL", nullable = false, length = 255)
    private String email;

    @Id
    @Column(name = "STUDENT_REGISTRATION", nullable = false, length = 255)
    private String immatriculation;

    @Column(name = "LAST_NAME", nullable = false, length = 255)
    private String nom;

    @Column(name = "FIRST_NAME", nullable = false, length = 255)
    private String prenom;

    @Column(name = "HOME_PHONE_NUMBER", nullable = false, length = 255)
    private String numDom;

    @Column(name = "CELL_NUMBER", nullable = false, length = 255)
    private String numPortable;



}