package com.example.demo.model.notification;
import com.example.demo.model.order.*;

public interface OrderTemp {
    public String generate(Customer user, Order order, String channel);
}

