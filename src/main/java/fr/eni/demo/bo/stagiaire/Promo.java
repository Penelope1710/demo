package fr.eni.demo.bo.stagiaire;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"etudiantEnis"})
@EqualsAndHashCode(exclude = {"etudiantEnis"})
@Builder

@Entity
@Table(name = "STUDENT_CLASS")
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 20)
    private String nom;

    @OneToMany(mappedBy = "promo", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private @Builder.Default List<EtudiantEni> etudiantEnis = new ArrayList<>();

    @PreRemove
    public void preRemove() {
        etudiantEnis.forEach(this::removeFromPromo);
    }

    public boolean removeFromPromo(EtudiantEni etudiantEni) {
        etudiantEni.setPromo(null);
        return true;
    }


}