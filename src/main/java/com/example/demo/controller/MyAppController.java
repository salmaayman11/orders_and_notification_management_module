package com.example.demo.controller;
import com.example.demo.model.notification.Notification;
import com.example.demo.model.notification.ShippmentTemp;
import com.example.demo.model.order.Customer;
import com.example.demo.model.order.Entity;
import com.example.demo.model.order.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
@RestController
@RequestMapping("/api")
public class MyAppController {
    /*The personService field is annotated with @Autowired,
    indicating that Spring should automatically inject an instance of PersonServiceImpl when creating an instance of MyAppController.
    This service is presumably responsible for business logic related to the Person entity.*/
    @Autowired
    OrderService service;
    @GetMapping("/get")
    public ArrayList<Entity> getAll() {
        return service.allProducts();
    }
    @DeleteMapping()
    public Boolean deleteM() {
        return service.deleteMessage();
    }

    @PostMapping("/add")
    @GetMapping("/get")
    public Notification shippingNoti(@RequestBody Customer user, @RequestBody Order order,String lan ) {
        return service.shippmentNotification(user, order,lan);
    }
}
