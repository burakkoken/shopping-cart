package com.trendyol.shoppingcart.discount.strategy;

/**
 * Created by Burak Köken on 17.8.2019.
 */
public class NoDiscountStrategy implements DiscountStrategy {

    public NoDiscountStrategy() {

    }

    @Override
    public double getDiscount(double totalAmount) {
        return 0;
    }

}
