package com.barclay.tests;

import com.barclay.tests.services.PriceRuleService;
import com.barclay.tests.services.PriceRuleServiceImpl;
//import junit.framework.Test;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit test for simple PriceEngine.
 */
public class PriceRuleServiceTest

{

    private PriceRuleService priceRuleService;
    @Before
    public void setup()
    {
       priceRuleService = new PriceRuleServiceImpl();

    }

    @Test
    public void testGetRecommendedPriceSimple()
    {
        double price = priceRuleService.getRecommendedPrice(TestInputHelper.getProductDetailList(), TestInputHelper.getProductMarket());
        double expectedPrice = 10.0;

        assertEquals(expectedPrice, price);

    }

    @Test
    public void testGetRecommendedPriceLH()
    {
        double price = priceRuleService.getRecommendedPrice(TestInputHelper.getProductDetailListComplicated(), TestInputHelper.getProductMarket());
        double expectedPrice = 11.0;

        assertEquals(expectedPrice, price);

    }

    @Test
    public void testApplyMarketRuleLH()
    {
        double expectedPrice = 10.5;
        assertEquals(expectedPrice, priceRuleService.applyMarketRule("LH", 10.0));

    }
    @Test
    public void testApplyMarketRuleLL()
    {
        double expectedPrice = 11.0;
        assertEquals(expectedPrice, priceRuleService.applyMarketRule("LL", 10.0));

    }
    @Test
    public void testApplyMarketRuleHL()
    {
        double expectedPrice = 9.5;
        assertEquals(expectedPrice, priceRuleService.applyMarketRule("HL", 10.0));

    }
    @Test
    public void testApplyMarketRuleHH()
    {
        double expectedPrice = 10.0;
        assertEquals(expectedPrice, priceRuleService.applyMarketRule("HH", 10.0));

    }
}
