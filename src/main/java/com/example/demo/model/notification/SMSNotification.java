package com.example.demo.model.notification;
import com.example.demo.model.order.*;

public class SMSNotification extends NotificationFactory{
    public SMSNotification(Order order, Customer customer) {
        super(order, customer);
    }
    public Notification createPlacemenetNoti(){
        Notification n =new Notification(" ","English",this.customer);
        n.setContent(new PlacementTemp().generate(this.customer, this.order, "Email"));
        return n;
    }
    public Notification createShippmentNoti(){
        Notification n =new Notification(" ","English",this.customer);
        n.setContent(new ShippmentTemp().generate(this.customer, this.order, "Email"));
        return n;
    }
}

