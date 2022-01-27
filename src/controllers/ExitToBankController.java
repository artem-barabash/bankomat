package controllers;

import conn.DataBaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.Main;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.SQLException;

public class ExitToBankController {

    private Main main;
    private Stage mainStage;

    @FXML
    public Label numberCodeLabel;
    String code = null;


    public void setMain(Main main) {
        this.main = main;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    public void changeFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(mainStage);

        if(file != null){
            try {
                code = readFileCard(file);
                numberCodeLabel.setText("");
                numberCodeLabel.setText(code);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            JOptionPane.showMessageDialog(null, "Оберіть будь ласка, файл-картку!");
        }
    }

    private String readFileCard(File file) throws IOException {
        String nameFile = String.valueOf(file);

        FileReader x = new FileReader(nameFile);
        BufferedReader varRead = new BufferedReader(x);

        String codeOfCard = varRead.readLine();

        return codeOfCard;
    }

    @FXML
    public void clickClose() {
        mainStage.close();
    }

    @FXML
    public void checkCardAndPerson() throws SQLException, IOException {
        main.person = DataBaseHandler.checkPersonForCard(code);
        if(code != null){
            if(main.person == null){
                JOptionPane.showMessageDialog(null, "Картка не дійсна!");
            }else {
                //System.out.println(main.person);
                main.switchViewPinCode();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Спочатку вставте, картку!");
        }
    }
}
