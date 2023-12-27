package com.example.demo.controller;


import com.example.demo.model.Person;
import com.example.demo.service.PersonServiceImpl;
import com.example.demo.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyAppController {

    /*The personService field is annotated with @Autowired,
    indicating that Spring should automatically inject an instance of PersonServiceImpl when creating an instance of MyAppController.
    This service is presumably responsible for business logic related to the Person entity.*/
    @Autowired
    PersonServiceImpl personService;

    @PostMapping("/add")
    public Response addPerson(@RequestBody Person p) {
        System.out.println("in add person"+p);
        boolean res = personService.addPerson(p);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Person Already Exists");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Person created successfully");
        return response;
    }


    @DeleteMapping("/delete/{id}")
    public Response deletePerson(@PathVariable("id") int id) {
        System.out.println("in delete with id:"+id);
        boolean res = personService.deletePerson(id);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Person Doesn't Exists");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Person deleted successfully");
        return response;
    }
    @GetMapping("/get/{id}")
	public Person getPerson(@PathVariable("id") int id) {
            System.out.println("in get with id:" + id);
		return personService.getPerson(id);
	}
        
    @GetMapping("/getDummy/{id}")
	public Person getDummyPerson(@PathVariable("id") int id) {
            System.out.println("in get dummy id:" + id);
		return personService.getDummyPerson(id);
	}
        
    @GetMapping("/get")
	public Person[] getAll() {
        System.out.println("in getAll");
		return personService.getAllPersons();
	}

}
