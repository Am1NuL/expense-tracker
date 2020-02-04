package com.thesis.expensetracker.repository;

import com.thesis.expensetracker.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query(value = "SELECT SUM(total_wealth) FROM wallets", nativeQuery = true)
    Integer getTotalBalance();
}
