package com.example.demo.model.notification;

public abstract class NotificationFactory {
    private Order order;
    private Customer customer;
    public NotificationFactory(Order order, Customer customer) {
        this.order = order;
        this.customer = customer;
    }
    public abstract Notification createPlacemenetNoti() ;
    public abstract Notification createShippmentNoti();
}
