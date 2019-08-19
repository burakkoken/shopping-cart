package com.trendyol.shoppingcart.discount;

/**
 * Created by Burak Köken on 16.8.2019.
 */
public class Coupon extends Discount {

    private double minPurchaseAmount;

    public Coupon(double minPurchaseAmount, double discountAmount, DiscountType discountType) {
        super(discountAmount, discountType);
        this.minPurchaseAmount = minPurchaseAmount;
    }

    public double getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public void setMinPurchaseAmount(double minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
    }

    @Override
    public boolean isApplicable(double totalAmount, int productQuantity) {
        return totalAmount >= minPurchaseAmount;
    }

}
