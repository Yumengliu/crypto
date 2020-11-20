package com.github.yumengliu.crypto.task;

import java.util.Date;

public class PriceResponse {

    private double price;
    private Date timestamp;

    public PriceResponse() {
    }

    public PriceResponse(double price, Date timestamp) {
        this.price = price;
        this.timestamp = timestamp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
