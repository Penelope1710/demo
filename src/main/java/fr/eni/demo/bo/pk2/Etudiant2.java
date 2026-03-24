package fr.eni.demo.bo.pk2;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "STUDENT2")
public class Etudiant2 {

    @EmbeddedId
    private EtudiantPK2 pk;

    @Column(name = "LAST_NAME", nullable = false, length = 255)
    private String nom;

    @Column(name = "FIRST_NAME", nullable = false, length = 255)
    private String prenom;

    @Column(name = "HOME_PHONE_NUMBER", nullable = false, length = 255)
    private String numDom;

    @Column(name = "CELL_NUMBER", nullable = false, length = 255)
    private String numPortable;

}