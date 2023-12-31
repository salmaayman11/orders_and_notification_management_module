package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SimpleRequestOrder {
    @JsonProperty("customerId")
    public String customerId;
    @JsonProperty("location")
    public String location;
    public List<String> productsId;
}
