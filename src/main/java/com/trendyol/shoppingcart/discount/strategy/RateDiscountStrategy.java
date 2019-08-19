package com.trendyol.shoppingcart.discount.strategy;

/**
 * Created by Burak Köken on 17.8.2019.
 */
public class RateDiscountStrategy implements DiscountStrategy {

    private double rate;

    public RateDiscountStrategy(double rate) {
        this.rate = rate;
    }

    @Override
    public double getDiscount(double totalAmount) {
        return totalAmount * (rate * 0.01);
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

}
