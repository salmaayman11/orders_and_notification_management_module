package com.example.demo.model.order;

public class Customer extends Entity {
    String phoneNum;
    String email;

    public Customer (String key, String name, double balance, String email, String phoneNum){
        super(name, key, balance);
        this.email=email;
        this.phoneNum=phoneNum;
    }

    public Customer(Entity entity) {
        super(entity);
    }

    public String getPhoneNum(){
        return phoneNum;
    }
    public String getEmail(){
        return email;
    }

}
