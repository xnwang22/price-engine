package com.barclay.tests;

import com.barclay.tests.models.PriceDetail;
import com.barclay.tests.models.ProductMarket;

import java.util.Arrays;
import java.util.List;

/**
 * Created by robinwang on 9/17/16.
 */
public class TestInputHelper {
    public static List<PriceDetail> getProductDetailList() {

        return Arrays.asList(
                new PriceDetail("flashdrive", "X", 1.0),
                new PriceDetail("ssd", "X", 10.0),
                new PriceDetail("flashdrive", "Y", 0.9),
                new PriceDetail("flashdrive","Z", 1.1),
                new PriceDetail("ssd", "Y", 12.5)
        );
    }
    public static ProductMarket getProductMarket() {

        return new ProductMarket("ssd", "LH");
    }

    public static List<PriceDetail> getProductDetailListComplicated() {
        return Arrays.asList(
                new PriceDetail("ssd", "X", 11.0),
                new PriceDetail("ssd", "W", 10.0),
                new PriceDetail("ssd", "V", 12.0),
                new PriceDetail("ssd", "Z", 11.0),
                new PriceDetail("ssd", "Y", 12.0)
        );
    }
}
