package controllers;

import com.itextpdf.text.DocumentException;
import conn.DataBaseHandler;
import instruments.CreatePDFfiles;
import instruments.LabelInfo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CashDrawerController {
    private Main main;
    private Stage mainStage;

    @FXML
    public TextField sumToCashTextField;


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

    @FXML
    public void clickReceiveCash() throws IOException, DocumentException {
        int summ = Integer.parseInt(sumToCashTextField.getText());

        if(sumToCashTextField.getText() == null || sumToCashTextField.getText() == ""){
            JOptionPane.showMessageDialog(null, "Введіть суму!");
        }else{
            if(summ <= 0){
                JOptionPane.showMessageDialog(null, "Сума не може бути 0 або менше!");
            }else{
                    if(main.person.balance < summ){
                        JOptionPane.showMessageDialog(null, "Не достатньо коштів!");
                    }else{
                        if(summ % 50 != 0){
                            JOptionPane.showMessageDialog(null, "Сума повинна бути кратна 50!");
                        } else{
                            DataBaseHandler.saveOperationOfService(main.person, "bankomat№1", summ);
                            receiveBillsForUser(findSumList(summ), summ);
                            LabelInfo. TextDisplayPopup("Мій банк", "<html><font size=11 color=blue>Зачекайте..",
                                    6, 700, 300, JOptionPane.INFORMATION_MESSAGE);
                            main.switchViewThankYou();
                        }
                    }
            }
        }
    }

    private void receiveBillsForUser(List<Integer> sumList, int summ) throws IOException, DocumentException {

        String nameFolder = getDateInTransaction();
        String pathFolder = "C:\\Users\\Artem\\OneDrive\\Робочий стіл\\trancation\\" + nameFolder;
        File file = new File(pathFolder);
        file.mkdir();

        for(Integer num : sumList){
            File str1 = new File("F:\\bankomat\\money-example\\" + num + ".png");
            File str2 = new File("C:\\Users\\Artem\\OneDrive\\Робочий стіл\\trancation\\" + nameFolder + "\\" + num + generatePwd(4) + ".png");

            try {
                copyFile(str1, str2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        CreatePDFfiles.createDocumentOfTransakcia(main.person, pathFolder, summ);

    }


    private static void copyFile(File sourceFile, File destFile)
            throws IOException {
        if (!sourceFile.exists()) {
            return;
        }
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }

    }

    private static List<Integer> findSumList(int sumMoney) {
        List<Integer> inputData = new LinkedList<>();
        inputData.add(50);
        inputData.add(100);
        inputData.add(200);
        inputData.add(500);
        inputData.add(1000);

        List<Integer> outData = new LinkedList<>();


        int sum = 0;

        while (sum != sumMoney){
            final Random random = new Random();
            outData.add(inputData.get(random.nextInt(inputData.size())));

            sum = 0;
            for (int cell : outData){
                sum += cell;
            }

            if (sum > sumMoney) {
                outData.remove(outData.size() - 1);
            }
        }
        //System.out.println(Arrays.toString(new List[]{outData}));
        return outData;
    }

    private static String generatePwd(int size) {
        String charsCaps = "abcdefghijklmnopqrstuvwxyz";
        String nums = "0123456789";
        String passSymbols = charsCaps + nums;
        Random rnd = new Random();

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(passSymbols.charAt(rnd.nextInt(passSymbols.length())));
        }
        return sb.toString();
    }

    public static String getDateInTransaction() {
        SimpleDateFormat dnt = new SimpleDateFormat("HH-mm-ss-dd-MM-yyyy");
        Date date = new Date();

        return dnt.format(date);
    }
}
