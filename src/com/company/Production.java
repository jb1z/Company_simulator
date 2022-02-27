package com.company;

public class Production {
    private byte type = 0; //1 - AC; 2 - EN; 3 - IT
    private float value = 0; // value of unit
    private int amount = 0;
    Production(byte type, float value, int amount){
        this.type = type;
        this.value = value;
        this.amount = amount;
    }
    public byte getType(){
        return type;
    }
    public float getValue(){
        return value;
    }
    public int getAmount(){
        return amount;
    }
    public void setType(byte type){
        this.type = type;
    }
    public void setValue(float value){
        this.value = value;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
}
