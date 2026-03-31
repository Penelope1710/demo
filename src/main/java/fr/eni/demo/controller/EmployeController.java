package fr.eni.demo.controller;

import fr.eni.demo.bll.EmployeService;
import fr.eni.demo.bo.Employe;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/eniecole/employes")
public class EmployeController {

    //ça fait parti des bonnes pratiques d'injecter par constructeur
    private EmployeService employeService;

    @GetMapping
    public ResponseEntity<?> rechercherTousLesEmployes() {

        List<Employe> listeEmploye = employeService.lireTousLesEmployes();
        if (listeEmploye!= null && !listeEmploye.isEmpty()) {
            return ResponseEntity.ok(listeEmploye);
        }
        return ResponseEntity.noContent().build();
    }
}
