package com.example.demo.model.order;

import java.util.*;

public class OrderDB {
    private Map<String, Order> list = new HashMap<>();
    private int count;

    public boolean add(Order order){
        order.setKey(order.getKey() + count);
        if(list.containsKey(order.getKey())){
            return false;
        }
        else {
            list.put(order.getKey(), order);
            return true;
        }

    }
    public boolean delete(String key){
        if(list.containsKey(key)){
            list.remove(key);
            return true;
        }
        else{
            return false;
        }
    }
    public Order get(String key){
        return list.getOrDefault(key, null);
    }

    public List<Order> getAll() {
        return new ArrayList<Order>(list.values());
    }
}
