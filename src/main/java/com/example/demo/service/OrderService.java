package com.example.demo.service;
import com.example.demo.model.order.*;
import org.springframework.stereotype.Service;
import com.example.demo.model.notification.*;

import java.util.ArrayList;

@Service
public class OrderService {
    DB products = new DB();
    DB customers = new DB();
    OrderDB orders = new OrderDB();
    OrderService() {
        customers.add(new Customer("125", "Tony", 51000.0, "antoy.safaga@gmail.com", "01270856065"));
        products.add(new Product("Yoghurt", "25", 12.5, "El Maraie", "Milks"));
    }
    public ArrayList<Entity> allProducts() {
        return products.getAll();
    }
    public boolean placeSimpleOrder(String location, String customerId) {
        ArrayList<Product> prods = new ArrayList<>();
        prods.add((Product) products.get("25"));
        System.out.println(prods.get(0).print());
        Customer cus = (Customer) (customers.get(customerId));
        Order order = new SimpleOrder(location, cus, prods);
        orders.add(order);
        return true;
    }
    public ArrayList<Order> getAllOrders() {
        return orders.getAll();
    }
}
