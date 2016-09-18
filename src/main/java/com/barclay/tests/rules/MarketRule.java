package com.barclay.tests.rules;

/**
 * Created by robinwang on 9/18/16.
 */
@FunctionalInterface
interface MarketRule {
    double operation(double a);
}
