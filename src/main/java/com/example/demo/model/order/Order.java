package com.example.demo.model.order;

public interface Order {
    public String details();
    public double cost();
    public double fees();
    public String location();
    public String getKey();
}
