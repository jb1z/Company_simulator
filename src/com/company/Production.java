package com.company;

public class Production {
    private byte type = 0;
    private float value = 0; // value of unit
    private int amount = 0;
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
