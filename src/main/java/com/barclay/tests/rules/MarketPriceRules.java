package com.barclay.tests.rules;

import com.barclay.tests.exceptions.NoMatchMarketRuleExeption;

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
    public static double applyRule(String market, double price) throws NoMatchMarketRuleExeption {

        MarketRule marketRule = ruleMap.get(market);
        if(marketRule == null)
            throw new NoMatchMarketRuleExeption("Cannot find matching rule for market supply and demand " + market);
        return marketRule.operation(price);
    }
}

