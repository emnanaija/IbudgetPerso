package com.example.ibudgetproject.Controller;

import com.example.ibudgetproject.Services.FeteService;
import com.example.ibudgetproject.Services.GeminiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fetes")
public class FeteController {
    private static final Logger logger = LoggerFactory.getLogger(FeteController.class);

    private final FeteService feteService;

    @Autowired
    private GeminiService geminiService;

    public FeteController(FeteService feteService, GeminiService geminiService) {
        this.feteService = feteService;
        this.geminiService = geminiService;
    }


    /*@GetMapping("/{year}/{month}")
    public List<String> getFetesDuMois(@PathVariable int year, @PathVariable int month) {
        logger.info("📩 Requête reçue pour récupérer les fêtes du mois : Année={} Mois={}", year, month);

        List<String> fetes = feteService.getFetesDuMois(year, month);

        if (fetes.isEmpty()) {
            logger.warn("⚠️ Aucune fête trouvée pour {}/{}", month, year);
        } else {
            logger.info("✅ {} fêtes trouvées pour {}/{} : {}", fetes.size(), month, year, fetes);
        }

        return fetes;
    }*/
    @GetMapping("/{year}/{month}")
    public String getFetes(@PathVariable int year, @PathVariable int month) {
        List<String> fetes = feteService.getFetesDuMois(year, month);

        if (!fetes.isEmpty()) {
            String fete = fetes.get(0); // Prendre la première fête pour l'exemple
            // Créer le prompt pour Gemini
            String budgetPrompt = "proposer moi un budget pour la fete   " + fete +"en dinars tunisien (une estimation a peu pres selon ce que tu connais comme informations pour une famille de 5 personnes) reponds moi avec les montants et la description seulement";
            String cadeauxPrompt = "Quels sont des cadeaux populaires pour propose moi avec le montant " + fete + "?";

            // Demander des suggestions à Gemini
            String budgetSuggestions = geminiService.getSuggestions(budgetPrompt);
            String cadeauxSuggestions = geminiService.getSuggestions(cadeauxPrompt);

            return "Suggestions pour " + fete + " : \n" +
                    "Budget : " + budgetSuggestions + "\n" +
                    "Cadeaux : " + cadeauxSuggestions;
        } else {
            return "Aucune fête trouvée pour " + month + "/" + year;
        }
    }
}
