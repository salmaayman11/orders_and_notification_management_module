package com.example.demo.model.order;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class CompoundOrder implements Order {
    private ArrayList<Order> orders = new ArrayList<Order>();
    private String key = "";

    public CompoundOrder() {
        key = UUID.randomUUID().toString();
    }
    public boolean add(Order order) {
        return check(order.location()) && orders.add(order);
    }
    public boolean remove(Order order) {
        return orders.remove(order);
    }
    private boolean check(String location) {
        return orders.isEmpty() || Objects.equals(orders.get(0).location(), location);
    }
    public double cost() {
        double cost = 0;
        for (Order o :
                orders) {
            cost += o.cost();
        }
        return cost;
    }
    @Override
    public String details() {
        String s = "Compound order:\n" + getKey() + "\nWith orders: " + '\n';
        double cost = 0;
        for (int i = 0; i < orders.size(); i++) {
            s += "Order " + (i+1) + ":\n" + orders.get(i).details() + '\n';
            cost += orders.get(i).cost();
        }
        s += "Total cost: " + cost + '\n';
        s += "Fees: " + fees() + '\n';
        return s;
    }
    @Override
    public double fees() {
        return cost() * 0.25;
    }
    @Override
    public String location() {
        return orders.get(0).location();
    }
    @Override
    public String getKey() {
        return key;
    }
    public ArrayList<Order>getOrders(){
        return orders;
    }
}
