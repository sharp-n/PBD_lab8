package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.*;

public class Controller {
   Connection con = Main.getCon();
    @FXML
    private TextField tableTextField, item1TextField, item2TextField, conditionTextField,conditionItemTextField;

    @FXML
    private TextArea col1;

    @FXML
    private TextArea col2;


    @FXML
    void delete(ActionEvent event) {
        if (!tableTextField.getText().equals("")&&!conditionItemTextField.getText().equals("")&&!conditionTextField.getText().equals("")) {
            if (tableTextField.getText().equals("Names")||tableTextField.getText().equals("Races")) {
                try {
                    String tableName = tableTextField.getText();
                    String SQL = "DELETE FROM " + tableName + " WHERE " + conditionTextField.getText() +
                            "='" + conditionItemTextField.getText() + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(SQL);

                } catch (SQLException e) {
                    errorPrint(e.toString());
                }
            }
        } else errorPrint("Fill empty fields!");
    }

    @FXML
    void readNames() {
        try {
            String SQL = "SELECT Name, Race FROM Names";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            col1.setText("");
            col2.setText("");
            int counter = 0;
            String fill1= "----------------------------";
            String fill2= "------------------------------------------------";
            while (rs.next()) {
                counter++;
                col1.appendText(counter+"\t|  " + rs.getString("Name")+"\n" + fill1+"\n");
                col2.appendText(counter+"\t|  " + rs.getString("Race")+"\n" + fill2+"\n");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    void readRaces() {
        try {
            String SQL = "SELECT Race, Description FROM Races";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            col1.setText("");
            col2.setText("");
            int counter = 0;
            String fill1 = "----------------------------";
            String fill = "------------------------------------------------";
            while (rs.next()) {
                counter++;
                col1.appendText(counter+"\t|  " + rs.getString("Race")+"\n" + fill1+"\n");
                col2.appendText(counter+"\t|  " + rs.getString("Description")+"\n" + fill+"\n");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    void write(ActionEvent event) {
        if (!tableTextField.getText().equals("")&&!item2TextField.getText().equals("")&&!item1TextField.getText().equals("")) {
            if (tableTextField.getText().equals("Names")||tableTextField.getText().equals("Races")) {
                try {
                    String tableName = tableTextField.getText();
                    String SQL = "INSERT INTO " + tableName + "\n" +
                            "VALUES('" + item1TextField.getText() + "','" + item2TextField.getText() + "')";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(SQL);
                } catch (SQLException e) {
                    errorPrint(e.toString());
                }
            }
        }else errorPrint("Fill empty fields!");
    }

    public static Scene getSceneWin(String name) {
        Parent root = loadFXMLWin(name);
        assert root != null;
        return new Scene(root);
    }
    private static Parent loadFXMLWin(String name) {
        try {
            return FXMLLoader.load(Controller.class.getResource(name));
        }
        catch (IOException e) {
            e.printStackTrace();
            return new Pane();
        }
    }

    public static void errorPrint(String error){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.setContentText(error);
        alert.showAndWait();
    }
}
