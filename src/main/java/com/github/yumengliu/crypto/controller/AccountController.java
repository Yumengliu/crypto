package com.github.yumengliu.crypto.controller;

import com.github.yumengliu.crypto.model.Account;
import com.github.yumengliu.crypto.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account")
    public UUID createAccount(@RequestBody Map<String, String> jsonBody) {
        return accountService.createAccount(jsonBody.get("name"), Double.parseDouble(jsonBody.get("usd_balance")));
    }

    @GetMapping("/account/{accountId}")
    public Account getAccount(@PathVariable UUID accountId) {
        return accountService.getAccount(accountId);
    }
}
