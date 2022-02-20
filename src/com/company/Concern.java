package com.company;

public class Concern {
    String name = "";
    byte type = 0;
    float value = 0;
    private static int count = 0;
    Concern(String name, byte type, float value){
        this.name = name;
        this.type = type;
        this.value = value;
        count++;
    }
    public static int getCount(){
        return count;
    }
}
