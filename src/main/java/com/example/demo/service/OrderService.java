package com.example.demo.service;
import com.example.demo.model.order.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import com.example.demo.model.notification.*;

import java.util.ArrayList;
import java.util.Random;

@Service
public class OrderService {
    MsgQueue queue=new MsgQueue();
    Notification noti;
    DB products = new DB();
    DB customers = new DB();

    OrderService() {
        products.add(new Product("Yoghurt", "125", 12.5, "El Maraie", "Milks"));

    }
    public ArrayList<Entity> allProducts() {
        return products.getAll();
    }
    public boolean placeSimpleOrder(String location, String customerId, ArrayList<Product> prods) {
        Customer cus = new Customer(customers.get(customerId));
        Order order = new SimpleOrder(location, cus, prods);
        return true;
    }
    public Boolean deleteMessage(){
        queue.pop();
        return true;
    }
    public Notification shippmentNotification(Customer user,Order order,String language){
        ShippmentTemp shippment=new ShippmentTemp();
        Random rand=new Random();
        int num= rand.nextInt(2);
        if(num==0){
            String cont= shippment.generate(user,order,"SMS");
            Notification noti=new Notification( cont, language,  user);
            SMSNotification sms=new SMSNotification(order,user);
            noti=sms.createShippmentNoti(cont);
            queue.add(noti);
        }
        else {
            String cont= shippment.generate(user,order,"Email");
            Notification noti=new Notification( cont, language,  user);
            EmailNotification email=new EmailNotification(order,user);
            noti= email.createShippmentNoti(cont);
            queue.add(noti);
        }
        return noti;
    }

}
