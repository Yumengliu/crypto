package com.github.yumengliu.crypto.service;

import com.github.yumengliu.crypto.model.Account;

import java.util.UUID;

public interface AccountService {

    UUID createAccount(String name, double usdBalance);

    Account getAccount(UUID accountId);

}
