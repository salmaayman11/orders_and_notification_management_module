package com.example.demo.model.notification;

public class PlacementTemp implements OrderTemp {
    public String generate(Customer user, Order order, String channel) {
        if (channel == "SMS") {
            return "Dear " + Customer.name + " this a confirmation message for your booking with ID " + order.getKey();
        }
        else if (channel=="Email"){
            return "Dear " + Customer.name + " this a confirmation email for your booking with ID " + order.getKey();

        }
    }
}
