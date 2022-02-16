package com.company;

public class Company {
    private String name = "Your company";
    float money = 1_000_000;
    float value = 500_000 + this.money;
    Concern[] arrConcern;
    public String getName()
    {
        return this.name;
    }
}
