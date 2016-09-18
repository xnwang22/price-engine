package com.barclay.tests.services;

import com.barclay.tests.exceptions.NoMatchMarketRuleExeption;
import com.barclay.tests.models.PriceDetail;
import com.barclay.tests.models.ProductMarket;
import com.barclay.tests.rules.MarketPriceRules;

import java.util.List;

/**
 * Created by robinwang on 9/17/16.
 */
public interface PriceRuleService {
    double getRecommendedPrice(List<PriceDetail> priceDetailList, ProductMarket productMarketSet);
    default double applyMarketRule(String market, double recommendedPrice) throws NoMatchMarketRuleExeption {
        return MarketPriceRules.applyRule(market, recommendedPrice);
    }

}
