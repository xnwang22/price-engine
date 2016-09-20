package com.barclay.tests;

import com.barclay.tests.exceptions.FieldsCountExeption;
import com.barclay.tests.exceptions.NoMatchMarketRuleExeption;
import com.barclay.tests.models.PriceDetail;
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
 * Price Engine application
 */
public class PriceEngine {
    private PriceRuleService priceRuleService = new PriceRuleServiceImpl();

    public PriceEngine(BufferedReader bufferedReader) {
        this.reader = bufferedReader;

    }

    public PriceRuleService getPriceRuleService() {
        return priceRuleService;
    }

    BufferedReader reader;

    public static void main(String[] args) {
        // first get products and market
        PriceEngine priceEngine = new PriceEngine(new BufferedReader(new InputStreamReader(System.in)));

        Set<ProductMarket> productMarketSet = null;
        try {
            productMarketSet = priceEngine.getProductSet();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FieldsCountExeption fieldsCountExeption) {
            fieldsCountExeption.printStackTrace();
        }

        // get ProductDetails
        List<PriceDetail> priceDetailList = null;
        try {
            priceDetailList = priceEngine.getPriceDetails();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FieldsCountExeption fieldsCountExeption) {
            fieldsCountExeption.printStackTrace();
        }

        int i = 0;
        for (final ProductMarket productMarket : productMarketSet) {
            double recommendedPrice = priceEngine.getPriceRuleService().getRecommendedPrice(priceDetailList, productMarket);
            double finalPrice = 0;
            try {
                finalPrice = priceEngine.getPriceRuleService().applyMarketRule(productMarket.getMarket(), recommendedPrice);
            } catch (NoMatchMarketRuleExeption noMatchMarketRuleExeption) {
                noMatchMarketRuleExeption.printStackTrace();
            }
            System.out.println(String.format("%s %.1f", (char) ("A".charAt(0) + i), finalPrice));
            // **note** here only 52 products, since there are only 52 letters
            i++;
        }

    }

    // make method package visible for unit test
    Set<ProductMarket> getProductSet() throws IOException, FieldsCountExeption {
        Set<ProductMarket> productMarketSet = new HashSet<>(); // it is more appropriate to use set here
        System.out.println("Please give product count:");
        int productCount = Integer.parseInt(reader.readLine());
        if (productCount < 1) {
            System.out.println("Number of product count has to be greater than 0");
            return productMarketSet;
        }
        for (int i = 0; i < productCount; i++) {
            String input = reader.readLine();
            String[] fields = input.split(" ");
            if (fields == null || fields.length != 3) {
                throw new FieldsCountExeption("Product Market has " + fields.length + " fields.");
            }

            ProductMarket productMarket = new ProductMarket(fields[0], fields[1] + fields[2]);
            productMarketSet.add(productMarket);
        }
        return productMarketSet;
    }

    // make method package visible for unit test
    List<PriceDetail> getPriceDetails() throws IOException, FieldsCountExeption {
        List<PriceDetail> priceDetailList = new ArrayList<>();
        System.out.println("Please give price details count:");
        int productDetailsCount = Integer.parseInt(reader.readLine());

        if (productDetailsCount < 1) {
            System.out.println("Number of product price count has to be greater than 0");
            return priceDetailList;
        }

        for (int i = 0; i < productDetailsCount; i++) {
            String input = reader.readLine();
            String[] fields = input.split(" ");
            if (fields == null || fields.length != 3) {
                throw new FieldsCountExeption("Product price details has " + fields.length + " fields.");
            }
            PriceDetail priceDetail = new PriceDetail(fields[0], fields[1], Double.valueOf(fields[2]));
            priceDetailList.add(priceDetail);
        }
        return priceDetailList;
    }
}
