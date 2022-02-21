package com.company;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.scene.control.Label;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class ThreadedTimer extends Thread{
    private int i = 0;
    public Label timerLabel = new Label("Hours 0");
    ThreadedTimer(){
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(10), e->{
            this.i++;
            this.timerLabel.setText("Hours " + i);
        }));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }
}
