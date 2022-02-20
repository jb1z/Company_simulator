package com.company;

public class Concern {
    String name = "";
    byte type = 0;
    float value = 0;
    private static int count = 0;
    Production[] arrProduction = new Production[0];
    Concern(String name, byte type, float value){
        this.name = name;
        this.type = type;
        this.value = value;
        count++;
    }
    public Production[] arrProductionAdding(Production[] origArrProduction, Production elementToAdd)
    {
        Production[] tempArrProduction = new Production[origArrProduction.length + 1];
        System.arraycopy(origArrProduction, 0, tempArrProduction, 0, origArrProduction.length);
        tempArrProduction[tempArrProduction.length - 1] = elementToAdd;
        return tempArrProduction;
    }
    public static int getCount(){
        return count;
    }
}
