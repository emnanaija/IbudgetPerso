package com.example.ibudgetproject.Controller;

import com.example.ibudgetproject.Entity.SpendingWallet;
import com.example.ibudgetproject.Services.SpendingWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class SpendingWalletController {

    @Autowired
    private SpendingWalletService walletService;

    // Endpoint pour créer un wallet
    @PostMapping
    public ResponseEntity<SpendingWallet> createWallet() {
        SpendingWallet wallet = walletService.createWallet();
        return ResponseEntity.status(201).body(wallet);
    }

    // Endpoint pour désactiver un wallet
    @PutMapping("/{id}/desactivate")
    public SpendingWallet deactivateWallet(@PathVariable Long id) {
        return walletService.deactivateWallet(id);


    }
    // Endpoint pour récupérer tous les wallets
    @GetMapping
    public ResponseEntity<List<SpendingWallet>> getAllWallets() {
        List<SpendingWallet> wallets = walletService.getAllWallets();
        return ResponseEntity.ok(wallets);
    }

}
