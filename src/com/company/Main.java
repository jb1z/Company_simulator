package com.company;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.util.Objects;
import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application{
    static ThreadedTimer mainTimer;
    public static void main(String[] args) {
        launch(args);
    }
    public void confirmButtonClick(Company company, Label moneyLabel, Label valueLabel, ObservableList<String> concerns,
                              String concernName, float moneyChange, byte concernTypeID){
        boolean nameCheck = false;
        for(int i = 0; i < company.arrConcern.length; i++){
            if (Objects.equals(company.arrConcern[i].getName(), concernName)) {
                nameCheck = true;
            }
        }
        if(nameCheck){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention!");
            alert.setHeaderText(null);
            alert.setContentText("This name is already exist! Choose another one.");
            alert.showAndWait();
        } else {
            company.arrConcern = company.arrConcernAdding(company.arrConcern, new Concern(concernName, concernTypeID, moneyChange, TimeExchanger.getTime()));
            company.valueRefresh();
            concerns.add(concernName);
            company.moneyChange(-moneyChange);
            moneyLabel.setText("Company's money: " + company.getMoney() + "$");
            valueLabel.setText("Company's value: " + company.getValue() + "$");
        }
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
        Label[] productionLabels = new Label[9];
        StackPane root = new StackPane();
        /*TextField*/
        TextField nameInput = new TextField();
        /*ComboBox&Label with all user's concerns*/
        ObservableList<String> concerns = FXCollections.observableArrayList();
        ComboBox<String> concernComboBox = new ComboBox<>(concerns);
        /*Labels*/
        Label nameLabel = new Label();
        Label moneyLabel = new Label();
        Label valueLabel = new Label();
        Label agricultureConcern = new Label("Agriculture cost is 200k$");
        Label energyConcern = new Label("Energy cost is 300k$");
        Label itConcern = new Label("IT cost is 400k$");
        Label confirmLabel = new Label("Confirm buying of concern by entering it's name:");
        Label infoLabel = new Label("Information about concern:");
        Label infoLabelName = new Label();
        Label infoLabelValue = new Label();
        Label infoLabelType = new Label();
        Label countConcerns = new Label(String.valueOf(mainCompany.getCount()));
        /*Production labels*/
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
                productionLabels[i] = new Label("Amount: " + mainCompany.arrCompanyProduction[indAmount].getAmount()
                        + "/" + mainCompany.productionStorage[indAmount]);
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
        Label[] amountLabels = {productionLabels[1], productionLabels[4], productionLabels[7]};
        /*Buttons*/
        Button startButton = new Button("Start");
        Button agricultureButton = new Button("Buy");
        Button energyButton = new Button("Buy");
        Button itButton = new Button("Buy");
        Button confirmButton = new Button("Confirm");
        /*Labels visibility*/
        agricultureConcern.setVisible(false);
        energyConcern.setVisible(false);
        itConcern.setVisible(false);
        confirmLabel.setVisible(false);
        countConcerns.setVisible(false);
        infoLabel.setVisible(false);
        infoLabelName.setVisible(false);
        infoLabelValue.setVisible(false);
        infoLabelType.setVisible(false);
        /*Buttons visibility*/
        agricultureButton.setVisible(false);
        energyButton.setVisible(false);
        itButton.setVisible(false);
        confirmButton.setVisible(false);
        /*Other's objects visibility*/
        nameInput.setVisible(false);
        concernComboBox.setVisible(false);
        /*Placement*/
        StackPane.setAlignment(nameLabel, Pos.TOP_LEFT);
        StackPane.setAlignment(moneyLabel, Pos.TOP_LEFT);
        StackPane.setAlignment(valueLabel, Pos.TOP_LEFT);
        StackPane.setAlignment(agricultureConcern, Pos.TOP_RIGHT);
        StackPane.setAlignment(agricultureButton, Pos.TOP_RIGHT);
        StackPane.setAlignment(energyConcern, Pos.TOP_RIGHT);
        StackPane.setAlignment(energyButton, Pos.TOP_RIGHT);
        StackPane.setAlignment(itConcern, Pos.TOP_RIGHT);
        StackPane.setAlignment(itButton, Pos.TOP_RIGHT);
        StackPane.setAlignment(confirmLabel, Pos.TOP_RIGHT);
        StackPane.setAlignment(infoLabel, Pos.TOP_RIGHT);
        StackPane.setAlignment(infoLabelName, Pos.TOP_RIGHT);
        StackPane.setAlignment(confirmButton, Pos.TOP_RIGHT);
        StackPane.setAlignment(infoLabelValue, Pos.TOP_RIGHT);
        StackPane.setAlignment(infoLabelType, Pos.TOP_RIGHT);
        StackPane.setAlignment(countConcerns, Pos.TOP_RIGHT);
        StackPane.setAlignment(concernComboBox, Pos.TOP_RIGHT);
        StackPane.setAlignment(nameInput, Pos.TOP_RIGHT);
        StackPane.setMargin(moneyLabel, new Insets(20.0, 0.0, 0.0, 0.0));
        StackPane.setMargin(valueLabel, new Insets(40.0, 0.0, 0.0, 0.0));
        StackPane.setMargin(agricultureConcern, new Insets(65.0, 95.0, 0.0, 0.0));
        StackPane.setMargin(agricultureButton, new Insets(60.0, 5.0, 0.0, 0.0));
        StackPane.setMargin(energyConcern, new Insets(105.0, 95.0, 0.0, 0.0));
        StackPane.setMargin(energyButton, new Insets(100.0, 5.0, 0.0, 0.0));
        StackPane.setMargin(itConcern, new Insets(145.0, 95.0, 0.0, 0.0));
        StackPane.setMargin(itButton, new Insets(140.0, 5.0, 0.0, 0.0));
        StackPane.setMargin(confirmLabel, new Insets(180.0, 5.0, 0.0, 0.0));
        StackPane.setMargin(infoLabel, new Insets(300.0, 5.0, 0.0, 0.0));
        StackPane.setMargin(confirmButton, new Insets(220.0, 5.0, 0.0, 0.0));
        StackPane.setMargin(infoLabelName, new Insets(320.0, 5.0, 0.0, 0.0));
        StackPane.setMargin(infoLabelValue, new Insets(340.0, 5.0, 0.0, 0.0));
        StackPane.setMargin(infoLabelType, new Insets(360.0, 5.0, 0.0, 0.0));
        StackPane.setMargin(countConcerns, new Insets(265.0, 45.0, 0.0, 0.0));
        StackPane.setMargin(concernComboBox, new Insets(260.0, 65.0, 0.0, 0.0));
        StackPane.setMargin(nameInput, new Insets(220.0, 95.0, 0.0, 0.0));
        /*Size setting*/
        agricultureButton.setPrefWidth(80);
        energyButton.setPrefWidth(80);
        itButton.setPrefWidth(80);
        nameInput.setMaxWidth(80);
        confirmButton.setPrefWidth(80);
        concernComboBox.setPrefWidth(110);
        /*Editable&Disable*/
        nameInput.setEditable(false);
        confirmButton.setDisable(true);
        /*Launching thread with a timer with timerLabel*/
        mainTimer = new ThreadedTimer(mainCompany, amountLabels, valueLabel);
        mainTimer.timerLabel.setVisible(false);
        StackPane.setAlignment(mainTimer.timerLabel, Pos.TOP_RIGHT);
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
        Timeline abilityToBuyCheck = new Timeline(new KeyFrame(Duration.millis(100), e->{
            agricultureButton.setDisable(mainCompany.getMoney() < 200_000f || !confirmButton.isDisable());
            energyButton.setDisable(mainCompany.getMoney() < 300_000f || !confirmButton.isDisable());
            itButton.setDisable(mainCompany.getMoney() < 300_000f || !confirmButton.isDisable());
        }));
        abilityToBuyCheck.setCycleCount(Animation.INDEFINITE);
        abilityToBuyCheck.play();
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
            countConcerns.setText(String.valueOf(mainCompany.getCount()));
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
        root.setStyle("-fx-background-radius: 6;" +
                "-fx-background-color: rgb(255, 160, 122), rgb(240, 255, 240);" +
                "-fx-background-insets: 0, 0 1 1 0;");
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