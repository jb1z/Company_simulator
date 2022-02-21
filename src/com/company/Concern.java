package com.company;

public class Concern {
    private String name = "";
    private byte type = 0;
    private float value = 0;
    private static int count = 0;
    public Production concernProduction = new Production();
    Concern(String name, byte type, float value){
        this.name = name;
        this.type = type;
        this.value = value;
        count++;
    }
    public static int getCount(){
        return count;
    }
    public byte getType(){
        return type;
    }
    public String getName(){
        return name;
    }
    public float getValue(){
        return value;
    }
    public void setType(byte type){
        this.type = type;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setValue(float value){
        this.value = value;
    }
}
