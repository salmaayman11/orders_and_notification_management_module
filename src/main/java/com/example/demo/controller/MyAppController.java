package com.example.demo.controller;
import com.example.demo.model.SimpleRequestOrder;
import com.example.demo.model.order.*;
import com.example.demo.model.order.Product;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.example.demo.model.notification.Notification;
import com.example.demo.model.notification.ShippmentTemp;
import com.example.demo.model.order.Customer;
import com.example.demo.model.order.Entity;
import com.example.demo.model.order.Order;
import com.example.demo.service.OrderService;
import com.example.demo.model.order.*;
import com.example.demo.model.notification.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class MyAppController {

    /*The personService field is annotated with @Autowired,
    indicating that Spring should automatically inject an instance of PersonServiceImpl when creating an instance of MyAppController.
    This service is presumably responsible for business logic related to the Person entity.*/
    @Autowired
    OrderService service ;

    @PostMapping("/add")
    @GetMapping("/get")
    public Notification orderPlacementNotification(@RequestBody Customer user, @RequestBody Order order){
        return service.orderPlacementNotification(user, order);
    }

    @PostMapping("/add/{name}/{balance}/{email}/{phoneNum}")
    public boolean createAccount(@PathVariable ("name") String name, @PathVariable ("balance") double balance,@PathVariable ("email") String email,@PathVariable ("phoneNum") String phoneNum){

        return service.createAccount(name, email, phoneNum, balance);
    }

    @GetMapping("/get")
    public Map<String,Integer> countRemainingPartsByCategories() {
        return service.countRemainingPartsByCategories();
    }


    @GetMapping("/get")
	public ArrayList<Entity> getAll() {
		return service.allProducts();
	}

    @PostMapping("/place/simple")
    public boolean placeSimpleOrder(@RequestBody SimpleRequestOrder request) {
        return service.placeSimpleOrder(request.customerId, request.location, request.productsId);
    }
    @PostMapping("/place/compound")
    public boolean placeCompoundOrder(@RequestBody List<SimpleRequestOrder> request) {
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
    public boolean shipSimpleOrderAPI(@PathVariable("orderKey") String orderKey){
        return service.shipSimpleOrder(orderKey);
    }

    @PostMapping("/shipCompoundOrder/{orderKey}")
    boolean shipCompoundOrderAPI(@PathVariable("orderKey") String orderKey){
        return service.shipCompoundOrder(orderKey);
    }

}
