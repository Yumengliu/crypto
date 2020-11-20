package com.github.yumengliu.crypto.service;

import com.github.yumengliu.crypto.model.BtcOrder;

import java.util.UUID;

public interface OrderService {

    UUID createOrder(UUID accountId, double priceLimit);

    BtcOrder getOrder(UUID orderId);

    void executeOrdersOnPrice(double price);
}
