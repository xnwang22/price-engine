package com.barclay.tests.models;

/**
 * Created by robinwang on 9/17/16.
 * This model capture the product name (code)
 * and the market. The market is represented by supply and demand concatenated
 * this model is more flexible than using enums in case of there are more market indicators or
 * the levels are more detailed like "H, M, L"
 */

public class ProductMarket {
    private String name;
   private String market;

    public ProductMarket(String name, String market) {
        this.name = name;
        this.market = market;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductMarket that = (ProductMarket) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
