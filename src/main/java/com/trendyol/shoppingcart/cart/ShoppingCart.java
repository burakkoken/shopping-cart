package com.trendyol.shoppingcart.cart;

import com.trendyol.shoppingcart.cost.DeliveryCostCalculator;
import com.trendyol.shoppingcart.discount.Campaign;
import com.trendyol.shoppingcart.discount.Coupon;
import com.trendyol.shoppingcart.discount.strategy.DiscountStrategy;
import com.trendyol.shoppingcart.discount.strategy.DiscountStrategyFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Burak Köken on 16.8.2019.
 */
public class ShoppingCart {

    private Map<Product, Integer> products;
    private List<Campaign> campaigns;
    private List<Coupon> coupons;
    private DeliveryCostCalculator deliveryCostCalculator;

    public ShoppingCart() {
        this.products = new HashMap<>();
        this.campaigns = new ArrayList<>();
        this.coupons = new ArrayList<>();
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public DeliveryCostCalculator getDeliveryCostCalculator() {
        return deliveryCostCalculator;
    }

    public void setDeliveryCostCalculator(DeliveryCostCalculator deliveryCostCalculator) {
        this.deliveryCostCalculator = deliveryCostCalculator;
    }

    public int addItem(Product product, int quantity) {
        int totalQuantity = quantity;
        if (products.containsKey(product)) {
            totalQuantity = totalQuantity + products.get(product);
            products.replace(product, totalQuantity);
        } else {
            products.put(product, totalQuantity);
        }
        return totalQuantity;
    }

    public void applyCampaign(Campaign campaign) {
        campaigns.add(campaign);
    }

    public void applyCoupon(Coupon coupon) {
        coupons.add(coupon);
    }

    public double getTotalCartAmount() {
        return products.entrySet().stream()
                .mapToDouble(x -> x.getKey().getPrice() * x.getValue())
                .sum();
    }

    private double getTotalAmountAfterCampaignDiscount() {
        return getTotalCartAmount() - getCampaignDiscount();
    }

    public double getTotalAmountAfterDiscounts() {
        return getTotalAmountAfterCampaignDiscount() - getCouponDiscount();
    }

    public double getTotalDiscount() {
        return getCampaignDiscount() + getCouponDiscount();
    }

    public double getCouponDiscount() {
        double totalCouponDiscount = 0;
        for(Coupon coupon : coupons) {
            if(coupon.isApplicable(getTotalAmountAfterCampaignDiscount() - totalCouponDiscount, 0)) {
                DiscountStrategyFactory discountStrategyFactory = new DiscountStrategyFactory();
                DiscountStrategy discountStrategy = discountStrategyFactory.getDiscountStrategy(coupon);
                totalCouponDiscount += discountStrategy.getDiscount(getTotalAmountAfterCampaignDiscount());
            }
        }
        return totalCouponDiscount;
    }

    private List<Product> getProductsInCategory(Category category) {
        return products.keySet().stream()
                .filter(product -> product.getCategory().inCategory(category))
                .collect(Collectors.toList());
    }

    private int getProductQuantityByCategory(Category category) {
        List<Product> productList = getProductsInCategory(category);
        return productList.stream()
                .mapToInt(product -> products.get(product))
                .sum();
    }

    private double getTotalAmountByCategory(Category category) {
        List<Product> productList = getProductsInCategory(category);
        return productList.stream()
                .mapToDouble(product -> product.getPrice() * products.get(product))
                .sum();
    }

    public double getCampaignDiscount() {
        double maxCampaignDiscount = 0;
        for(Campaign campaign : campaigns) {
            Category category = campaign.getCategory();
            int productQuantity = getProductQuantityByCategory(category);
            if(campaign.isApplicable(0, productQuantity)) {
                double totalAmount = getTotalAmountByCategory(category);
                DiscountStrategyFactory discountStrategyFactory = new DiscountStrategyFactory();
                DiscountStrategy discountStrategy = discountStrategyFactory.getDiscountStrategy(campaign);
                maxCampaignDiscount = Math.max(maxCampaignDiscount, discountStrategy.getDiscount(totalAmount));
            }
        }
        return maxCampaignDiscount;
    }

    public double getDeliveryCost() {
        return deliveryCostCalculator.calculateFor(this);
    }

    public int getNumberOfDeliveries() {
        Set<Category> productCategories =  products.keySet().stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());
        return productCategories.size();
    }

    public int getNumberOfProducts() {
        return products.size();
    }

    private Set<Category> getAllCategories() {
        Set<Category> categories = new HashSet<>();
        for(Product product : products.keySet()) {
            Category current = product.getCategory();
            while (current != null) {
                categories.add(current);
                current = current.getParentCategory();
            }
        }
        return categories;
    }

    private List<Product> getProductsByCategory(Category category) {
        return products.keySet().stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    private Map<Category, Set<Category>> getCategoryMap() {
        Map<Category, Set<Category>> categoryMap = new HashMap<>();
        for(Product product : products.keySet()) {
            Category current = product.getCategory();
            while (current != null && current.getParentCategory() != null) {
                Set<Category> categoryList = categoryMap.get(current.getParentCategory());
                if(categoryList == null) {
                    categoryList = new HashSet<>();
                }
                categoryList.add(current);
                categoryMap.put(current.getParentCategory(), categoryList);
                current = current.getParentCategory();
            }
        }
        return categoryMap;
    }

    private void printProductsGroupedByCategory(Set<Category> categories, Map<Category, Set<Category>> categoryMap, String indentation) {
        Iterator<Category> categoryIterator = categories.iterator();
        while (categoryIterator.hasNext()) {
            Category category = categoryIterator.next();
            List<Product> productList = getProductsByCategory(category);
            System.out.println(indentation + "+ " + category.getTitle()
                    + " (Product Quantity : " + findProductQuantityForProductList(getProductsInCategory(category))
                    + " , Amount : " + String.format("%.2f TL", findTotalAmountForProductList(getProductsInCategory(category))) + ")"
            );
            for(Product product : productList) {
                System.out.println(indentation + "\tProduct Name    : " + product.getTitle());
                System.out.println(indentation + "\tQuantity        : " + products.get(product));
                System.out.println(indentation + "\tUnit Price      : " + String.format("%.2f TL", product.getPrice()));
                System.out.println(indentation + "\tTotal Price     : " + String.format("%.2f TL", product.getPrice() * products.get(product)));
                System.out.println(indentation + "\t-----------------------------------");
            }
            Set<Category> subCategories = categoryMap.get(category);
            if(subCategories != null && subCategories.size() != 0) {
                printProductsGroupedByCategory(subCategories, categoryMap, "\t");
            }
        }
    }

    private int findProductQuantityForProductList(List<Product> productsList) {
        return productsList.stream()
                .mapToInt(product -> products.get(product))
                .sum();
    }

    private double findTotalAmountForProductList(List<Product> productsList) {
        return productsList.stream()
                .mapToDouble(product -> product.getPrice() * products.get(product))
                .sum();
    }

    public void print() {
        Map<Category, Set<Category>> categoryMap = getCategoryMap();
        Set<Category> parentCategories = getAllCategories().stream()
                .filter(category -> category.getParentCategory() == null)
                .collect(Collectors.toSet());
        printProductsGroupedByCategory(parentCategories, categoryMap, "");
        System.out.println();
        System.out.println("Total Amount                    : " + String.format("%.2f TL", getTotalCartAmount()));
        System.out.println("---------------------------------");
        System.out.println("Campaign Discount               : " + String.format("%.2f TL", getCampaignDiscount()));
        System.out.println("Coupon Discount                 : " + String.format("%.2f TL", getCouponDiscount()));
        System.out.println("Total Discount                  : " + String.format("%.2f TL", getTotalDiscount()));
        System.out.println("---------------------------------");
        System.out.println("Total Amount After Discounts    : " + String.format("%.2f TL", getTotalAmountAfterDiscounts()));
        System.out.println("Delivery Cost                   : " + String.format("%.2f TL", getDeliveryCost()));
    }

}
