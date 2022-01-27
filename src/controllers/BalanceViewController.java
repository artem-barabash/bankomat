package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.Main;
import model.Person;

import java.io.IOException;

public class BalanceViewController {
    private Main main;
    private Stage mainStage;

    @FXML
    public Label balanceOfPerson;

    public void showData(Person person){
        balanceOfPerson.setText(person.balance + " грн.");
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    public void backToMenu() throws IOException {
        main.switchViewMenuAccount();
    }
}
