package com.thesis.expensetracker.security;

import com.thesis.expensetracker.model.Account;
import com.thesis.expensetracker.services.AccountService;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@NoArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private AccountService accountService;

    public UserDetailsServiceImpl(AccountService accountService) {
        super();
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountService.findByEmail(email);

        if(account == null) {
            throw new UsernameNotFoundException(email);
        }

        return new UserPrincipal(account);
    }
}
