package org.example.service;


import org.example.model.Basket;
import org.example.model.Product;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ConsoleReaderTest {
    private static final String APPLE = "APPLE";
    private static final String SOUP = "SOUP";
    private static final String BREAD = "BREAD";
    private static final String MILK = "MILK";
    private ConsoleReader consoleReader = new ConsoleReader();

    @Test
    public void readBasketFromConsole() {
        //given
        String inputString = "8\n" +
                "4\n" +
                "3\n" +
                "2\n" +
                "70\n";
        //when
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());
        Basket actual = consoleReader.readBasketFromConsole(new Scanner(in));
        Basket expected = getExpectedBasket();
        assertEquals(actual, expected);

    }

    private Basket getExpectedBasket() {
        Map<Product, Integer> products = new HashMap<>();
        products.put(Product.builder().name(SOUP).build(), 8);
        products.put(Product.builder().name(BREAD).build(), 4);
        products.put(Product.builder().name(MILK).build(), 3);
        products.put(Product.builder().name(APPLE).build(), 2);
        return Basket.builder()
                .productQuantityMap(products)
                .daysTillPurchaseFromToday(70)
                .build();
    }
}