package org.example.application;

import org.example.model.Basket;
import org.example.service.ConsoleReader;

import java.util.Scanner;

public class PeterApplication {
     public static void main(String[] args) {
         new Scanner(System.in);
         ConsoleReader consoleReader = new ConsoleReader();
         Basket basket = consoleReader.readBasketFromConsole(new Scanner(System.in));
    }
}
