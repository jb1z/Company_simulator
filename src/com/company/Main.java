package com.company;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.concurrent.atomic.AtomicReference;

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
        /*Label&TextField&Button*/
        /*Label*/
        Label confirmLabel = new Label("Confirm buying of concern by entering it's name:");
        confirmLabel.setVisible(false);
        StackPane.setAlignment(confirmLabel, Pos.TOP_LEFT);
        StackPane.setMargin(confirmLabel, new Insets(180.0, 0.0, 0.0, 00.0));
        /*TextField*/
        TextField nameInput = new TextField();
        nameInput.setMaxWidth(80);
        nameInput.setVisible(false);
        nameInput.setEditable(false);
        StackPane.setAlignment(nameInput, Pos.TOP_LEFT);
        StackPane.setMargin(nameInput, new Insets(220.0, 0.0, 0.0, 00.0));
        /*Button*/
        Button confirmButton = new Button("Confirm");
        confirmButton.setVisible(false);
        confirmButton.setDisable(true);
        confirmButton.setPrefWidth(80);
        StackPane.setAlignment(confirmButton, Pos.TOP_LEFT);
        StackPane.setMargin(confirmButton, new Insets(220.0, 0.0, 0.0, 90.0));
        /*ComboBox with all user's concerns*/
        ObservableList<String> concerns = FXCollections.observableArrayList();
        ComboBox<String> concernComboBox = new ComboBox<>(concerns);
        concernComboBox.setPrefWidth(110);
        StackPane.setAlignment(concernComboBox, Pos.TOP_LEFT);
        StackPane.setMargin(concernComboBox, new Insets(260.0, 0.0, 0.0, 0.0));
        concernComboBox.setVisible(false);

        /*Buttons events*/
        AtomicReference<Byte> concernTypeID = new AtomicReference<>((byte) 0);
        AtomicReference<Float> moneyChange = new AtomicReference<Float>(0f);
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
            nameInput.setVisible(true);
            confirmButton.setVisible(true);
            confirmLabel.setVisible(true);
        });
        agricultureButton.setOnAction(event -> {
            concernTypeID.set((byte) 1);
            moneyChange.set(200_000f);
            agricultureButton.setDisable(true);
            energyButton.setDisable(true);
            itButton.setDisable(true);
            nameInput.setEditable(true);
            confirmButton.setDisable(false);
        });
        energyButton.setOnAction(event -> {
            concernTypeID.set((byte) 2);
            moneyChange.set(300_000f);
            agricultureButton.setDisable(true);
            energyButton.setDisable(true);
            itButton.setDisable(true);
            nameInput.setEditable(true);
            confirmButton.setDisable(false);
        });
        itButton.setOnAction(event-> {
            concernTypeID.set((byte) 3);
            moneyChange.set(400_000f);
            agricultureButton.setDisable(true);
            energyButton.setDisable(true);
            itButton.setDisable(true);
            nameInput.setEditable(true);
            confirmButton.setDisable(false);
        });
        confirmButton.setOnAction(event -> {
            agricultureButton.setDisable(false);
            energyButton.setDisable(false);
            itButton.setDisable(false);
            confirmButton.setDisable(true);
            buttonClick(mainCompany, moneyLabel, valueLabel, concerns,
                    nameInput.getText(), moneyChange.get(), concernTypeID.get());
            confirmButton.setDisable(true);
            nameInput.setEditable(false);
            nameInput.clear();
        });
        /*ComboBox event*/
        concernComboBox.setOnAction(event ->{
            String tempString = concernComboBox.getValue();
        });

        /*Scene activating*/
        root.getChildren().addAll(startButton, nameLabel, moneyLabel, valueLabel, agricultureConcern, agricultureButton,
                energyConcern, energyButton, itConcern, itButton, concernComboBox, nameInput, confirmLabel, confirmButton);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Company");
        stage.setWidth(1000);
        stage.setHeight(500);
        stage.show();
    }
}