package com.barclay.tests;

import com.barclay.tests.models.ProductDetail;
import com.barclay.tests.models.ProductMarket;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Unit test for simple PriceEngine.
 */
public class PriceEngineTest

{
    InputStream in;
    InputStreamReader inr;
    BufferedReader bufferedReader;
    PriceEngine priceEngine;


    @Before
    public void setup() {
        in = mock(InputStream.class);
        inr = mock(InputStreamReader.class);
        bufferedReader = mock(BufferedReader.class);
        priceEngine = new PriceEngine(bufferedReader);

    }

    @Test
    public void testGetProductMarket() throws Exception {

            PowerMockito.whenNew(InputStreamReader.class).withArguments(in).thenReturn(inr);
            PowerMockito.whenNew(BufferedReader.class).withArguments(inr).thenReturn(bufferedReader);
            PowerMockito.when(bufferedReader.readLine()).thenReturn("1").thenReturn("aaa H H\\n");
            Set<ProductMarket> ret = priceEngine.getProductSet();
            assertEquals(1, ret.size());
            ret.contains(new ProductMarket("aaa", "HH"));


    }

    @Test
    public void testGetProductDetails() throws Exception {

        PowerMockito.whenNew(InputStreamReader.class).withArguments(in).thenReturn(inr);
        PowerMockito.whenNew(BufferedReader.class).withArguments(inr).thenReturn(bufferedReader);
        PowerMockito.when(bufferedReader.readLine()).thenReturn("1").thenReturn("aaa X 1.0");
        List<ProductDetail> list = priceEngine.getProductDetails();
        assertEquals(1, list.size());
        assertEquals(1.0, list.get(0).getPrice(), 0.0);
        assertEquals("X", list.get(0).getCompetitor());
        assertEquals("aaa", list.get(0).getCode());

    }

}
