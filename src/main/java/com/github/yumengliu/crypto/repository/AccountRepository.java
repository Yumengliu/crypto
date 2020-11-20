package com.github.yumengliu.crypto.repository;

import com.github.yumengliu.crypto.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}
