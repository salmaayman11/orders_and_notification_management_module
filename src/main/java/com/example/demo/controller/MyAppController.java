package com.example.demo.controller;

import com.example.demo.model.order.Entity;
import com.example.demo.service.OrderService;
import com.example.demo.model.order.*;
import com.example.demo.model.notification.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/api")
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
}
