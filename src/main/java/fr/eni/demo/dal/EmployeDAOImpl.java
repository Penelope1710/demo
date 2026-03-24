package fr.eni.demo.dal;

import fr.eni.demo.bo.Employe;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeDAOImpl implements EmployeDAO{

    //pour pouvoir l'utiliser dans toute la classe
    private  static List<Employe> listeEmployes = new ArrayList<>();

    @Override
    public void create(Employe e) {
        listeEmployes.add(e);
    }

    @Override
    public Optional<Employe> read(Integer id) {
        Optional<Employe> optionalEmploye = listeEmployes
                .stream()
                .filter(e->e.getId().equals(id))
                .findAny();
        return Optional.empty();
    }

    @Override
    public Optional<Employe> findByImmatriculation(String immatriculation) {
        Optional<Employe> optionalEmploye = listeEmployes
                .stream()
                .filter(e -> e.getImmatriculation().equals(immatriculation))
                .findAny();
        return Optional.empty();
    }

    @Override
    public List<Employe> findAll() {
        return List.of();
    }

    @Override
    public void update(Employe e) {
        Optional<Employe> optionalEmploye = read(e.getId());
        if (optionalEmploye.isPresent()) {
            Employe employe = optionalEmploye.get();
            employe.setEmail(e.getEmail());
            employe.setNom(e.getNom());
            employe.setPrenom(e.getPrenom());
            //....
        }
    }

    @Override
    public void delete(Employe e) {
        listeEmployes.remove(e);

    }
}
