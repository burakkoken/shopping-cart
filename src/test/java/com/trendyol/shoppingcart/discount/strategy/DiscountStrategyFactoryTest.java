package com.trendyol.shoppingcart.discount.strategy;

import com.trendyol.shoppingcart.BaseTest;
import com.trendyol.shoppingcart.cart.Category;
import com.trendyol.shoppingcart.discount.Campaign;
import com.trendyol.shoppingcart.discount.Coupon;
import com.trendyol.shoppingcart.discount.DiscountType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Burak Köken on 17.8.2019.
 */
public class DiscountStrategyFactoryTest extends BaseTest {

    private DiscountStrategyFactory discountStrategyFactory;

    @Override
    protected Class<?> getPojoClass() {
        return DiscountStrategyFactory.class;
    }

    @Before
    public void setUp() {
        discountStrategyFactory = new DiscountStrategyFactory();
    }

    @Test
    public void givenNull_WhenGetDiscountStrategy_ThenReturnsNoDiscountStrategy() {
        Assert.assertEquals(NoDiscountStrategy.class, discountStrategyFactory.getDiscountStrategy(null).getClass());
    }

    @Test
    public void givenAmountCampaignDiscount_WhenGetDiscountStrategy_ThenReturnsAmountDiscountStrategy() {
        Campaign campaign = new Campaign(new Category("food"), 100, 5, DiscountType.Amount);
        Assert.assertEquals(AmountDiscountStrategy.class, discountStrategyFactory.getDiscountStrategy(campaign).getClass());
    }

    @Test
    public void givenAmountCouponDiscount_WhenGetDiscountStrategy_ThenReturnsAmountDiscountStrategy() {
        Coupon coupon = new Coupon(100, 10, DiscountType.Amount);
        Assert.assertEquals(AmountDiscountStrategy.class, discountStrategyFactory.getDiscountStrategy(coupon).getClass());
    }

    @Test
    public void givenRateCampaignDiscount_WhenGetDiscountStrategy_ThenReturnsRateDiscountStrategy() {
        Campaign campaign = new Campaign(new Category("food"), 100, 5, DiscountType.Rate);
        Assert.assertEquals(RateDiscountStrategy.class, discountStrategyFactory.getDiscountStrategy(campaign).getClass());
    }

    @Test
    public void givenRateCouponDiscount_WhenGetDiscountStrategy_ThenReturnsRateDiscountStrategy() {
        Coupon coupon = new Coupon(100, 10, DiscountType.Rate);
        Assert.assertEquals(RateDiscountStrategy.class, discountStrategyFactory.getDiscountStrategy(coupon).getClass());
    }



}