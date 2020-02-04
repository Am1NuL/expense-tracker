package com.thesis.expensetracker.services;

import com.thesis.expensetracker.model.Account;

public interface AccountService extends CrudService<Account, Long> {

    Account findByEmail(String email);
}
