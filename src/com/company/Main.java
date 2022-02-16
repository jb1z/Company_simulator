package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        Button startButton = new Button("Start");
        startButton.setPrefWidth(80);
        Label nameLabel = new Label();
        Label moneyLabel = new Label();
        Label valueLabel = new Label();
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Company mainCompany = new Company();
                nameLabel.setText("Company's name: " + mainCompany.getName());
                moneyLabel.setText("Company's money:" + String.valueOf(mainCompany.money) + "$");
                valueLabel.setText("Value: " + String.valueOf(mainCompany.value) + "$");
            }
        });
        FlowPane root = new FlowPane(startButton, nameLabel, moneyLabel, valueLabel);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("JavaFX Application");
        stage.setWidth(1000);
        stage.setHeight(500);
        stage.show();
    }
}