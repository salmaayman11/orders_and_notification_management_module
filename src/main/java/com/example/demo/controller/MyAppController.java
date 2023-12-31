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

@RestController
@RequestMapping("/order")
public class MyAppController {

    /*The personService field is annotated with @Autowired,
    indicating that Spring should automatically inject an instance of PersonServiceImpl when creating an instance of MyAppController.
    This service is presumably responsible for business logic related to the Person entity.*/
    @Autowired
    OrderService service;
        
    @GetMapping("/product/get")
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
    @GetMapping("/get")
    public String getAllOrders() {
        return service.getAllOrders();
    }
    @GetMapping("/get/customer/{id}")
    public Customer getCustomer(@PathVariable("id") String id) {
        return service.getCustomer(id);
    }
}
