package com.example.demo.model.notification;

public class EmailNotification extends NotificationFactory{


    public EmailNotification(Order order, Customer customer) {
        super(order, customer);
    }

    public Notification createPlacemenetNoti(){
        Notification n =new Notification(" ","English",this.customer);
        PlacementTemp temp = new PlacementTemp();
        n.content=PlacementTemp.generate(this.customer,this.order,"Email");
        return n;
    }
    public Notification createShippmentNoti(){
        Notification n =new Notification(" ","English",this.customer);
        ShippmentTemp temp = new ShippmentTemp();
        n.content=ShippmentTemp.generate(this.customer,this.order,"Email");
        return n;
    }
}
