package com.company;

public class Company {
    private final String name = "Your company";
    private float money = 1_000_000;
    private float value = 500_000 + this.money;
    Concern[] arrConcern = new Concern[0];
    public boolean moneyCheck(float checker){
        return !(this.money - checker <= 0);
    }
    public String getName() {
        return this.name;
    }
    public float getMoney(){
        return this.money;
    }
    public float getValue(){
        return this.value;
    }
    public void moneyChange(float change){
        this.money+=change;
    }
    public void valueRefresh(){
        this.value = 500_000 + this.money;
        for(int i = 0; i < arrConcern.length;i++) {
            this.value+=arrConcern[i].value;
        }
    }
    public Concern[] arrConcernAdding(Concern[] origArrConcern, Concern elementToAdd)
    {
        Concern[] tempArrConcern = new Concern[origArrConcern.length + 1];
        System.arraycopy(origArrConcern, 0, tempArrConcern, 0, origArrConcern.length);
        tempArrConcern[tempArrConcern.length - 1] = elementToAdd;
        return tempArrConcern;
    }
}
