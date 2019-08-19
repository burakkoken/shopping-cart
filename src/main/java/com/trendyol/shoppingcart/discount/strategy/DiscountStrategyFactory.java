package com.trendyol.shoppingcart.discount.strategy;

import com.trendyol.shoppingcart.discount.Discount;
import com.trendyol.shoppingcart.discount.DiscountType;

/**
 * Created by Burak Köken on 17.8.2019.
 */
public class DiscountStrategyFactory {

    public DiscountStrategy getDiscountStrategy(Discount discount) {
        if(discount != null) {
            if(DiscountType.Amount.equals(discount.getDiscountType())) {
                return new AmountDiscountStrategy(discount.getDiscountAmount());
            } else if(DiscountType.Rate.equals(discount.getDiscountType())) {
                return new RateDiscountStrategy(discount.getDiscountAmount());
            }
        }
        return new NoDiscountStrategy();
    }

}
