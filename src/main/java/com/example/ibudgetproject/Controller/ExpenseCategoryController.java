package com.example.ibudgetproject.Controller;


import com.example.ibudgetproject.Entity.ExpenseCategory;
import com.example.ibudgetproject.Services.ExpenseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ExpenseCategoryController {

    @Autowired
    private ExpenseCategoryService categoryService;

    // ✅ Ajouter une catégorie
    @PostMapping
    public ExpenseCategory createCategory(@RequestBody ExpenseCategory category) {
        return categoryService.addCategory(category);
    }

    // ✅ Modifier une catégorie (sauf montantDepensé)
    @PutMapping("/{id}")
    public ExpenseCategory updateCategory(@PathVariable Long id, @RequestBody ExpenseCategory category) {
        return categoryService.updateCategory(id, category);
    }

    // ✅ Supprimer une catégorie
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    // ✅ Lister toutes les catégories
    @GetMapping
    public List<ExpenseCategory> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // ✅ Obtenir une catégorie par ID
    @GetMapping("/{id}")
    public ExpenseCategory getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
}
