package com.trendyol.shoppingcart.discount.strategy;

import com.trendyol.shoppingcart.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Burak Köken on 17.8.2019.
 */
public class RateDiscountStrategyTest extends BaseTest {

    private RateDiscountStrategy rateDiscountStrategy;

    @Override
    protected Class<?> getPojoClass() {
        return RateDiscountStrategy.class;
    }

    @Before
    public void setUp() {
        rateDiscountStrategy = new RateDiscountStrategy(20);
    }

    @Test
    public void givenRateDiscount_WhenCalculatedDiscountWithZeroTotalAmount_ThenReturnsZero() {
        Assert.assertEquals(0, rateDiscountStrategy.getDiscount(0), 0);
    }

    @Test
    public void givenRateDiscount_WhenCalculatedDiscountWithTwoHundredTotalAmount_ThenReturnsForty() {
        Assert.assertEquals(200 * 20 * 0.01, rateDiscountStrategy.getDiscount(200), 0);
    }

}