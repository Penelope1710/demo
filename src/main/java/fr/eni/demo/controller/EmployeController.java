package fr.eni.demo.controller;

import fr.eni.demo.bll.EmployeService;
import fr.eni.demo.bo.Employe;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    // c'est String car une url est toujours en String et ensuite on convertit
    public ResponseEntity<?> rechercherUnEmploye(@PathVariable String idInPath) {

        //convertir en Integer
        try {
            Integer id = Integer.parseInt(idInPath);
            Employe e = employeService.lire(id);
            return ResponseEntity.ok(e);

        }catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'identifiant doit être un entier");
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping
    //pour récupérer le flux json : requestBody
    public ResponseEntity<?> ajouteEmploye(@Valid @RequestBody Employe employee) {

        try {
            employeService.ajouter(employee);
            return ResponseEntity.ok(employee);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
}
