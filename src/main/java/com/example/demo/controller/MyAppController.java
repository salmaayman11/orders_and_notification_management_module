package com.example.demo.controller;


import com.example.demo.model.order.*;
import com.example.demo.model.order.Product;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    public boolean placeSimpleOrder() {
        return service.placeSimpleOrder("ALex", "125");
    }
    @GetMapping("/get")
    public ArrayList<String> getAllOrders() {
        ArrayList<String> s = new ArrayList<>();
        for (Order o :
                service.getAllOrders()) {
            s.add(o.details());
        }
        return s;
    }
}
