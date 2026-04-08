package com.mjaquino.service;

import com.mjaquino.model.Account;
import com.mjaquino.service.MatchResultService;

import java.util.List;
// handles the account 
public class AccountService {
    // for signing up
    public Account signUp(String username, String password) {
        // will be reject if user type/ entered blank value
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return null;
        }

        List<Account> accounts = MatchResultService.loadAccounts();

        // check duplicate username
        for (Account account : accounts) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                return null;
            }
        }

        // create and save new account
        Account newAccount = new Account(username, password);
        accounts.add(newAccount);
        MatchResultService.saveAccounts(accounts);
        return newAccount;
    }

    //for logging in
    public Account login(String username, String password) {
        // will be reject if user type/ entered blank value 
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return null;
        }

        List<Account> accounts = MatchResultService.loadAccounts();

        // find matching account
        for (Account account : accounts) {
            if (account.getUsername().equalsIgnoreCase(username)
                    && account.getPassword().equals(password)) {
                return account;
            }
        }

        return null;
    }

    public void updateAccount(Account updatedAccount) {
        List<Account> accounts = MatchResultService.loadAccounts();

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equalsIgnoreCase(updatedAccount.getUsername())) {
                accounts.set(i, updatedAccount);
                MatchResultService.saveAccounts(accounts);
                return;
            }
        }

        // add if not found
        accounts.add(updatedAccount);
        MatchResultService.saveAccounts(accounts);
    }
}