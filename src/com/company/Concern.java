package com.company;

public class Concern {
    private static int count = 0;
    private String name = "";
    private byte type = 0;
    private float value = 0;
    private int creatingTime = 0;
    public Production concernProduction = new Production();
    Concern(String name, byte type, float value, int creatingTime){
        this.name = name;
        this.type = type;
        this.value = value;
        this.creatingTime = creatingTime;
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
    public int getCreatingTime(){
        return creatingTime; }
    public void setType(byte type){
        this.type = type;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setValue(float value){
        this.value = value;
    }
    public void setCreatingTime(int creatingTime){
        this.creatingTime = creatingTime;
    }
}
