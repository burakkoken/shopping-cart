package com.trendyol.shoppingcart;

import com.trendyol.shoppingcart.cart.Category;
import com.trendyol.shoppingcart.cart.Product;
import com.trendyol.shoppingcart.cart.ShoppingCart;
import com.trendyol.shoppingcart.cost.DeliveryCostCalculator;
import com.trendyol.shoppingcart.discount.Campaign;
import com.trendyol.shoppingcart.discount.Coupon;
import com.trendyol.shoppingcart.discount.DiscountType;

/**
 * Created by Burak Köken on 18.8.2019.
 */
public class Main {

    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        /* delivery cost calculator */
        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(3.0, 4.0, 2.99);
        shoppingCart.setDeliveryCostCalculator(deliveryCostCalculator);
        /* categories */
        Category categoryFood = new Category("Food");
        Category categoryFruit = new Category("Fruit", categoryFood);
        Category categoryDrink = new Category("Drink", categoryFood);
        Category categoryElectronic = new Category("Electronic");
        Category categoryComputersAndAccessories = new Category("Computers & Accessories", categoryElectronic);
        /* products */
        Product apple = new Product("Apple", 15.0, categoryFruit);
        Product banana = new Product("Banana", 25.0, categoryFruit);
        Product coke = new Product("Coke", 10.0, categoryDrink);
        Product mouse = new Product("Mouse", 15, categoryComputersAndAccessories);
        Product keyboard = new Product("Keyboard", 20, categoryComputersAndAccessories);
        /* add the products to the shopping cart */
        shoppingCart.addItem(apple, 4);
        shoppingCart.addItem(banana, 4);
        shoppingCart.addItem(coke, 6);
        shoppingCart.addItem(mouse, 4);
        shoppingCart.addItem(keyboard, 3);
        /* campaigns */
        Campaign campaign1 = new Campaign(categoryFruit, 75, 5, DiscountType.Amount);
        Campaign campaign2 = new Campaign(categoryFruit, 30, 3, DiscountType.Rate);
        /* apply the campaigns */
        shoppingCart.applyCampaign(campaign1);
        shoppingCart.applyCampaign(campaign2);
        /* coupons */
        Coupon coupon1 = new Coupon(140, 50, DiscountType.Amount);
        Coupon coupon2 = new Coupon(200, 20, DiscountType.Rate);
        /* apply the coupons */
        shoppingCart.applyCoupon(coupon1);
        shoppingCart.applyCoupon(coupon2);
        /* print the shopping cart */
        shoppingCart.print();
    }

}
