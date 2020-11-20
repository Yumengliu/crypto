package com.github.yumengliu.crypto.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.*;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private double usdBalance;
    private double btc;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BtcOrder> btcOrders = new ArrayList<>();

    protected Account() {
    }

    public Account(String name, double usdBalance) {
        this.name = name;
        this.usdBalance = usdBalance;
        this.btc = 0.0;
    }

    public void addOrder(BtcOrder btcOrder) {
        this.btcOrders.add(btcOrder);
        btcOrder.setAccount(this);
    }

    public void buyBtc(double amount, double cost) {
        btc += amount;
        usdBalance -= cost;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUsdBalance() {
        return usdBalance;
    }

    public void setUsdBalance(double usdBalance) {
        this.usdBalance = usdBalance;
    }

    public double getBtc() {
        return btc;
    }

    public void setBtc(double btc) {
        this.btc = btc;
    }

    public List<BtcOrder> getBtcOrders() {
        return btcOrders;
    }

    public void setBtcOrders(List<BtcOrder> btcOrders) {
        this.btcOrders = btcOrders;
    }
}
