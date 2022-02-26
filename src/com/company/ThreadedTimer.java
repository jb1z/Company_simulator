package com.company;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.scene.control.Label;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class ThreadedTimer extends Thread{
    private int i = 0;
    public Label timerLabel = new Label("Hours 0");
    ThreadedTimer(Company mainCompany, Label[] amountLabels, Label valueLabel){
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), e->{
            this.i++;
            this.timerLabel.setText("Hours " + i);
            TimeExchanger.setTime(i);
            for(int j = 0; j < mainCompany.arrConcern.length; j++){
                if((i - mainCompany.arrConcern[j].getCreatingTime()) % 12 == 0){
                    mainCompany.arrCompanyProduction[mainCompany.arrConcern[j].getType() - 1].setAmount(
                            mainCompany.arrCompanyProduction[mainCompany.arrConcern[j].getType() - 1].getAmount() + 100);
                amountLabels[mainCompany.arrConcern[j].getType() - 1].setText("Amount: " +
                        mainCompany.arrCompanyProduction[mainCompany.arrConcern[j].getType() - 1].getAmount()
                + "/" + mainCompany.productionStorage[mainCompany.arrConcern[j].getType() - 1]);
                mainCompany.valueRefresh();
                valueLabel.setText("Company's value: " + mainCompany.getValue() + "$");
                }
            }
        }));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }
}
