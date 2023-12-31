package com.example.demo.model.notification;
import com.example.demo.model.order.*;

public abstract class NotificationFactory {
    Order order;
    Customer customer;
    public NotificationFactory(Order order, Customer customer) {
        this.order = order;
        this.customer = customer;
    }
    public abstract Notification createPlacemenetNoti(String cont) ;
    public abstract Notification createShippmentNoti();
}
