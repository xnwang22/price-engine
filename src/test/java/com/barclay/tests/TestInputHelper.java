package com.barclay.tests;

import com.barclay.tests.models.ProductDetail;
import com.barclay.tests.models.ProductMarket;

import java.util.Arrays;
import java.util.List;

/**
 * Created by robinwang on 9/17/16.
 */
public class TestInputHelper {
    public static List<ProductDetail> getProductDetailList() {

        return Arrays.asList(
                new ProductDetail("flashdrive", "X", 1.0),
                new ProductDetail("ssd", "X", 10.0),
                new ProductDetail("flashdrive", "Y", 0.9),
                new ProductDetail("flashdrive","Z", 1.1),
                new ProductDetail("ssd", "Y", 12.5)
        );
    }
    public static ProductMarket getProductMarket() {

        return new ProductMarket("ssd", "LH");
    }

    public static List<ProductDetail> getProductDetailListComplicated() {
        return Arrays.asList(
                new ProductDetail("ssd", "X", 11.0),
                new ProductDetail("ssd", "W", 10.0),
                new ProductDetail("ssd", "V", 12.0),
                new ProductDetail("ssd", "Z", 11.0),
                new ProductDetail("ssd", "Y", 12.0)
        );
    }
}
