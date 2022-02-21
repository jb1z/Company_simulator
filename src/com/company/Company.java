package com.company;

public class Company {
    private final String name = "Your company";
    private float money;
    private float value;
    Concern[] arrConcern = new Concern[0];
    public String getName() {
        return this.name;
    }
    public float getMoney(){
        return this.money;
    }
    public float getValue(){
        return this.value;
    }
    Production[] arrCompanyProduction = new Production[3];
    Company(){
        for(int i =0; i < arrCompanyProduction.length; i++){
            arrCompanyProduction[i] = new Production();
            arrCompanyProduction[i].setAmount(100);
            arrCompanyProduction[i].setType((byte)(i+1));
        }
        arrCompanyProduction[0].setValue(200);
        arrCompanyProduction[1].setValue(400);
        arrCompanyProduction[2].setValue(800);
        money = 1_000_000;
        valueRefresh();
    }
    public void moneyChange(float change){
        this.money+=change;
    }
    public void valueRefresh(){
        this.value = 500_000 + this.money;
        for(int i = 0; i < arrConcern.length;i++) {
            this.value+=arrConcern[i].getValue();
        }
        for(int i = 0; i < arrCompanyProduction.length; i++){
            this.value+=(arrCompanyProduction[i].getValue() * arrCompanyProduction[i].getAmount());
        }
    }
    public Concern[] arrConcernAdding(Concern[] origArrConcern, Concern elementToAdd)
    {
        Concern[] tempArrConcern = new Concern[origArrConcern.length + 1];
        System.arraycopy(origArrConcern, 0, tempArrConcern, 0, origArrConcern.length);
        tempArrConcern[tempArrConcern.length - 1] = elementToAdd;
        return tempArrConcern;
    }
    /*public Production[] arrProductionAdding(Production[] origArrProduction, Production elementToAdd)
    {
        Production[] tempArrProduction = new Production[origArrProduction.length + 1];
        System.arraycopy(origArrProduction, 0, tempArrProduction, 0, origArrProduction.length);
        tempArrProduction[tempArrProduction.length - 1] = elementToAdd;
        return tempArrProduction;
    }*/
    public int getCount(){
        return Concern.getCount();
    }
}
