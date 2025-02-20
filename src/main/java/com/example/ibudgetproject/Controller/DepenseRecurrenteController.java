package com.example.ibudgetproject.Controller;

import com.example.ibudgetproject.Entity.DepenseReccurente;
import com.example.ibudgetproject.Services.DepenseReccurenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/depenses-recurrentes")
@RequiredArgsConstructor
public class DepenseRecurrenteController {

    private final DepenseReccurenteService depenseRecurrenteService;

    @PostMapping
    public ResponseEntity<DepenseReccurente> ajouterDepense(@RequestBody DepenseReccurente depense) {
        return ResponseEntity.ok(depenseRecurrenteService.ajouterDepense(depense));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepenseReccurente> modifierDepense(@PathVariable Long id, @RequestBody DepenseReccurente depense) {
        return ResponseEntity.ok(depenseRecurrenteService.modifierDepense(id, depense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerDepense(@PathVariable Long id) {
        depenseRecurrenteService.supprimerDepense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DepenseReccurente>> listerDepenses() {
        return ResponseEntity.ok(depenseRecurrenteService.listerDepenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepenseReccurente> getDepense(@PathVariable Long id) {
        return ResponseEntity.ok(depenseRecurrenteService.getDepenseById(id));
    }


    @PostMapping("/traiter")
    public ResponseEntity<String> traiterDepensesRecurrentes() {
        depenseRecurrenteService.traiterDepensesRecurrentes();
        return ResponseEntity.ok("Traitement des dépenses récurrentes effectué.");
    }
}
