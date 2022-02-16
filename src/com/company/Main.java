package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        StackPane root = new StackPane();
        Button startButton = new Button("Start");
        Label nameLabel = new Label();
        StackPane.setAlignment(nameLabel, Pos.TOP_LEFT);
        Label moneyLabel = new Label();
        StackPane.setAlignment(moneyLabel, Pos.TOP_LEFT);
        StackPane.setMargin(moneyLabel, new Insets(20.0, 0.0, 0.0, 0.0));
        Label valueLabel = new Label();
        StackPane.setAlignment(valueLabel, Pos.TOP_LEFT);
        StackPane.setMargin(valueLabel, new Insets(40.0, 0.0, 0.0, 0.0));
        root.getChildren().addAll(startButton, nameLabel, moneyLabel, valueLabel);
        startButton.setOnAction(event -> {
            Company mainCompany = new Company();
            nameLabel.setText("Company's name: " + mainCompany.getName());
            moneyLabel.setText("Company's money:" + mainCompany.money + "$");
            valueLabel.setText("Value: " + mainCompany.value + "$");
            startButton.setVisible(false);
        });
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Company");
        stage.setWidth(1000);
        stage.setHeight(500);
        stage.show();
    }
}