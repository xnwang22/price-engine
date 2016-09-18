package com.barclay.tests;

import com.barclay.tests.exceptions.NoMatchMarketRuleExeption;
import com.barclay.tests.services.PriceRuleService;
import com.barclay.tests.services.PriceRuleServiceImpl;
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
    public void testApplyMarketRuleLH() throws NoMatchMarketRuleExeption {
        double expectedPrice = 10.5;
        assertEquals(expectedPrice, priceRuleService.applyMarketRule("LH", 10.0));

    }

    @Test
    public void testApplyMarketRuleLL() throws NoMatchMarketRuleExeption {
        double expectedPrice = 11.0;
        assertEquals(expectedPrice, priceRuleService.applyMarketRule("LL", 10.0));

    }

    @Test
    public void testApplyMarketRuleHL() throws NoMatchMarketRuleExeption {
        double expectedPrice = 9.5;
        assertEquals(expectedPrice, priceRuleService.applyMarketRule("HL", 10.0));

    }

    @Test
    public void testApplyMarketRuleHH() throws NoMatchMarketRuleExeption {
        double expectedPrice = 10.0;
        assertEquals(expectedPrice, priceRuleService.applyMarketRule("HH", 10.0));

    }

    @Test(expected = NoMatchMarketRuleExeption.class)
    public void testApplyMarketRuleThrowsExeption() throws NoMatchMarketRuleExeption {
        double expectedPrice = 10.0;
        assertEquals(expectedPrice, priceRuleService.applyMarketRule("HX", 10.0));

    }
}
