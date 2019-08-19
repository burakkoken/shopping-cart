package com.trendyol.shoppingcart.cart;

/**
 * Created by Burak Köken on 16.8.2019.
 */
public class Category {

    private String title;
    private Category parentCategory;

    public Category(String title) {
        this.title = title;
    }

    public Category(String title, Category parentCategory) {
        this.title = title;
        this.parentCategory = parentCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public boolean inCategory(Category category) {
        if(equals(category)) {
            return true;
        }
        return (parentCategory != null && parentCategory.inCategory(category));
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Category)) {
            return false;
        }
        Category category = (Category) obj;
        return category.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        int result;
        result = 31 * title.hashCode();
        result = result + ((parentCategory != null) ? parentCategory.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return (parentCategory == null) ? "/" + title : parentCategory.toString() + "/" + title;
    }

}
