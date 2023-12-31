package com.example.demo.service;
import com.example.demo.model.order.*;
import org.springframework.stereotype.Service;
import com.example.demo.model.notification.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class OrderService {
    DB products = new DB();
    DB customers = new DB();
    MsgQueue msgQueue = new MsgQueue();
    Notification noti;
    OrderService() {
        products.add(new Product("Cheese", "126", 8.0, "Domtey", "Milks"));
        products.add(new Product("Juice", "127", 2.0, "Johaina", "Beverages"));
        products.add(new Product("Soda", "128", 5.0, "V7", "Beverages"));
        products.add(new Product("Yoghurt", "125", 12.5, "El Maraie", "Milks"));
    }
    public ArrayList<Entity> allProducts() {
        return products.getAll();
    }

    public Map<String,Integer> countRemainingPartsByCategories(){
        //ArrayList<Entity> productsList = products.getAll();
        ArrayList<Entity> productsList = allProducts();
        Map<String, Integer> categoryRemainingParts = new HashMap<>();

        for(Entity entity: productsList){
            if(entity instanceof Product){
                Product product = (Product) entity;
                String category = product.getCategory();
                int remainingParts = categoryRemainingParts.getOrDefault(category,0);
                remainingParts ++;
                categoryRemainingParts.put(category,remainingParts);
            }
        }
        return categoryRemainingParts;
    }

    public boolean createAccount( String name, String email, String phoneNum , double balance){
      // Random customerKey = new Random();
       String customKey = UUID.randomUUID().toString();
        Customer newCustomer = new Customer(customKey, name, balance, email, phoneNum);
        boolean isAccountAdded = customers.add(newCustomer);
        return isAccountAdded;
    }


    public boolean placeSimpleOrder(String location, String customerId, ArrayList<Product> prods) {
        Customer cus = new Customer(customers.get(customerId));
        Order order = new SimpleOrder(location, cus, prods);
        return true;
    }

    public Notification orderPlacementNotification(Customer customer, Order order){
        Random random = new Random();
        int randomChannel = random.nextInt(2);
        if (randomChannel == 0 ){ //SMS notification

            SMSNotification smsNotification = new SMSNotification(order, customer);
            noti = smsNotification.createPlacemenetNoti();
            if(msgQueue.getAll().isEmpty()){
                msgQueue.add(noti);
                deleteMessage();
            }else{
                msgQueue.add(noti);
            }
        }
        else {
            EmailNotification emailNotification = new EmailNotification(order, customer);
            Notification noti = emailNotification.createPlacemenetNoti();
            if(msgQueue.getAll().isEmpty()){
                msgQueue.add(noti);
                deleteMessage();
            }else{
                msgQueue.add(noti);
            }

        }
       return noti;
    }



}
