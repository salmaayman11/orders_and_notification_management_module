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
            message+=" your booking of the"+ ((SimpleOrder) order).details +" with ID "+Order.getKey()+" is shipped successfully "+"thanks for using our store";
        }
        else if (order instanceof CompoundOrder) {
            for (Order sub : sub) {
                if (sub instanceof SimpleOrder) {
                    message += ((SimpleOrder) order).details + " ";
                }
            }
            message+="with ID "+Order.getKey()+" is shipped successfully "+"thanks for using our store";
        }

        return message;



    }
}
