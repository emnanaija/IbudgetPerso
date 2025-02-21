package com.example.ibudgetproject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Depense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double montant; // Montant de la dépense

    @Column(nullable = true)
    private LocalDate date; // Date de la dépense

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EtatDepense etat; // État de la dépense (Prévue ou Réalisée)

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false) // Une dépense appartient toujours à un Wallet
    @JsonBackReference
    private SpendingWallet wallet;

    @ManyToOne
    @JsonBackReference

    @JoinColumn(name = "category_id", nullable = true) // Une dépense peut être associée à une catégorie (facultatif)
    private ExpenseCategory category;
    @Column(name = "photo_url", nullable = true)
    private String photoUrl;
}
