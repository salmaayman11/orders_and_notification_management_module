package com.example.demo.service;
import com.example.demo.model.order.*;
import org.springframework.stereotype.Service;
import com.example.demo.model.notification.*;

import java.util.ArrayList;

@Service
public class OrderService {
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
}
