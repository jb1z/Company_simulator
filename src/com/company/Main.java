package com.company;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    public void buttonClick(Company company, Label moneyLabel, Label valueLabel, ObservableList<String> concerns,
                            String concernType, float moneyChange, byte concernTypeID){
        company.moneyChange(-moneyChange);
        moneyLabel.setText("Company's money:" + company.getMoney() + "$");
        valueLabel.setText("Value: " + company.getValue() + "$");
        company.arrConcern = company.arrConcernAdding(company.arrConcern, new Concern(concernType, concernTypeID, moneyChange));
        company.valueRefresh();
        concerns.add(concernType);
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
        /*Label&Button for buying agriculture*/
        Label agricultureConcern = new Label("Agriculture cost is 200k$");
        Button agricultureButton = new Button("Buy");
        agricultureButton.setPrefWidth(80);
        agricultureConcern.setVisible(false);
        agricultureButton.setVisible(false);
        StackPane.setAlignment(agricultureConcern, Pos.TOP_LEFT);
        StackPane.setMargin(agricultureConcern, new Insets(65.0, 0.0, 0.0, 90.0));
        StackPane.setAlignment(agricultureButton, Pos.TOP_LEFT);
        StackPane.setMargin(agricultureButton, new Insets(60.0, 0.0, 0.0, 0.0));
        /*Label&Button for buying energy*/
        Label energyConcern = new Label("Energy cost is 300k$");
        Button energyButton = new Button("Buy");
        energyButton.setPrefWidth(80);
        energyConcern.setVisible(false);
        energyButton.setVisible(false);
        StackPane.setAlignment(energyConcern, Pos.TOP_LEFT);
        StackPane.setMargin(energyConcern, new Insets(105.0, 0.0, 0.0, 90.0));
        StackPane.setAlignment(energyButton, Pos.TOP_LEFT);
        StackPane.setMargin(energyButton, new Insets(100.0, 0.0, 0.0, 0.0));
        /*Label&Button for buying it*/
        Label itConcern = new Label("IT cost is 400k$");
        Button itButton = new Button("Buy");
        itButton.setPrefWidth(80);
        itConcern.setVisible(false);
        itButton.setVisible(false);
        StackPane.setAlignment(itConcern, Pos.TOP_LEFT);
        StackPane.setMargin(itConcern, new Insets(145.0, 0.0, 0.0, 90.0));
        StackPane.setAlignment(itButton, Pos.TOP_LEFT);
        StackPane.setMargin(itButton, new Insets(140.0, 0.0, 0.0, 0.0));
        /*ComboBox with all user's concerns*/
        ObservableList<String> concerns = FXCollections.observableArrayList();
        ComboBox<String> concernComboBox = new ComboBox<String>(concerns);
        concernComboBox.setPrefWidth(110);
        StackPane.setAlignment(concernComboBox, Pos.TOP_LEFT);
        StackPane.setMargin(concernComboBox, new Insets(180.0, 0.0, 0.0, 0.0));
        concernComboBox.setVisible(false);

        /*Buttons events*/
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
            concernComboBox.setVisible(true);
        });
        agricultureButton.setOnAction(event ->{
            buttonClick(mainCompany, moneyLabel, valueLabel, concerns, "Agriculture", 200_000, (byte) 1);
        });
        energyButton.setOnAction(event ->{
            buttonClick(mainCompany, moneyLabel, valueLabel, concerns, "Energy", 300_000, (byte) 2);
        });
        itButton.setOnAction(event->{
            buttonClick(mainCompany, moneyLabel, valueLabel, concerns, "IT", 400_000, (byte) 3);
        });

        /*Scene activating*/
        root.getChildren().addAll(startButton, nameLabel, moneyLabel, valueLabel, agricultureConcern, agricultureButton,
                energyConcern, energyButton, itConcern, itButton, concernComboBox);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Company");
        stage.setWidth(1000);
        stage.setHeight(500);
        stage.show();
    }
}