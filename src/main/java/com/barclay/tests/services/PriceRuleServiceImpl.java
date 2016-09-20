package com.barclay.tests.services;

import com.barclay.tests.models.PriceDetail;
import com.barclay.tests.models.ProductMarket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by robinwang on 9/17/16.
 */
public class PriceRuleServiceImpl implements PriceRuleService {
    @Override
    public double getRecommendedPrice(List<PriceDetail> priceDetailList, ProductMarket productMarket) {
        // 1) calculate average price for product
        Supplier<Stream<PriceDetail>> streamSupplier = () -> priceDetailList.stream().filter(e -> e.getCode().equals(productMarket.getName()));
        double averagePrice = streamSupplier.get().mapToDouble(value -> value.getPrice()).average().getAsDouble();

        // 2) filter out promotion price
        Stream<PriceDetail> filterPromotionStream = streamSupplier.get().filter(e -> e.getPrice() >= averagePrice * 0.5);

        // 3) filter out error price
        Stream<PriceDetail> filterErrorStream = filterPromotionStream.filter(e -> e.getPrice() <= averagePrice * 1.5);
        List<Double> doublePriceList = filterErrorStream.mapToDouble(e -> e.getPrice()).boxed().collect(Collectors.toCollection(ArrayList<Double>::new));

        // 4) get most frequently occuring price
        Map<Double, Long> collect = doublePriceList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // finally return the price with most occurrence, or the least of the most frequent occurrences
        return collect.entrySet().stream().max((o1, o2) -> {
            if (o1.getValue() > o2.getValue())
                return 1;
            else if (o1.getValue() < o2.getValue())
                return -1;
            else {  // get frequency is the same, then get the least price
                if (o1.getKey() > o2.getKey())
                    return -1;
                else if (o1.getKey() < o2.getKey())
                    return 1;
                else
                    return 0;
            }
        }).get().getKey();

    }

}
