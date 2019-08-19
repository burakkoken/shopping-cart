package com.trendyol.shoppingcart.discount;

/**
 * Created by Burak Köken on 16.8.2019.
 */
public abstract class Discount {

    private double discountAmount;
    private DiscountType discountType;

    public Discount(double discountAmount, DiscountType discountType) {
        this.discountAmount = discountAmount;
        this.discountType = discountType;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public abstract boolean isApplicable(double totalAmount, int productQuantity);

}
