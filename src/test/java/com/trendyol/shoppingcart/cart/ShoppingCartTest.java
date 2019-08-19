package com.trendyol.shoppingcart.cart;

import com.trendyol.shoppingcart.BaseTest;
import com.trendyol.shoppingcart.cost.DeliveryCostCalculator;
import com.trendyol.shoppingcart.discount.Campaign;
import com.trendyol.shoppingcart.discount.Coupon;
import com.trendyol.shoppingcart.discount.DiscountType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Burak Köken on 16.8.2019.
 */
public class ShoppingCartTest extends BaseTest {

    private ShoppingCart shoppingCart;
    private DeliveryCostCalculator deliveryCostCalculator;
    /* Categories */
    private Category categoryFood;
    private Category categoryFruit;
    private Category categoryDrink;
    /* Products */
    private Product apple;
    private Product banana;
    private Product coke;

    @Override
    protected Class<?> getPojoClass() {
        return ShoppingCart.class;
    }

    @Before
    public void setUp() {
        shoppingCart = new ShoppingCart();
        /* delivery cost calculator */
        deliveryCostCalculator = new DeliveryCostCalculator(3.0, 4.0, 2.99);
        shoppingCart.setDeliveryCostCalculator(deliveryCostCalculator);
        /* categories */
        categoryFood = new Category("Food");
        categoryFruit = new Category("Fruit", categoryFood);
        categoryDrink = new Category("Drink", categoryFood);
        /* products */
        apple = new Product("Apple", 15.0, categoryFruit);
        banana = new Product("Banana", 25.0, categoryFruit);
        coke = new Product("Coke", 10.0, categoryDrink);
    }

    @Test
    public void givenAnyProduct_WhenAddedToShoppingCart_ThenShouldReturnQuantity() {
        Assert.assertEquals(4, shoppingCart.addItem(apple, 4));
    }

    @Test
    public void givenAnyProductThatHasBeenAddedBefore_WhenAddedAgain_ThenShouldReturnUpdatedQuantity() {
        shoppingCart.addItem(apple, 4);
        Assert.assertEquals(9, shoppingCart.addItem(apple, 5));
    }

    @Test
    public void givenShoppingCart_WhenGetTotalAmount_ThenShouldReturnTotalAmountOfProductsInShoppingCart() {
        shoppingCart.addItem(apple, 4);
        shoppingCart.addItem(banana, 2);
        shoppingCart.addItem(coke, 6);
        Assert.assertEquals(apple.getPrice() * 4 +
                banana.getPrice() * 2 +
                coke.getPrice() * 6,
                shoppingCart.getTotalCartAmount(), 0);
    }

    @Test
    public void givenShoppingCart_WhenGetNumberOfProducts_ThenShouldReturnNumberOfProductsInShoppingCart() {
        shoppingCart.addItem(apple, 4);
        shoppingCart.addItem(banana, 2);
        shoppingCart.addItem(coke, 6);
        Assert.assertEquals(3, shoppingCart.getNumberOfProducts(), 0);
    }

    @Test
    public void givenShoppingCart_WhenGetNumberOfDeliveries_ThenShouldReturnNumberOfDeliveriesInShoppingCart() {
        shoppingCart.addItem(apple, 4);
        shoppingCart.addItem(banana, 2);
        shoppingCart.addItem(coke, 6);
        Assert.assertEquals(2, shoppingCart.getNumberOfDeliveries(), 0);
    }

    @Test
    public void givenShoppingCart_WhenGetDeliveryCost_ThenShouldReturnTotalDeliveryCostForShoppingCart() {
        shoppingCart.addItem(apple, 4);
        shoppingCart.addItem(banana, 2);
        shoppingCart.addItem(coke, 6);
        Assert.assertEquals(deliveryCostCalculator.getCostPerProduct() * 3 +
                deliveryCostCalculator.getCostPerDelivery() * 2 +
                deliveryCostCalculator.getFixedCost(), shoppingCart.getDeliveryCost(), 0);
    }

    @Test
    public void givenShoppingCart_WhenGetCampaignDiscountAfterAmountCampaignsOnlyAreApplied_ThenReturnsCampaignDiscount() {
        shoppingCart.addItem(apple, 4);
        shoppingCart.addItem(banana, 4);
        shoppingCart.addItem(coke, 6);
        /* Campaign */
        Campaign campaign1 = new Campaign(categoryFood, 75, 5, DiscountType.Amount);
        Campaign campaign2 = new Campaign(categoryFruit, 60, 3, DiscountType.Amount);
        Campaign campaign3 = new Campaign(categoryDrink, 80, 5, DiscountType.Amount);
        shoppingCart.applyCampaign(campaign1);
        shoppingCart.applyCampaign(campaign2);
        shoppingCart.applyCampaign(campaign3);
        Assert.assertEquals(80, shoppingCart.getCampaignDiscount(), 0);
    }

    @Test
    public void givenShoppingCart_WhenGetCampaignDiscountAfterRateCampaignsOnlyAreApplied_ThenReturnsCampaignDiscount() {
        shoppingCart.addItem(apple, 4);
        shoppingCart.addItem(banana, 4);
        shoppingCart.addItem(coke, 6);
        /* Campaign */
        Campaign campaign1 = new Campaign(categoryFood, 30, 5, DiscountType.Rate);
        Campaign campaign2 = new Campaign(categoryFruit, 35, 3, DiscountType.Rate);
        Campaign campaign3 = new Campaign(categoryDrink, 40, 5, DiscountType.Rate);
        shoppingCart.applyCampaign(campaign1);
        shoppingCart.applyCampaign(campaign2);
        shoppingCart.applyCampaign(campaign3);
        Assert.assertEquals(66, shoppingCart.getCampaignDiscount(), 0);
    }


    @Test
    public void givenShoppingCart_WhenGetCouponDiscountAfterAmountCouponsOnlyAreApplied_ThenReturnsCouponDiscount() {
        shoppingCart.addItem(apple, 4);
        shoppingCart.addItem(banana, 4);
        shoppingCart.addItem(coke, 6);
        /* coupons */
        Coupon coupon1 = new Coupon(150, 50, DiscountType.Amount);
        Coupon coupon2 = new Coupon(200, 75, DiscountType.Amount);
        shoppingCart.applyCoupon(coupon1);
        shoppingCart.applyCoupon(coupon2);
        Assert.assertEquals(50, shoppingCart.getCouponDiscount(), 0);
    }

    @Test
    public void givenShoppingCart_WhenGetCouponDiscountAfterRateCouponsOnlyAreApplied_ThenReturnsCouponDiscount() {
        shoppingCart.addItem(apple, 4);
        shoppingCart.addItem(banana, 4);
        shoppingCart.addItem(coke, 6);
        /* coupons */
        Coupon coupon1 = new Coupon(150, 10, DiscountType.Rate);
        Coupon coupon2 = new Coupon(200, 20, DiscountType.Rate);
        shoppingCart.applyCoupon(coupon1);
        shoppingCart.applyCoupon(coupon2);
        Assert.assertEquals(22, shoppingCart.getCouponDiscount(), 0);
    }

    @Test
    public void givenShoppingCart_WhenGetTotalDiscountAfterCampaignsAndCouponsAreApplied_ThenReturnsTotalDiscount() {
        shoppingCart.addItem(apple, 4);
        shoppingCart.addItem(banana, 4);
        shoppingCart.addItem(coke, 6);
        /* campaigns */
        Campaign campaign1 = new Campaign(categoryFood, 75, 5, DiscountType.Amount);
        Campaign campaign2 = new Campaign(categoryFruit, 30, 3, DiscountType.Rate);
        /* coupons */
        Coupon coupon1 = new Coupon(140, 50, DiscountType.Amount);
        Coupon coupon2 = new Coupon(200, 20, DiscountType.Rate);
        shoppingCart.applyCampaign(campaign1);
        shoppingCart.applyCampaign(campaign2);
        shoppingCart.applyCoupon(coupon1);
        shoppingCart.applyCoupon(coupon2);
        Assert.assertEquals(125, shoppingCart.getTotalDiscount(), 0);
    }

    @Test
    public void givenShoppingCart_WhenGetTotalAmountAfterDiscounts_ThenReturnsTotalAmountAfterDiscounts() {
        shoppingCart.addItem(apple, 4);
        shoppingCart.addItem(banana, 4);
        shoppingCart.addItem(coke, 6);
        /* campaigns */
        Campaign campaign1 = new Campaign(categoryFood, 75, 5, DiscountType.Amount);
        Campaign campaign2 = new Campaign(categoryFruit, 30, 3, DiscountType.Rate);
        /* coupons */
        Coupon coupon1 = new Coupon(140, 50, DiscountType.Amount);
        Coupon coupon2 = new Coupon(200, 20, DiscountType.Rate);
        shoppingCart.applyCampaign(campaign1);
        shoppingCart.applyCampaign(campaign2);
        shoppingCart.applyCoupon(coupon1);
        shoppingCart.applyCoupon(coupon2);
        Assert.assertEquals(95, shoppingCart.getTotalAmountAfterDiscounts(), 0);
    }

    @Test
    public void testPrint() {
        shoppingCart.addItem(apple, 4);
        shoppingCart.addItem(banana, 4);
        shoppingCart.addItem(coke, 6);
        /* extra products */
        Category categoryElectronic = new Category("Electronic");
        Category categoryComputersAndAccessories = new Category("Computers & Accessories", categoryElectronic);
        Product mouse = new Product("Mouse", 15, categoryComputersAndAccessories);
        Product keyboard = new Product("Keyboard", 20, categoryComputersAndAccessories);
        shoppingCart.addItem(mouse, 4);
        shoppingCart.addItem(keyboard, 3);
        /* campaigns */
        Campaign campaign1 = new Campaign(categoryFruit, 75, 5, DiscountType.Amount);
        Campaign campaign2 = new Campaign(categoryFruit, 30, 3, DiscountType.Rate);
        /* coupons */
        Coupon coupon1 = new Coupon(140, 50, DiscountType.Amount);
        Coupon coupon2 = new Coupon(200, 20, DiscountType.Rate);
        shoppingCart.applyCampaign(campaign1);
        shoppingCart.applyCampaign(campaign2);
        shoppingCart.applyCoupon(coupon1);
        shoppingCart.applyCoupon(coupon2);
        shoppingCart.print();
    }

}