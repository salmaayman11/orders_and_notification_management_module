package com.example.demo.service;
import com.example.demo.model.order.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.example.demo.model.notification.*;


import java.util.ArrayList;
import java.util.Random;

@Service
public class OrderService {

    MsgQueue queue = new MsgQueue();
    Notification noti;
    OrderDB orders = new OrderDB();
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

    @Async
    public void deleteMessage() throws InterruptedException {
        queue.pop();
        Thread.sleep(30000);
    }

    public Notification shippmentNotification(Customer user, Order order) {
        Random rand = new Random();
        int num = rand.nextInt(2);
        if (num == 0) {
            SMSNotification sms = new SMSNotification(order, user);
            noti = sms.createShippmentNoti();
            queue.add(noti);
        } else {
            EmailNotification email = new EmailNotification(order, user);
            noti = email.createShippmentNoti();
            queue.add(noti);
        }
        return noti;
    }
    public boolean shipSimpleOrder(String orderKey) {
        Order o = (SimpleOrder) orders.get(orderKey);
        if (o != null) {
            Customer c = ((SimpleOrder) o).getCustomer();
            c.setAmount(c.getAmount() - o.fees());
            Notification n = new SMSNotification(o, c).createShippmentNoti();
            Notification nn = new EmailNotification(o, c).createShippmentNoti();
            queue.add(n);
            queue.add(nn);
            return true;
        }
        return false;
    }
    public boolean shipCompoundOrder(String orderKey) {
        Order o = (CompoundOrder) orders.get(orderKey);
        ArrayList<Order> os = ((CompoundOrder) o).getOrders();
        ArrayList<Customer> cs = new ArrayList<>();
        if (o != null) {
            for (Order or : os) {
                Customer c = ((SimpleOrder) or).getCustomer();
                c.setAmount(c.getAmount() - o.fees());
            }
            for (Order or : os) {
                Customer c = ((SimpleOrder) or).getCustomer();
                Notification n = new SMSNotification(or, c).createShippmentNoti();
                Notification nn = new EmailNotification(or, c).createShippmentNoti();
                queue.add(n);
                queue.add(nn);
            }
            return true;
        }
        return false;
    }
}
