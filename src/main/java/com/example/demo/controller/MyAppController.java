package com.example.demo.controller;
import com.example.demo.model.AllProducts;
import com.example.demo.model.SimpleRequestOrder;
import com.example.demo.model.notification.MsgQueue;
import com.example.demo.model.order.*;
import com.example.demo.model.order.Product;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
@RequestMapping("/order")
public class MyAppController {
    /*The personService field is annotated with @Autowired,
    indicating that Spring should automatically inject an instance of PersonServiceImpl when creating an instance of MyAppController.
    This service is presumably responsible for business logic related to the Person entity.*/
    @Autowired
    OrderService service;

    @GetMapping("/product/get")
	public AllProducts getAll() {
        AllProducts all = new AllProducts();
        all.products = service.allProducts();
        all.categories = service.countRemainingPartsByCategories();
		return all;
	}

    @PostMapping("/place/simple")
    public boolean placeSimpleOrder(@RequestBody SimpleRequestOrder request) throws InterruptedException {
        return service.placeSimpleOrder(request.customerId, request.location, request.productsId);
    }
    @PostMapping("/place/compound")
    public boolean placeCompoundOrder(@RequestBody List<SimpleRequestOrder> request) throws InterruptedException {
        return service.placeCompoundOrder(request);
    }
    @GetMapping("/getall")
    public String getAllOrders() {
        return service.getAllOrders();
    }
    @GetMapping("/get/customer/{id}")
    public Customer getCustomer(@PathVariable("id") String id) {
        return service.getCustomer(id);
    }

//    @DeleteMapping()
//    public void deleteM() throws InterruptedException {
//        service.deleteMessage();
//    }
//    @PostMapping("/add/noti")
//    public Notification shippingNoti(@RequestBody Customer user, @RequestBody Order order ) throws InterruptedException {
//        return service.shippmentNotification(user, order);
//    }
    @PostMapping("/shipSimpleOrder/{orderKey}")
    public boolean shipSimpleOrderAPI(@PathVariable("orderKey") String orderKey) throws InterruptedException {
        return service.shipSimpleOrder(orderKey);
    }
    @PostMapping("/shipCompoundOrder/{orderKey}")
    boolean shipCompoundOrderAPI(@PathVariable("orderKey") String orderKey) throws InterruptedException {
        return service.shipCompoundOrder(orderKey);
    }
//    @GetMapping("/get/notification")
//    public Notification orderPlacementNotification(@RequestBody Customer user, @RequestBody Order order) throws InterruptedException {
//        return service.orderPlacementNotification(user, order);
//    }
    @PostMapping("/add/customer")
    public String createAccount(@RequestBody Customer customer){
        return service.createAccount(customer);
    }
    @GetMapping("/get/msgqueue")
    public Queue<Notification> getMsgQueue() {
        return service.getQueueNotifications();
    }
}
