package com.github.yumengliu.crypto.task;

import com.github.yumengliu.crypto.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PriceCheckerTask {

    private static final Logger log = LoggerFactory.getLogger(PriceCheckerTask.class);

    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    private final OrderService orderService;

    @Autowired
    public PriceCheckerTask(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedDelay = 1000)
    public void fetchPriceAndExecuteOrders() {
        log.info("fetching");
        PriceResponse response = restTemplate.getForObject("http://127.0.0.1:5000/btc-price", PriceResponse.class);
        if (response == null) {
            log.info("null response");
            return;
        }
        double price = response.getPrice();
        log.info("price is : " + price);
        orderService.executeOrdersOnPrice(price);
    }
}
