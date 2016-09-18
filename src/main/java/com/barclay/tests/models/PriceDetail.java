package com.barclay.tests.models;

/**
 * Created by robinwang on 9/17/16.
 * This model capture the input of
 * product code, competitor and their price
 */

public class PriceDetail {
    private String code;
    private String competitor;
    private double price;

    public PriceDetail(String code, String competitor, double price) {
        this.code = code;
        this.competitor = competitor;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompetitor() {
        return competitor;
    }

    public void setCompetitor(String competitor) {
        this.competitor = competitor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
