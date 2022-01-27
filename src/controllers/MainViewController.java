package controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import main.Main;

public class MainViewController {
    private Stage mainStage;
    private Main main;

    @FXML
    public void initialize() {}

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
