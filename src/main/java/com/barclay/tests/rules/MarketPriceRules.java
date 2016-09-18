package com.barclay.tests.rules;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by robinwang on 9/17/16.
 */
public class MarketPriceRules {
    private static Map<String, MarketRule> ruleMap;
    static MarketRule noChange = (double a) -> {return a;};
    static MarketRule increaseTenPercent = (double a) -> a * 1.10;
    static MarketRule increaseFivePercent = (double a) -> a * 1.05;
    static MarketRule decreaseFivePercent = (double a) -> a * 0.95;
    static {
        ruleMap = new HashMap<>();
        ruleMap.put("HH", noChange);
        ruleMap.put("LL", increaseTenPercent);
        ruleMap.put("LH", increaseFivePercent);
        ruleMap.put("HL", decreaseFivePercent);
    }
    public static double applyRule(String market, double price)
    {
        return ruleMap.get(market).operation(price);
    }
}
//public class MarketPriceRules {
//    private static Map<String, Double> ruleMap;
//
//    static {
//        ruleMap = new HashMap<>();
//        ruleMap.put("HH", 1.0);
//        ruleMap.put("LL", 1.10);
//        ruleMap.put("LH", 1.05);
//        ruleMap.put("HL", 0.95);
//    }
//    public static double applyRule(String market)
//    {
//        return ruleMap.get(market);
//    }
//}
