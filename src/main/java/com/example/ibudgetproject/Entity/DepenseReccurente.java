package com.example.ibudgetproject.Entity;
import com.example.ibudgetproject.Repository.ExpenseCategoryRepository;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepenseReccurente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private SpendingWallet wallet;

    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private ExpenseCategory categorie;

    private BigDecimal montant;

    private LocalDate dateDebut;
    private LocalDate dateFin; // Optionnel, pour arrêter la récurrence à une date précise

    @Enumerated(EnumType.STRING)
    private FrequenceDepense frequence;
}
