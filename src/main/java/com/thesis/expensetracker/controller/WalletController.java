package com.thesis.expensetracker.controller;

import com.thesis.expensetracker.model.Account;
import com.thesis.expensetracker.model.Wallet;
import com.thesis.expensetracker.services.AccountService;
import com.thesis.expensetracker.services.WalletService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class WalletController {

    private WalletService walletService;
    private AccountService accountService;

    public WalletController(WalletService walletService, AccountService accountService) {
        this.walletService = walletService;
        this.accountService = accountService;
    }

    @RequestMapping(value = "/wallets", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Wallet addWallet(@RequestBody Wallet wallet) {
        return walletService.save(wallet);
    }

    @GetMapping("/wallets")
    public List<Wallet> getWallets() {
        return walletService.findAll();
    }

    @GetMapping("/total-balance")
    public Integer getTotalBalance() {
        return walletService.getTotalBalance();
    }
}
