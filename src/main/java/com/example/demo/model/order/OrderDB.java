package com.example.demo.model.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderDB {
    private Map<String, Order> list = new HashMap<>();

    public boolean add(Order order){
        if(list.containsKey(order.getKey())){
            return false;
        }
        else{
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
    public ArrayList<Order> getAll(){
        return new ArrayList<>(list.values());
    }
}
