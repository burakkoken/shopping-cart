package com.trendyol.shoppingcart.discount;

import com.trendyol.shoppingcart.cart.Category;

/**
 * Created by Burak Köken on 16.8.2019.
 */
public class Campaign extends Discount {

    private int minQuantity;
    private Category category;

    public Campaign(Category category, double discountAmount, int minQuantity, DiscountType discountType) {
        super(discountAmount, discountType);
        this.minQuantity = minQuantity;
        this.category = category;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean isApplicable(double totalAmount, int productQuantity) {
        return productQuantity >= minQuantity;
    }

}
