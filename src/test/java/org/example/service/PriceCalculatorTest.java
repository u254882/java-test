package org.example.service;


import org.example.model.Basket;
import org.example.model.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PriceCalculatorTest {

    private static final String APPLE = "APPLE";
    private static final String SOUP = "SOUP";
    private static final String BREAD = "BREAD";
    private static final String MILK = "MILK";

    private PriceCalculator target = new PriceCalculator(Clock.fixed(Instant.now(),ZoneId.of("Z")));
    @Test
    public void basketWithApplesOnlyNoPromotion() {
        //given
        Map<Product, Integer> productQuantityMap = new HashMap<>();
        productQuantityMap.put(Product.builder().name(APPLE).build(),2);
        Basket basket = Basket.builder().productQuantityMap(productQuantityMap).daysTillPurchaseFromToday(100).build();
        BigDecimal expected = BigDecimal.valueOf(0.4);

        BigDecimal actual = target.getPrice(basket);
        assertEquals(expected, actual);

    }

    @Test
    public void basketWithApplesOnlyPromotion() {
        //given
        Map<Product, Integer> productQuantityMap = new HashMap<>();
        productQuantityMap.put(Product.builder().name(APPLE).build(),2);
        Basket basket = Basket.builder().productQuantityMap(productQuantityMap).daysTillPurchaseFromToday(-2).build();
        BigDecimal expected = BigDecimal.valueOf(0.36);

        //when
        BigDecimal actual = target.getPrice(basket);
        //then
        assertEquals(expected, actual);

    }

    @Test
    public void soupAndBreadWithPromotion() {
        //given
        Map<Product, Integer> productQuantityMap = new HashMap<>();
        productQuantityMap.put(Product.builder().name(SOUP).build(),3);
        productQuantityMap.put(Product.builder().name(BREAD).build(),2);
        Basket basket = Basket.builder().productQuantityMap(productQuantityMap).daysTillPurchaseFromToday(0).build();
        BigDecimal expected = BigDecimal.valueOf(3.15);

        //when
        BigDecimal actual = target.getPrice(basket);
        //then
        assertEquals(expected, actual);

    }
    @Test
    public void appleAndMilkWithPromotionToday() {
        //given
        Map<Product, Integer> productQuantityMap = new HashMap<>();
        productQuantityMap.put(Product.builder().name(APPLE).build(),6);
        productQuantityMap.put(Product.builder().name(MILK).build(),1);
        Basket basket = Basket.builder().productQuantityMap(productQuantityMap).daysTillPurchaseFromToday(0).build();
        BigDecimal expected = BigDecimal.valueOf(1.90);

        //when
        BigDecimal actual = target.getPrice(basket);
        //then
        assertEquals(expected, actual);

    }
    @Test
    public void appleAndMilkWithPromotion() {
        //given
        Map<Product, Integer> productQuantityMap = new HashMap<>();
        productQuantityMap.put(Product.builder().name(APPLE).build(),6);
        productQuantityMap.put(Product.builder().name(MILK).build(),1);
        Basket basket = Basket.builder().productQuantityMap(productQuantityMap).daysTillPurchaseFromToday(5).build();
        BigDecimal expected = BigDecimal.valueOf(1.84);

        //when
        BigDecimal actual = target.getPrice(basket);
        //then
        assertEquals(expected, actual);

    }

    /*
Price a basket containing: 6 apples and a bottle of milk, bought today,
Expected total cost = 1.90;
Price a basket containing: 6 apples and a bottle of milk, bought in 5 days time,
Expected total cost = 1.84;
Price a basket containing: 3 apples, 2 tins of soup and a loaf of bread, bought in 5 days time,
Expected total cost = 1.97.
 */
}