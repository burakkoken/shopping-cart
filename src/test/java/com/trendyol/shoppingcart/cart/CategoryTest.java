package com.trendyol.shoppingcart.cart;

import com.trendyol.shoppingcart.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Burak Köken on 16.8.2019.
 */
public class CategoryTest extends BaseTest {

    @Override
    protected Class<?> getPojoClass() {
        return Category.class;
    }

    @Test
    public void givenTwoSameCategory_WhenCompared_ThenShouldBeEqual() {
        Category categoryFood = new Category("food");
        Assert.assertTrue(categoryFood.equals(new Category("food")));
        Category categoryFruit = new Category("fruit", categoryFood);
        Assert.assertTrue(categoryFruit.equals(new Category("fruit", categoryFood)));
    }

    @Test
    public void givenTwoSameCategoryThatHaveSameReference_WhenCompared_ThenShouldBeEqual() {
        Category categoryFood = new Category("food");
        Assert.assertTrue(categoryFood.equals(categoryFood));
    }

    @Test
    public void givenTwoDifferentCategory_WhenCompared_ThenShouldNotBeEqual() {
        Category categoryFood = new Category("food");
        Category categoryClothing = new Category("clothing");
        Assert.assertFalse(categoryFood.equals(categoryClothing));
    }

    @Test
    public void givenOneCategoryAndOneAnyObject_WhenCompared_ThenShouldNotBeEqual() {
        Category categoryFood = new Category("food");
        Assert.assertFalse(categoryFood.equals(new Object()));
    }

    @Test
    public void givenAnyCategoryIsWithinAnotherCategory_WhenInCategoryIsCalled_ThenReturnsTrue() {
        Category categoryFood = new Category("food");
        Category categoryFruit = new Category("fruit", categoryFood);
        Assert.assertTrue(categoryFood.inCategory(new Category("food")));
        Assert.assertTrue(categoryFruit.inCategory(new Category("food")));
        Assert.assertTrue(categoryFruit.inCategory(new Category("fruit", categoryFood)));
    }

    @Test
    public void givenAnyCategoryIsNotWithinAnotherCategory_WhenInCategoryIsCalled_ThenReturnsFalse() {
        Category categoryFood = new Category("food");
        Category categoryFruit = new Category("fruit", categoryFood);
        Assert.assertFalse(categoryFood.inCategory(new Category("clothing")));
        Assert.assertFalse(categoryFruit.inCategory(new Category("clothing")));
    }

    @Test
    public void testCategoryToString() {
        Category categoryFood = new Category("food");
        Category categoryFruit = new Category("fruit", categoryFood);
        Assert.assertEquals("/food", categoryFood.toString());
        Assert.assertEquals("/food/fruit", categoryFruit.toString());
    }

}