package fr.eni.demo.bo.formation;

import fr.eni.demo.bo.Employe;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
//builder de la classe mère
@SuperBuilder
@Getter
@Setter
//appel à la table mère pour ces 2 méthodes, on n'utilisera pas le @Data à cause de l'héritage
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = "TRAINER")
public class Formateur extends Employe {

    @ManyToMany()
    @JoinTable(name = "COMPUTER_COURSES_PROVIDED", joinColumns = {@JoinColumn(name = "TRAINER_ID")},
    inverseJoinColumns = {@JoinColumn(name = "COURSE_ID")})
    //nom de la clé étrangère

    private @Builder.Default List<Cours> listeCours = new ArrayList<>();
}
