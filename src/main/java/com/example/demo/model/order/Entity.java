package com.example.demo.model.order;

public abstract class Entity {
    String name;
    String key;
    double amountOfMoney;
    public Entity(String name,String key, double amountOfMoney){
        this.key=key;
        this.amountOfMoney=amountOfMoney;
        this.name =name;
    }
    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public double getAmount() {
        return amountOfMoney;
    }

    public void setAmount(double newAmount) {
        this.amountOfMoney = newAmount;
    }
}
