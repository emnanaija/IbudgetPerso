package com.example.ibudgetproject.Repository;

import com.example.ibudgetproject.Entity.Depense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepenseRepository extends JpaRepository<Depense, Long> {
    List<Depense> findByWalletId(Long walletId);


}
