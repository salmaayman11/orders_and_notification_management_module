package com.example.demo.service;
import com.example.demo.model.order.*;
import org.springframework.stereotype.Service;
import com.example.demo.model.notification.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    DB products = new DB();
    DB customers = new DB();
    OrderDB orders = new OrderDB();
    OrderService() {
        customers.add(new Customer("125", "Tony", 51000.0, "antoy.safaga@gmail.com", "01270856065"));
        customers.add(new Customer("126", "Ahmed", 5100.0, "amhed.safaga@gmail.com", "01270856060"));
        products.add(new Product("Yoghurt", "25", 12.5, "El Maraie", "Milks"));
        products.add(new Product("Milk", "12", 60, "El Maraie", "Milks"));
        products.add(new Product("Cheese", "0", 8.4, "El Maraie", "Milks"));
        products.add(new Product("Ahmed Tea", "205", 150.25, "Ahmed Tea", "Beverages"));
        products.add(new Product("Pillow", "69", 50, "The Oriental Weavers", "Cottons"));
    }
    public ArrayList<Entity> allProducts() {
        return products.getAll();
    }
    public boolean placeSimpleOrder(String customerId, String location, List<String> prodsId) {
        ArrayList<Product> prods = new ArrayList<>();
        for (String id :
                prodsId) {
            Product p = (Product) products.get(id);
            if(p == null) {
                System.out.println("No Such products");
                return false;
            }
            prods.add(p);
        }
        Customer cus = (Customer) (customers.get(customerId));
        if(cus == null) {
            System.out.println("No such customer " + customerId);
            return false;
        }
        Order order = new SimpleOrder(location, cus, prods);
        orders.add(order);
        return true;
    }
    public String getAllOrders() {
        String s = "";
        int i = 0;
        for (Order o :
                orders.getAll()) {
            i++;
            s += "Order " + i + "\n";
            s += o.details();
        }
        return s;
    }
}
