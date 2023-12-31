package com.example.demo.model.notification;
import com.example.demo.model.order.*;

public class EmailNotification extends NotificationFactory{


    public EmailNotification(Order order, Customer customer) {
        super(order, customer);
    }

    public Notification createPlacemenetNoti(){
        Notification n = new Notification(new PlacementTemp().generate(this.customer, this.order, "Email"),"English",this.customer);
        return n;
    }
    public Notification createShippmentNoti(){
        Notification n = new Notification(new ShippmentTemp().generate(this.customer, this.order, "Email"),"English",this.customer);
        return n;
    }
}
