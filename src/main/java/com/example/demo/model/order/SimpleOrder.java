package com.example.demo.model.order;

import java.util.ArrayList;

public class SimpleOrder implements Order {

    private ArrayList<Product> products = new ArrayList<Product>();
    private String location;
    private Customer customer;
    private String key = "";
    public Customer getCustomer() {
        return customer;
    }

    public SimpleOrder(String location, Customer customer, ArrayList<Product> prods) {
        this.location = location;
        this.customer = customer;
        products = prods;
        getKey();
    }

    @Override
    public double cost() {
        double cost = 0;
        for (Product p :
                products) {
            cost += p.getAmount();
        }
        return cost;
    }
    @Override
    public void setKey(String s) {
        key = s;
    }
    @Override
    public double fees() {
        return cost() * 0.15;
    }
    @Override
    public String location() {
        return location;
    }
    @Override
    public String details() {
        String s = "Order code: " + getKey() + '\n' + "With products\n";
        for (int i = 0; i < products.size(); i++) {
            s += "Product " + (i+1) + ":\n" + products.get(i).print() + '\n';
        }
        s += "For customer: " + customer.getName() + "\n" + "Location: " + location + '\n' + "Cost: " +  cost() + "\n";
        return s;
    }
    @Override
    public String getKey() {
        for (Product p :
                products) {
            key += p.getKey();
        }
        return key;
    }
    public Customer getCustomer(){
        return customer;
    }
}
