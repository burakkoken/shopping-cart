package com.trendyol.shoppingcart.discount.strategy;

import com.trendyol.shoppingcart.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Burak Köken on 17.8.2019.
 */
public class AmountDiscountStrategyTest extends BaseTest {

    private AmountDiscountStrategy amountDiscountStrategy;

    @Override
    protected Class<?> getPojoClass() {
        return AmountDiscountStrategy.class;
    }

    @Before
    public void setUp() {
        amountDiscountStrategy = new AmountDiscountStrategy(100);
    }

    @Test
    public void givenAmountDiscount_WhenCalculatedDiscountWithTwoHundredTotalAmount_ThenReturnsOneHundred() {
        Assert.assertEquals(100, amountDiscountStrategy.getDiscount(200), 0);
    }

}