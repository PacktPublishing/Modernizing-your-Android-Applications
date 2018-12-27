package com.packt.coolnewandroid;

import java.io.Serializable;

public class Automobile implements Serializable {

    private String make;
    private String model;

    public Automobile(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getName() {
        return make + model;
    }

}