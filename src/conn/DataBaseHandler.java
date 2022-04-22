package conn;

import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Main;
import model.OperationItem;
import model.Person;
import security.AESUtils;
import security.CryptoControl;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class DataBaseHandler {
    private Main main;

    public static Connection getConnection(){
        try {
            String driver = "com.postgresql.jdbc.Driver";
            String url = "jdbc:postgresql://localhost:5432/mydb";
            String user = "postgres";
            String password = "1234";
            Connection con = DriverManager.getConnection(url, user, password);

            return con;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static Person checkPersonForCard(String codeNumber) throws SQLException {
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE number = '" + codeNumber + "'");
        Person person= null;
        while(rs.next()){
            ObservableList<OperationItem> incomeList = FXCollections.observableArrayList();
            ObservableList<OperationItem> costsList = FXCollections.observableArrayList();
            LocalDate date = rs.getObject("birthday", LocalDate.class);
            person= new Person(rs.getString("number"), rs.getString("phone"), rs.getString("password")
                    , rs.getString("surname"), rs.getString("name"), rs.getString("fathername"), date, rs.getFloat("balance"), incomeList , costsList);

        }
        return person;
    }

    public static boolean checkPinCode(String numberCode, String pincode) throws SQLException {
        Statement stmt = getConnection().createStatement();

        String encryptPinCode = AESUtils.encrypt(pincode, CryptoControl.secretKeyForPinCode);
        ResultSet rs = stmt.executeQuery("SELECT * FROM card WHERE number = '" + numberCode + "' AND pincode = '" + encryptPinCode + "'");

        while (rs.next()) {
            String number = rs.getString("number");
            String pin = rs.getString("pincode");

            return true;
        }
        return  false;
    }

    public static void saveOperationOfService(Person person, String nameService, float summ){
        try {
            String sql = "INSERT INTO operations VALUES (?, ?, ?, ?)";



            Thread.sleep(1000);
            transakciaRemoveBalance(person.getNumberCode(), summ);

            Thread.sleep(1000);
            java.util.Date dt = new java.util.Date();
            Timestamp ts = new Timestamp(dt.getTime());

            PreparedStatement pstmt = getConnection().prepareStatement(sql);
            pstmt.setString(1, person.getNumberCode());
            pstmt.setString(2, nameService);
            pstmt.setFloat(3, summ);
            pstmt.setTimestamp(4, ts);

            pstmt.executeUpdate();
        }catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            return;
        }
    }

    private static void transakciaRemoveBalance(String numberCode, float summ) throws SQLException {
        Statement stmt = getConnection().createStatement();
        String sql = "UPDATE users SET balance = balance -" + summ  + "  WHERE number = '" + numberCode + "'";
        stmt.execute(sql);
    }
}
