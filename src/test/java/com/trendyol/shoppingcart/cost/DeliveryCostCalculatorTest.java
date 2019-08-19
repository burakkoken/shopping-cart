package com.trendyol.shoppingcart.cost;

import com.trendyol.shoppingcart.BaseTest;
import com.trendyol.shoppingcart.cart.Category;
import com.trendyol.shoppingcart.cart.Product;
import com.trendyol.shoppingcart.cart.ShoppingCart;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Burak Köken on 16.8.2019.
 */
public class DeliveryCostCalculatorTest extends BaseTest {

    @Override
    protected Class<?> getPojoClass() {
        return DeliveryCostCalculator.class;
    }

    @Test
    public void givenEmptyShoppingCart_WhenCalculatedDeliveryCost_ThenReturnsFixedCost() {
        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(3.0, 4.0, 2.99);
        Assert.assertEquals(2.99, deliveryCostCalculator.calculateFor(new ShoppingCart()), 0);
    }

    @Test
    public void givenShoppingCart_WhenCalculatedDeliveryCost_ThenReturnsTotalDeliveryCost() {
        ShoppingCart shoppingCart = new ShoppingCart();
        /* categories */
        Category categoryFood = new Category("Food");
        Category categoryFruit = new Category("Fruit", categoryFood);
        Category categoryDrink = new Category("Drink", categoryFood);
        /* products */
        Product apple = new Product("Apple", 15.0, categoryFruit);
        Product banana = new Product("Banana", 25.0, categoryFruit);
        Product coke = new Product("Coke", 10.0, categoryDrink);
        shoppingCart.addItem(apple, 4);
        shoppingCart.addItem(banana, 2);
        shoppingCart.addItem(coke, 6);
        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(3.0, 4.0, 2.99);
        Assert.assertEquals(deliveryCostCalculator.getCostPerProduct() * 3 +
                deliveryCostCalculator.getCostPerDelivery() * 2 +
                deliveryCostCalculator.getFixedCost(),
                deliveryCostCalculator.calculateFor(shoppingCart),
                0);
    }

}