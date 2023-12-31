package com.example.demo.model.notification;
import com.example.demo.model.order.*;

public class SMSNotification extends NotificationFactory{
    public SMSNotification(Order order, Customer customer) {
        super(order, customer);
    }
    public Notification createPlacemenetNoti(){
        return new Notification(new PlacementTemp().generate(this.customer, this.order, "Email"),"English",this.customer);
    }
    public Notification createShippmentNoti(){
        return new Notification(new ShippmentTemp().generate(this.customer, this.order, "Email"),"English",this.customer);
    }
}

