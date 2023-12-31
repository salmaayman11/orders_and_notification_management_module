package com.example.demo.model.notification;
import com.example.demo.model.order.*;

import java.util.Objects;

public class PlacementTemp implements OrderTemp {
    public String generate(Customer user, Order order, String channel) {
        return "Dear " + user.getName() + " this a confirmation " + channel + " for your booking with ID " + order.getKey();
    }
}
