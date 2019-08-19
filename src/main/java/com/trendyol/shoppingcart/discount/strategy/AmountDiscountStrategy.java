package com.trendyol.shoppingcart.discount.strategy;

/**
 * Created by Burak Köken on 17.8.2019.
 */
public class AmountDiscountStrategy implements DiscountStrategy {

    private double amount;

    public AmountDiscountStrategy(double amount) {
        this.amount = amount;
    }

    @Override
    public double getDiscount(double totalAmount) {
        return amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
