package com.trendyol.shoppingcart.cost;

import com.trendyol.shoppingcart.cart.ShoppingCart;

/**
 * Created by Burak Köken on 16.8.2019.
 */
public class DeliveryCostCalculator {

    private double costPerDelivery;
    private double costPerProduct;
    private double fixedCost;

    public DeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }

    public double getCostPerDelivery() {
        return costPerDelivery;
    }

    public void setCostPerDelivery(double costPerDelivery) {
        this.costPerDelivery = costPerDelivery;
    }

    public double getCostPerProduct() {
        return costPerProduct;
    }

    public void setCostPerProduct(double costPerProduct) {
        this.costPerProduct = costPerProduct;
    }

    public double getFixedCost() {
        return fixedCost;
    }

    public void setFixedCost(double fixedCost) {
        this.fixedCost = fixedCost;
    }

    public double calculateFor(ShoppingCart shoppingCart) {
        return (costPerDelivery * shoppingCart.getNumberOfDeliveries()) +
                (costPerProduct * shoppingCart.getNumberOfProducts()) +
                fixedCost;
    }

}
