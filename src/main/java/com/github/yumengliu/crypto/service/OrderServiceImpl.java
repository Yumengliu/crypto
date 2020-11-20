package com.github.yumengliu.crypto.service;

import com.github.yumengliu.crypto.model.Account;
import com.github.yumengliu.crypto.model.BtcOrder;
import com.github.yumengliu.crypto.repository.AccountRepository;
import com.github.yumengliu.crypto.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    @Value("${TRADING_PERCENTAGE: 1.0}")
    private double trading_percentage;
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final AccountRepository accountRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(AccountRepository accountRepository, OrderRepository orderRepository) {
        this.accountRepository = accountRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public UUID createOrder(UUID accountId, double priceLimit) {
        BtcOrder newBtcOrder = new BtcOrder(priceLimit);
        Account account = accountRepository.findById(accountId).orElseThrow();
        account.addOrder(newBtcOrder);
        orderRepository.save(newBtcOrder);
        return newBtcOrder.getId();
    }

    @Override
    public BtcOrder getOrder(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

    @Override
    public void executeOrdersOnPrice(double price) {
        orderRepository.findAllByNotExecutedAndLowerPriceLimit(price).forEach(o -> executeOnSingleOrder(o, price));
    }

    private void executeOnSingleOrder(BtcOrder btcOrder, double price) {
        log.info("execute on order: " + btcOrder.getId());
        btcOrder.setExecuted(true);
        Account account = btcOrder.getAccount();

        double moneyToBuyBtc = account.getUsdBalance() * trading_percentage;
        double btcAmount = moneyToBuyBtc / price;

        account.buyBtc(btcAmount, moneyToBuyBtc);
        orderRepository.save(btcOrder);
        accountRepository.save(account);
    }
}
