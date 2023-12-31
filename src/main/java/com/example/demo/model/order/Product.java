package com.example.demo.model.order;

public class Product extends Entity{
    String vendor;
    String category;
    public Product(String name, String key, double price, String vendor , String category) {
        super(name, key, price);
        this.category = category;
        this.vendor = vendor;

    }
    public String getCategory() {
        return category;
    }
    public String getVendor() {
        return vendor;
    }
    public String print(){
        String productInfo = "Product Name: " + getName() + "\n" +
                "Product Key: " + getKey() + "\n" +
                "Amount: " + getAmount() + "\n" +
                "Vendor: " + vendor + "\n" +
                "Category: " + category + "\n";
        return productInfo;
    }
}
