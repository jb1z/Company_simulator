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
import java.util.Objects;
import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application{
    static ThreadedTimer mainTimer;
    public static void main(String[] args) {
        launch(args);
    }
    public void labelRefresh(Label label, int valueToRefresh){
        label.setText(String.valueOf(valueToRefresh));
    }
    public void confirmButtonClick(Company company, Label moneyLabel, Label valueLabel, ObservableList<String> concerns,
                              String concernName, float moneyChange, byte concernTypeID){
        company.moneyChange(-moneyChange);
        moneyLabel.setText("Company's money: " + company.getMoney() + "$");
        valueLabel.setText("Company's value: " + company.getValue() + "$");
        company.arrConcern = company.arrConcernAdding(company.arrConcern, new Concern(concernName, concernTypeID, moneyChange));
        company.valueRefresh();
        concerns.add(concernName);
    }
    public void buyingButtonClick(AtomicReference<Byte> concernTypeID, byte ID, AtomicReference<Float> moneyChange,
                                  float change, Button agricultureButton, Button energyButton, Button itButton,
                                  Button confirmButton, TextField nameInput){
        concernTypeID.set(ID);
        moneyChange.set(change);
        agricultureButton.setDisable(true);
        energyButton.setDisable(true);
        itButton.setDisable(true);
        confirmButton.setDisable(false);
        nameInput.setEditable(true);
    }

    public void start(Stage stage) {
        Company mainCompany = new Company();
        /*Launching thread with a timer*/
        mainTimer = new ThreadedTimer(mainCompany);
        /*timerLabel*/
        mainTimer.timerLabel.setVisible(false);
        StackPane.setAlignment(mainTimer.timerLabel, Pos.TOP_RIGHT);
        /*Starting labels*/
        StackPane root = new StackPane();
        root.setStyle("-fx-background-radius: 6;" +
               "-fx-background-color: rgb(255, 160, 122), rgb(240, 255, 240);" +
               "-fx-background-insets: 0, 0 1 1 0;");
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
        StackPane.setAlignment(agricultureConcern, Pos.TOP_RIGHT);
        StackPane.setMargin(agricultureConcern, new Insets(65.0, 95.0, 0.0, 0.0));
        StackPane.setAlignment(agricultureButton, Pos.TOP_RIGHT);
        StackPane.setMargin(agricultureButton, new Insets(60.0, 5.0, 0.0, 0.0));
        /*Label&Button for buying energy*/
        Label energyConcern = new Label("Energy cost is 300k$");
        Button energyButton = new Button("Buy");
        energyButton.setPrefWidth(80);
        energyConcern.setVisible(false);
        energyButton.setVisible(false);
        StackPane.setAlignment(energyConcern, Pos.TOP_RIGHT);
        StackPane.setMargin(energyConcern, new Insets(105.0, 95.0, 0.0, 0.0));
        StackPane.setAlignment(energyButton, Pos.TOP_RIGHT);
        StackPane.setMargin(energyButton, new Insets(100.0, 5.0, 0.0, 0.0));
        /*Label&Button for buying it*/
        Label itConcern = new Label("IT cost is 400k$");
        Button itButton = new Button("Buy");
        itButton.setPrefWidth(80);
        itConcern.setVisible(false);
        itButton.setVisible(false);
        StackPane.setAlignment(itConcern, Pos.TOP_RIGHT);
        StackPane.setMargin(itConcern, new Insets(145.0, 95.0, 0.0, 0.0));
        StackPane.setAlignment(itButton, Pos.TOP_RIGHT);
        StackPane.setMargin(itButton, new Insets(140.0, 5.0, 0.0, 0.0));
        /*Label&TextField&Button*/
        /*Label*/
        Label confirmLabel = new Label("Confirm buying of concern by entering it's name:");
        confirmLabel.setVisible(false);
        StackPane.setAlignment(confirmLabel, Pos.TOP_RIGHT);
        StackPane.setMargin(confirmLabel, new Insets(180.0, 5.0, 0.0, 0.0));
        /*TextField*/
        TextField nameInput = new TextField();
        nameInput.setMaxWidth(80);
        nameInput.setVisible(false);
        nameInput.setEditable(false);
        StackPane.setAlignment(nameInput, Pos.TOP_RIGHT);
        StackPane.setMargin(nameInput, new Insets(220.0, 95.0, 0.0, 0.0));
        /*Button*/
        Button confirmButton = new Button("Confirm");
        confirmButton.setVisible(false);
        confirmButton.setDisable(true);
        confirmButton.setPrefWidth(80);
        StackPane.setAlignment(confirmButton, Pos.TOP_RIGHT);
        StackPane.setMargin(confirmButton, new Insets(220.0, 5.0, 0.0, 0.0));
        /*ComboBox&Label with all user's concerns*/
        ObservableList<String> concerns = FXCollections.observableArrayList();
        ComboBox<String> concernComboBox = new ComboBox<>(concerns);
        concernComboBox.setPrefWidth(110);
        Label countConcerns = new Label(String.valueOf(mainCompany.getCount()));
        countConcerns.setVisible(false);
        StackPane.setAlignment(countConcerns, Pos.TOP_RIGHT);
        StackPane.setMargin(countConcerns, new Insets(265.0, 45.0, 0.0, 0.0));
        StackPane.setAlignment(concernComboBox, Pos.TOP_RIGHT);
        StackPane.setMargin(concernComboBox, new Insets(260.0, 65.0, 0.0, 0.0));
        concernComboBox.setVisible(false);
        /*Information block*/
        Label infoLabel = new Label("Information about concern:");
        infoLabel.setVisible(false);
        StackPane.setAlignment(infoLabel, Pos.TOP_RIGHT);
        StackPane.setMargin(infoLabel, new Insets(300.0, 5.0, 0.0, 0.0));
        Label infoLabelName = new Label();
        infoLabelName.setVisible(false);
        StackPane.setAlignment(infoLabelName, Pos.TOP_RIGHT);
        StackPane.setMargin(infoLabelName, new Insets(320.0, 5.0, 0.0, 0.0));
        Label infoLabelValue = new Label();
        infoLabelValue.setVisible(false);
        StackPane.setAlignment(infoLabelValue, Pos.TOP_RIGHT);
        StackPane.setMargin(infoLabelValue, new Insets(340.0, 5.0, 0.0, 0.0));
        Label infoLabelType = new Label();
        infoLabelType.setVisible(false);
        StackPane.setAlignment(infoLabelType, Pos.TOP_RIGHT);
        StackPane.setMargin(infoLabelType, new Insets(360.0, 5.0, 0.0, 0.0));
        /*Production labels*/
        Label[] productionLabels = new Label[9];
        int indType = 0;
        int indAmount = 0;
        int indValue = 0;
        for(int i = 0; i < productionLabels.length; i++){
            productionLabels[i] = new Label();
            if(i % 3 == 0){
                String strType = "";
                if(mainCompany.arrCompanyProduction[indType].getType() == 1){
                    strType = "Agriculture";
                }
                else if (mainCompany.arrCompanyProduction[indType].getType() == 2){
                    strType = "Energy";
                }
                else if (mainCompany.arrCompanyProduction[indType].getType() == 3){
                    strType = "IT";
                }
                productionLabels[i] = new Label("Type: " + strType);
                indType++;
            }
            if((i - 1) % 3 == 0){
                productionLabels[i] = new Label("Amount: " + mainCompany.arrCompanyProduction[indAmount].getAmount());
                indAmount++;
            }
            if((i - 2) % 3 == 0){
                productionLabels[i] = new Label("Unit's value: " + mainCompany.arrCompanyProduction[indValue].getValue());
                indValue++;
            }
            productionLabels[i].setVisible(false);
            StackPane.setAlignment(productionLabels[i], Pos.TOP_LEFT);
            StackPane.setMargin(productionLabels[i], new Insets(65.0 + 30 * i, 0.0, 0.0, 0.0));
        }

        /*Buttons events*/
        AtomicReference<Byte> concernTypeID = new AtomicReference<>((byte) 0);
        AtomicReference<Float> moneyChange = new AtomicReference<>(0f);
        startButton.setOnAction(event -> {
            mainTimer.start();
            nameLabel.setText("Company's name: " + mainCompany.getName());
            moneyLabel.setText("Company's money: " + mainCompany.getMoney() + "$");
            valueLabel.setText("Company's value: " + mainCompany.getValue() + "$");
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
            infoLabel.setVisible(true);
            infoLabelName.setVisible(true);
            infoLabelValue.setVisible(true);
            infoLabelType.setVisible(true);
            countConcerns.setVisible(true);
            mainTimer.timerLabel.setVisible(true);
            for(int i = 0; i < productionLabels.length; i++){
                productionLabels[i].setVisible(true);
            }
        });
        agricultureButton.setOnAction(event -> buyingButtonClick(concernTypeID, (byte)1, moneyChange,200_000f,agricultureButton, energyButton,
                itButton, confirmButton, nameInput));
        energyButton.setOnAction(event -> buyingButtonClick(concernTypeID, (byte)2, moneyChange,300_000f,agricultureButton, energyButton,
                itButton, confirmButton, nameInput));
        itButton.setOnAction(event-> buyingButtonClick(concernTypeID, (byte)3, moneyChange,400_000f,agricultureButton, energyButton,
                itButton, confirmButton, nameInput));
        confirmButton.setOnAction(event -> {
            agricultureButton.setDisable(false);
            energyButton.setDisable(false);
            itButton.setDisable(false);
            confirmButton.setDisable(true);
            confirmButtonClick(mainCompany, moneyLabel, valueLabel, concerns,
                    nameInput.getText(), moneyChange.get(), concernTypeID.get());
            confirmButton.setDisable(true);
            nameInput.setEditable(false);
            nameInput.clear();
            labelRefresh(countConcerns, mainCompany.getCount());
        });
        /*ComboBox event*/
        concernComboBox.setOnAction(event ->{
            String tempString = concernComboBox.getValue();
            for(int i = 0; i < mainCompany.arrConcern.length;i++) {
                if(Objects.equals(tempString, mainCompany.arrConcern[i].getName())) {
                    infoLabelName.setText(mainCompany.arrConcern[i].getName());
                    infoLabelValue.setText(String.valueOf(mainCompany.arrConcern[i].getValue()));
                    if(mainCompany.arrConcern[i].getType() == (byte) 1){
                        infoLabelType.setText("Agriculture");
                    }
                    else if(mainCompany.arrConcern[i].getType() == (byte) 2){
                        infoLabelType.setText("Energy");
                    }
                    else if(mainCompany.arrConcern[i].getType() == (byte) 3){
                        infoLabelType.setText("IT");
                    }
                }
            }
        });

        /*Scene activating*/
        root.getChildren().addAll(startButton, nameLabel, moneyLabel, valueLabel, agricultureConcern, agricultureButton,
                energyConcern, energyButton, itConcern, itButton, concernComboBox, nameInput, confirmLabel, confirmButton,
                infoLabel, infoLabelName, infoLabelValue, infoLabelType, countConcerns, mainTimer.timerLabel);
        for(int i = 0; i < productionLabels.length; i++){
            root.getChildren().add(productionLabels[i]);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Company");
        stage.setWidth(1000);
        stage.setHeight(750);
        stage.show();
    }
}