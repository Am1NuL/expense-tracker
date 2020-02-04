package com.thesis.expensetracker.controller;

import com.thesis.expensetracker.model.Account;
import com.thesis.expensetracker.services.AccountService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login/validate")
    public Account validateLogin() {
        return new Account();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account register(@RequestBody Account account) {
        return accountService.save(account);
    }
}
