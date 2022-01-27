package controllers;

import conn.DataBaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import main.Main;
import model.Person;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class PinCodeController {

    private Main main;
    private Stage mainStage;

    DataBaseHandler db;

    @FXML
    public PasswordField pincodeTextField;

    Person currentPerson;

    public Person takeDateMethod(Person person){
        if (person == null){
            throw new IllegalArgumentException("Num не должен быть null");
        }
        return  currentPerson = person;
    }


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
    public  void checkPinCodeOfPerson() throws SQLException, IOException {
        String code = main.person.numberCode;
        if(DataBaseHandler.checkPinCode(code, pincodeTextField.getText())){
            main.switchViewMenuAccount();
        }else{
            JOptionPane.showMessageDialog(null, "Пін-код не вірний!");
        }
    }
}
