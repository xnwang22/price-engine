package com.barclay.tests;

import com.barclay.tests.models.ProductDetail;
import com.barclay.tests.models.ProductMarket;
import com.barclay.tests.services.PriceRuleService;
import com.barclay.tests.services.PriceRuleServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class PriceEngine
{
    private PriceRuleService priceRuleService = new PriceRuleServiceImpl();

    public PriceEngine(BufferedReader bufferedReader) {
        this.reader = bufferedReader;

    }

    public PriceRuleService getPriceRuleService() {
        return priceRuleService;
    }
    BufferedReader reader;

    public static void main(String[] args )
    {
        // first get products and market
        PriceEngine priceEngine = new PriceEngine(new BufferedReader(new InputStreamReader(System.in)));

        Set<ProductMarket> productMarketSet = null;
        try {
            productMarketSet = priceEngine.getProductSet();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get ProductDetails
        List<ProductDetail> productDetailList = null;
        try {
            productDetailList = priceEngine.getProductDetails();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
        for(final ProductMarket productMarket : productMarketSet)
        {
            double recommendedPrice = priceEngine.getPriceRuleService().getRecommendedPrice(productDetailList, productMarket);
            double finalPrice = priceEngine.getPriceRuleService().applyMarketRule(productMarket.getMarket(), recommendedPrice);
            System.out.println(String.format("%s %.1f", (char)("A".charAt(0) + i), finalPrice));
            // **note** here only 52 products, since there are only 52 letters
            i++;
        }

    }


     Set<ProductMarket> getProductSet() throws IOException {
        Set<ProductMarket> productMarketSet = new HashSet<>(); // it is more appropriate to use set here
        System.out.println( "Please give product count:" );
        int productCount = Integer.parseInt(reader.readLine());
        if(productCount < 1)
        {
            System.out.println("Number of product count has to be greater than 0");
            return productMarketSet;
        }
        for( int i = 0; i < productCount; i++)
        {
            String input = reader.readLine();
            String[] fields = input.split(" ");
            if(fields.length != 3) {
                System.out.println("Product has 3 fields");
                return productMarketSet;
            }

            ProductMarket productMarket = new ProductMarket(fields[0], fields[1]+fields[2]);
            productMarketSet.add(productMarket);
        }
        return productMarketSet;
    }

     List<ProductDetail> getProductDetails() throws IOException {
        List<ProductDetail> productDetailList = new ArrayList<>();
        System.out.println( "Please give product details count:" );
        int productDetailsCount = Integer.parseInt(reader.readLine());

        if(productDetailsCount < 1)
        {
            System.out.println("Number of product count has to be greater than 0");
            return productDetailList;
        }

        for( int i = 0; i < productDetailsCount; i++)
        {
            String input = reader.readLine();
            String[] fields = input.split(" ");
            if(fields.length != 3) {
                System.out.println("Product details has 3 fields");
                return productDetailList;
            }
            ProductDetail productDetail = new ProductDetail(fields[0], fields[1], Double.valueOf(fields[2]));
            productDetailList.add(productDetail);
        }
        return productDetailList;
    }
}
