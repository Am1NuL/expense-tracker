package com.thesis.expensetracker.repository;

import com.thesis.expensetracker.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
