package com.example.demo.model.notification;
import com.example.demo.model.order.*;

public class ShippmentTemp implements OrderTemp {

    public String generate(Customer user, Order order, String channel){
        String message;
        message= "Dear "+ user.getName() + "\n";
        message+="This is a confirmation "+channel+" for your order:\n" + order.details() + '\n';
        if(order instanceof SimpleOrder) message+="Order fees: " + order.fees();
        message+="\nIs shipped successfully! thanks for using our store";

        return message;
    }
}
