package com.company;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        Company mainCompany = new Company();
        /*Starting labels*/
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

        Label agricultureConcern = new Label("Agriculture cost is 200k$");
        Button agricultureButton = new Button("Buy");
        agricultureButton.setPrefWidth(80);
        agricultureConcern.setVisible(false);
        agricultureButton.setVisible(false);
        StackPane.setAlignment(agricultureConcern, Pos.TOP_LEFT);
        StackPane.setMargin(agricultureConcern, new Insets(65.0, 0.0, 0.0, 90.0));
        StackPane.setAlignment(agricultureButton, Pos.TOP_LEFT);
        StackPane.setMargin(agricultureButton, new Insets(60.0, 0.0, 0.0, 0.0));

        Label energyConcern = new Label("Energy cost is 300k$");
        Button energyButton = new Button("Buy");
        energyButton.setPrefWidth(80);
        energyConcern.setVisible(false);
        energyButton.setVisible(false);
        StackPane.setAlignment(energyConcern, Pos.TOP_LEFT);
        StackPane.setMargin(energyConcern, new Insets(105.0, 0.0, 0.0, 90.0));
        StackPane.setAlignment(energyButton, Pos.TOP_LEFT);
        StackPane.setMargin(energyButton, new Insets(100.0, 0.0, 0.0, 0.0));

        Label itConcern = new Label("IT cost is 400k$");
        Button itButton = new Button("Buy");
        itButton.setPrefWidth(80);
        itConcern.setVisible(false);
        itButton.setVisible(false);
        StackPane.setAlignment(itConcern, Pos.TOP_LEFT);
        StackPane.setMargin(itConcern, new Insets(145.0, 0.0, 0.0, 90.0));
        StackPane.setAlignment(itButton, Pos.TOP_LEFT);
        StackPane.setMargin(itButton, new Insets(140.0, 0.0, 0.0, 0.0));

        startButton.setOnAction(event -> {
            nameLabel.setText("Company's name: " + mainCompany.getName());
            moneyLabel.setText("Company's money:" + mainCompany.getMoney() + "$");
            valueLabel.setText("Value: " + mainCompany.getValue() + "$");
            startButton.setVisible(false);
            agricultureConcern.setVisible(true);
            agricultureButton.setVisible(true);
            energyConcern.setVisible(true);
            energyButton.setVisible(true);
            itConcern.setVisible(true);
            itButton.setVisible(true);
        });
        agricultureButton.setOnAction(event ->{
            mainCompany.moneyChange(-200_000);
            moneyLabel.setText("Company's money:" + mainCompany.getMoney() + "$");
            mainCompany.valueRefresh();
            valueLabel.setText("Value: " + mainCompany.getValue() + "$");
        });
        energyButton.setOnAction(event ->{
            mainCompany.moneyChange(-300_000);
            moneyLabel.setText("Company's money:" + mainCompany.getMoney() + "$");
            mainCompany.valueRefresh();
            valueLabel.setText("Value: " + mainCompany.getValue() + "$");
        });
        itButton.setOnAction(event->{
            mainCompany.moneyChange(-400_000);
            moneyLabel.setText("Company's money:" + mainCompany.getMoney() + "$");
            mainCompany.valueRefresh();
            valueLabel.setText("Value: " + mainCompany.getValue() + "$");
        });

        /*Scene activating*/
        root.getChildren().addAll(startButton, nameLabel, moneyLabel, valueLabel, agricultureConcern, agricultureButton,
                energyConcern, energyButton, itConcern, itButton);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Company");
        stage.setWidth(1000);
        stage.setHeight(500);
        stage.show();
    }
}