package main;

import conn.DataBaseHandler;
import controllers.*;
import instruments.LabelInfo;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Person;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main extends Application {
    private Stage mainStage;
    private BorderPane mainPane;

    public Person person;


    @FXML
    public void initialize() {}

    @Override
    public void start(Stage stage) throws Exception{
        mainStage = stage;
        mainStage.setTitle(" ");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/main_view.fxml"));
        mainPane = loader.load();
        Scene scene = new Scene(mainPane);
        mainStage.setScene(scene);

        MainViewController controller = loader.getController();
        controller.setMainStage(mainStage);
        controller.setMain(this);

        switchViewExit();

        mainStage.show();
    }

    public void switchViewExit() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/exit_to_bank.fxml"));
        AnchorPane pane = loader.load();
        mainPane.setCenter(pane);

        ExitToBankController controller = loader.getController();
        controller.setMainStage(mainStage);
        controller.setMain(this);
    }

    public void switchViewPinCode() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/pincode_view.fxml"));
        AnchorPane pane = loader.load();
        mainPane.setCenter(pane);

        PinCodeController controller = loader.getController();
        controller.setMainStage(mainStage);
        controller.setMain(this);
    }

    public void switchViewMenuAccount() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/menu_account.fxml"));
        AnchorPane pane = loader.load();
        mainPane.setCenter(pane);

        MenuAccountController controller = loader.getController();
        controller.setMainStage(mainStage);
        controller.setMain(this);
    }

    public void switchViewBalanceOfPerson() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/balance_view.fxml"));
        AnchorPane pane = loader.load();
        mainPane.setCenter(pane);

        BalanceViewController controller = loader.getController();
        controller.setMainStage(mainStage);
        controller.setMain(this);
        controller.showData(person);
    }

    public void switchViewCashDrawer() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/сash_drawer_view.fxml"));
        AnchorPane pane = loader.load();
        mainPane.setCenter(pane);

        CashDrawerController controller = loader.getController();
        controller.setMainStage(mainStage);
        controller.setMain(this);
    }

    public void switchViewThankYou() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/thank_you_view.fxml"));
        AnchorPane pane = loader.load();
        mainPane.setCenter(pane);

        ThankYouViewController controller = loader.getController();
        controller.setMainStage(mainStage);
        controller.setMain(this);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
