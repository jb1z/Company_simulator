package com.company;

public class Concern {
    String name = "";
    byte type = 0;
    float value = 0;
    float profitPerDay = 0;
    static int count = 0;
    Concern(String name, byte type, float value){
        this.name = name;
        this.type = type;
        this.value = value;
        this.profitPerDay = 10000;
        count++;
    }
}
