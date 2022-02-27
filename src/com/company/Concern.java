package com.company;

public class Concern {
    private static int count = 0;
    private String name = "";
    private byte type = 0; //1 - AC; 2 - EN; 3 - IT
    private float value = 0;
    private int creatingTime = 0;
    public Production consumeProduction;
    Concern(String name, byte type, float value, int creatingTime){
        this.name = name;
        this.type = type;
        this.value = value;
        this.creatingTime = creatingTime;
        if (type == 1) {
            this.consumeProduction = new Production((byte) 3, 0, 20);
        } else if (type == 2) {
            this.consumeProduction = new Production((byte) 1, 0, 20);
        } else if (type == 3) {
            this.consumeProduction = new Production((byte) 2, 0, 20);
        }
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
