package com.trendyol.shoppingcart.discount.strategy;

import com.trendyol.shoppingcart.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Burak Köken on 17.8.2019.
 */
public class NoDiscountStrategyTest extends BaseTest {

    private NoDiscountStrategy noDiscountStrategy;

    @Override
    protected Class<?> getPojoClass() {
        return NoDiscountStrategy.class;
    }


    @Before
    public void setUp() {
        noDiscountStrategy = new NoDiscountStrategy();
    }

    @Test
    public void givenNoDiscount_WhenCalculatedDiscount_ThenReturnsZero() {
        Assert.assertEquals(0, noDiscountStrategy.getDiscount(100), 0);
        Assert.assertEquals(0, noDiscountStrategy.getDiscount(200), 0);
    }

}