package com.github.yumengliu.crypto.service;

import com.github.yumengliu.crypto.model.Account;
import com.github.yumengliu.crypto.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UUID createAccount(String name, double usdBalance) {
        Account newAccount = new Account(name, usdBalance);
        accountRepository.save(newAccount);
        return newAccount.getId();
    }

    @Override
    public Account getAccount(UUID accountId) {
        return accountRepository.findById(accountId).orElseThrow();
    }
}
