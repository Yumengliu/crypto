package com.github.yumengliu.crypto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Entity
public class BtcOrder {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne()
    @JoinColumn(name="account_id", nullable=false)
    @JsonBackReference
    private Account account;

    private double priceLimit;
    private boolean executed;
    private Date placeTime;
    private Date executeTime;

    protected BtcOrder() {
    }

    public BtcOrder(double priceLimit) {
        this.priceLimit = priceLimit;
        this.executed = false;
        placeTime = Date.from(Instant.now());
        executeTime = null;
    }

    public double getPriceLimit() {
        return priceLimit;
    }

    public void setPriceLimit(double priceLimit) {
        this.priceLimit = priceLimit;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
        if (executed) {
            setExecuteTime(Date.from(Instant.now()));
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(Date placeTime) {
        this.placeTime = placeTime;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }
}
