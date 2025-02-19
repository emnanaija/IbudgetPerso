package com.example.ibudgetproject.Controller;

import com.example.ibudgetproject.Services.FeteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fetes")
public class FeteController {
    private static final Logger logger = LoggerFactory.getLogger(FeteController.class);

    private final FeteService feteService;

    public FeteController(FeteService feteService) {
        this.feteService = feteService;
    }

    @GetMapping("/{year}/{month}")
    public List<String> getFetesDuMois(@PathVariable int year, @PathVariable int month) {
        logger.info("📩 Requête reçue pour récupérer les fêtes du mois : Année={} Mois={}", year, month);

        List<String> fetes = feteService.getFetesDuMois(year, month);

        if (fetes.isEmpty()) {
            logger.warn("⚠️ Aucune fête trouvée pour {}/{}", month, year);
        } else {
            logger.info("✅ {} fêtes trouvées pour {}/{} : {}", fetes.size(), month, year, fetes);
        }

        return fetes;
    }
}
