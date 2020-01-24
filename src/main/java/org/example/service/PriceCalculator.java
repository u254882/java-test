package org.example.service;

import org.example.model.Basket;
import org.example.model.Coupon;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

public class PriceCalculator {
    //this of course should come from some pricing service call.
    private final static BigDecimal APPLE_PRICE = new BigDecimal(0.10);
    private final static BigDecimal BREAD_PRICE = new BigDecimal(0.80);
    private final static BigDecimal SOUP_PRICE = new BigDecimal(0.65);
    private final static BigDecimal MILK_PRICE = new BigDecimal(1.30);


    private final Clock clock;

    public PriceCalculator(Clock clock) {
        this.clock = clock;

    }

    public BigDecimal getPrice(Basket basket) {
        return null;
    }

    private List<Coupon> getCoupons(Basket basket) {
        return null;
    }

    private Coupon getAppleCoupon(Basket basket) {
        LocalDate today = LocalDate.now(clock);
        LocalDate endOfNextMonth = today.plusMonths(1).withDayOfMonth(today.getMonth().length(today.isLeapYear()));
        if (today.plusDays(basket.getDaysTillPurchaseFromToday()).isBefore(endOfNextMonth)
                && today.minusDays(4).isBefore(today.plusDays(basket.getDaysTillPurchaseFromToday()).plusDays(1))) {
            Integer appleQuantity = basket.getProductQuantityMap().get("APPLE");
            return new Coupon(APPLE_PRICE.divide(BigDecimal.valueOf(10)).multiply(BigDecimal.valueOf(appleQuantity)));
        }
        return new Coupon(BigDecimal.ZERO);
    }

    private Coupon getSoupBreadCoupon(Basket basket) {
        LocalDate today = LocalDate.now(clock);
        LocalDate xDaysBefore = today.minusDays(1);
        LocalDate yDaysAfter = today.plusDays(7);
        if(today.isAfter(xDaysBefore.minusDays(1)) &&
        today.isBefore(yDaysAfter.plusDays(1))) {
            Integer soupCouponsForBread = basket.getProductQuantityMap().get("SOUP")/2;
            Integer numberOfLoavesOfBread = basket.getProductQuantityMap().get("BREAD");

            BigDecimal maxPossibleDiscountFromSoup = BREAD_PRICE.divide(BigDecimal.valueOf(2)).multiply(BigDecimal.valueOf(soupCouponsForBread));
            BigDecimal breadAmountWithDeduction = (BREAD_PRICE.multiply(BigDecimal.valueOf(numberOfLoavesOfBread))).subtract(maxPossibleDiscountFromSoup);
            if(breadAmountWithDeduction.compareTo(BigDecimal.ZERO) < 0){
                return new Coupon(BigDecimal.ZERO);
            } else {
                return new Coupon(breadAmountWithDeduction);
            }
        }
        return new Coupon(BigDecimal.ZERO);
    }
}
