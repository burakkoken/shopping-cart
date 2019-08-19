package com.trendyol.shoppingcart.discount;

import com.trendyol.shoppingcart.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Burak Köken on 16.8.2019.
 */
public class CouponTest extends BaseTest {

    @Override
    protected Class<?> getPojoClass() {
        return Coupon.class;
    }

    @Test
    public void givenPurchaseAmountGreaterThanCouponMinPurchaseAmount_WhenIsApplicableMethodIsCalled_ForRateCoupon_ThenReturnsTrue() {
        Coupon coupon = new Coupon(100, 200, DiscountType.Rate);
        Assert.assertTrue(coupon.isApplicable(150, 0));
    }

    @Test
    public void givenPurchaseAmountLessThanCouponMinPurchaseAmount_WhenIsApplicableMethodIsCalled_ForRateCoupon_ThenReturnsFalse() {
        Coupon coupon = new Coupon(100, 200, DiscountType.Rate);
        Assert.assertFalse(coupon.isApplicable(50, 0));
    }

    @Test
    public void givenPurchaseAmountGreaterThanCouponMinPurchaseAmount_WhenIsApplicableMethodIsCalled_ForAmountCoupon_ThenReturnsTrue() {
        Coupon coupon = new Coupon(100, 200, DiscountType.Amount);
        Assert.assertTrue(coupon.isApplicable(150, 0));
    }

    @Test
    public void givenPurchaseAmountLessThanCouponMinPurchaseAmount_WhenIsApplicableMethodIsCalled_ForAmountCoupon_ThenReturnsFalse() {
        Coupon coupon = new Coupon(100, 200, DiscountType.Amount);
        Assert.assertFalse(coupon.isApplicable(50, 0));
    }

}