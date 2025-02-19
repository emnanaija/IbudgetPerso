package com.example.ibudgetproject.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCategory {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String nom;

        private String description; // Description facultative de la catégorie

        @Column(nullable = false)
        private double budgetAlloué; // Budget alloué pour cette catégorie

        @Column(nullable = false)
        private double montantDepensé = 0.0;

        @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)

        private List<Depense> depenses;


}
