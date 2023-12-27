package com.example.demo.service;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.example.demo.model.Person;

/**
 *
 * @author eman.yehia
 */
public interface PersonService {

	public Boolean addPerson(Person p);
	
	public Boolean deletePerson(int id);
	
	public Person getPerson(int id);
	
	public Person[] getAllPersons();

}
