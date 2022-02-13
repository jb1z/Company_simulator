package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.text.Text;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        Button startButton = new Button("Start");
        startButton.setPrefWidth(80);
        Label nameLabel = new Label();
        nameLabel.setPrefWidth(100);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Company mainCompany = new Company();
                nameLabel.setText(mainCompany.name);
            }
        });
        FlowPane root = new FlowPane(startButton, nameLabel);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("JavaFX Application");
        stage.setWidth(300);
        stage.setHeight(250);
        stage.show();
    }
}