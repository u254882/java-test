package org.example.service;

import org.example.model.Basket;
import org.example.model.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleReader {

    private static final String APPLE = "APPLE";
    private static final String SOUP = "SOUP";
    private static final String BREAD = "BREAD";
    private static final String MILK = "MILK";

    public Basket readBasketFromConsole(Scanner in) {
        Map<Product, Integer> products = new HashMap<>();


        products.put(Product.builder().name(SOUP).build(), getNextInt(in, "Specify number of tins of soup"));
        products.put(Product.builder().name(BREAD).build(), getNextInt(in, "Specify number of loaves of bread"));
        products.put(Product.builder().name(MILK).build(), getNextInt(in, "Specify number of bottles of milk"));
        products.put(Product.builder().name(APPLE).build(), getNextInt(in, "Specify number of apples"));
        return Basket.builder()
                .productQuantityMap(products)
                .daysTillPurchaseFromToday(
                        getNextInt(in,
                                "Specify how many days from today you want to purchase to be completed"))
                .build();
    }
    private int getNextInt(Scanner in, String query) {
        while (!in.hasNextInt()) {
            System.out.println(query);
        }
        return in.nextInt();
    }
}
