package com.example.demo.model.notification;

public class ShippmentTemp implements OrderTemp {

    public String generate(Customer user , Order order,String channel){
        String message;

        message= "Dear "+ Customer.name+ " ";
        if(channel=="SMS"){
            message+="this is a "+channel;
        }
        else
            message+="this is an "+channel;
        if (order instanceof SimpleOrder) {
            for()
        }

        return " ";



    }
}
