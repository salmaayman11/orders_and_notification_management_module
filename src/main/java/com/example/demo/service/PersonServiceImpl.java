package com.example.demo.service;


import java.util.Set;

import com.example.demo.Common;
import com.example.demo.model.Person;
import org.springframework.stereotype.Service;


@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public Boolean addPerson(Person p) {
        try {
            if(Common.persons.get(p.getId()) != null){
                return false;
            }
            Common.persons.put(p.getId(), p);
        } catch (Exception e) {
            System.out.println("Exception in addPerson as" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Boolean deletePerson(int id) {
        try {
            if(Common.persons.get(id) == null){
                return false;
            }
            Common.persons.remove(id);
        } catch (Exception e) {
            System.out.println("Exception in addPerson as" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Person getPerson(int id) {
        try {
            return Common.persons.get(id);
        } catch (Exception e) {
            System.out.println("Exception in getPerson as" + e.getMessage());
        }
        return null;
    }

    @Override
    public Person[] getAllPersons() {
        try {
            Set<Integer> ids = Common.persons.keySet();
		Person[] p = new Person[ids.size()];
		int i=0;
		for(Integer id : ids){
			p[i] = Common.persons.get(id);
			i++;
		}
		return p;
        } catch (Exception e) {
            System.out.println("Exception in getAllPersons as" + e.getMessage());
        }
        return null;
    }
    
    public Person getDummyPerson(int id) {
        try {
            Person p = new Person();
		p.setAge(99);
		p.setName("Dummy");
		p.setId(id);
		return p;
        } catch (Exception e) {
            System.out.println("Exception in getDummyPerson as" + e.getMessage());
        }
	return null;	
	}
}
