package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

public class MenuAccountController {
    private Main main;
    private Stage mainStage;


    public void setMain(Main main) {
        this.main = main;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    public void clickClose() {
        mainStage.close();
    }

    @FXML
    public void showBalanceOfPerson() throws IOException {
        main.switchViewBalanceOfPerson();
    }

    @FXML
    public void clickCashDrawerPage() throws IOException {
        main.switchViewCashDrawer();
    }
}
