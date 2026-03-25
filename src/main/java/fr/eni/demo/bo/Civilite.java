package fr.eni.demo.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "CIVILITY")
public class Civilite {

    @Id
    private String cle;

    @Column(name = "LABEL", nullable = false, length = 20)
    private String libelle;

}
