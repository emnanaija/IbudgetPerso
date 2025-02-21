package com.example.ibudgetproject.Services;

import com.example.ibudgetproject.Entity.ExpenseCategory;
import com.example.ibudgetproject.Repository.ExpenseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpenseCategoryService {

    @Autowired
    private ExpenseCategoryRepository categoryRepository;

    // ✅ Ajouter une catégorie (montantDepensé est toujours 0.0)
    public ExpenseCategory addCategory(ExpenseCategory category) {
        if (category.getNom() == null || category.getNom().isEmpty()) {
            throw new RuntimeException("Le nom de la catégorie ne peut pas être vide !");
        }
        if (categoryRepository.existsByNom(category.getNom())) {
            throw new RuntimeException("Une catégorie avec ce nom existe déjà !");
        }
        category.setMontantDepensé(0.0); // Assurer que ce champ est toujours 0
        return categoryRepository.save(category);
    }


    // ✅ Modifier une catégorie (⚠️ montantDepensé NE DOIT PAS être modifié)
    public ExpenseCategory updateCategory(Long id, ExpenseCategory categoryDetails) {
        Optional<ExpenseCategory> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            ExpenseCategory category = existingCategory.get();
            category.setNom(categoryDetails.getNom());
            category.setDescription(categoryDetails.getDescription());
            category.setBudgetAlloué(categoryDetails.getBudgetAlloué());
            // ❌ Ne pas modifier montantDepensé ici
            return categoryRepository.save(category);
        } else {
            throw new RuntimeException("Catégorie non trouvée !");
        }
    }

    // ✅ Supprimer une catégorie
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Catégorie non trouvée !");
        }
        categoryRepository.deleteById(id);
    }

    // ✅ Récupérer toutes les catégories
    public List<ExpenseCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    // ✅ Récupérer une catégorie par ID
    public ExpenseCategory getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée !"));
    }



    public List<Map<String, Object>> getSoldeRestantParCategorie() {
        List<ExpenseCategory> categories = categoryRepository.findAll();
        List<Map<String, Object>> resultat = new ArrayList<>();

        for (ExpenseCategory categorie : categories) {
            Map<String, Object> categorieData = new HashMap<>();
            categorieData.put("id", categorie.getId());
            categorieData.put("nom", categorie.getNom());
            categorieData.put("budget_alloué", categorie.getBudgetAlloué());
            categorieData.put("montant_dépensé", categorie.getMontantDepensé());
            categorieData.put("solde_restant", categorie.getSoldeRestant()); // Calcul automatique

            resultat.add(categorieData);
        }
        return resultat;
    }
}
