package gpatable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Preethi
 */
public class LoadCSVController implements Initializable {
 final FileChooser fileChooser = new FileChooser();
 private DbConnect dc;
     @FXML
    private AnchorPane root;
      @FXML
    private AnchorPane smallpane;

    @FXML
    private JFXTextField onetext;

    @FXML
    private JFXTextField twotext;

    @FXML
    private JFXTextField threetext;

    @FXML
    private JFXTextField fourtext;

    @FXML
    private JFXTextField fivetext;

    @FXML
    private JFXTextField sixtext;

    @FXML
    private JFXTextField seventext;

    @FXML
    private JFXTextField eighttext;

    @FXML
    private JFXButton one;

    @FXML
    private JFXButton two;

    @FXML
    private JFXButton three;

    @FXML
    private JFXButton four;

    @FXML
    private JFXButton five;

    @FXML
    private JFXButton six;

    @FXML
    private JFXButton seven;

    @FXML
    private JFXButton eight;

    @FXML
    private JFXButton gpa;
Connection conn = (Connection) dc.Connect();
    @FXML
    void loadeight(ActionEvent event) {
        try{
File file = fileChooser.showOpenDialog(root.getScene().getWindow());
String onepath = file.getAbsolutePath();

String query = "LOAD DATA LOCAL INFILE '"+onepath+"' INTO TABLE decompose.semeightdata FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\n';";
conn.createStatement().executeUpdate(query);
        }
        catch(Exception e){
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Exception");
        alert.setContentText(e.toString());
        }
    }

    @FXML
    void loadfive(ActionEvent event) {
try{
File file = fileChooser.showOpenDialog(root.getScene().getWindow());
String onepath = file.getAbsolutePath();
String query = "LOAD DATA LOCAL INFILE '"+onepath+"' INTO TABLE decompose.semfivedata FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\n';";
conn.createStatement().executeUpdate(query);
        }
        catch(Exception e){
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Exception");
alert.setContentText(e.toString());
        }
    }

    @FXML
    void loadfour(ActionEvent event) {
try{
File file = fileChooser.showOpenDialog(root.getScene().getWindow());
String onepath = file.getAbsolutePath();

String query = "LOAD DATA LOCAL INFILE '"+onepath+"' INTO TABLE decompose.semfourdata FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\n';";
conn.createStatement().executeUpdate(query);
        }
        catch(Exception e){
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Exception");
alert.setContentText(e.toString());
        }
    }

    @FXML
    void loadone(ActionEvent event) {
        try{
File file = fileChooser.showOpenDialog(root.getScene().getWindow());
String onepath = file.getAbsolutePath();

String query = "LOAD DATA LOCAL INFILE '"+onepath+"' INTO TABLE decompose.semonedata FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\n';";
conn.createStatement().executeUpdate(query);
        }
        catch(Exception e){
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Exception");
alert.setContentText(e.toString());
        }
        
    }

    @FXML
    void loadseven(ActionEvent event) {
try{
File file = fileChooser.showOpenDialog(root.getScene().getWindow());
String onepath = file.getAbsolutePath();

String query = "LOAD DATA LOCAL INFILE '"+onepath+"' INTO TABLE decompose.semsevendata FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\n';";
conn.createStatement().executeUpdate(query);
        }
        catch(Exception e){
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Exception");
alert.setContentText(e.toString());
        }
    }

    @FXML
    void loadsix(ActionEvent event) {
try{
File file = fileChooser.showOpenDialog(root.getScene().getWindow());
String onepath = file.getAbsolutePath();

String query = "LOAD DATA LOCAL INFILE '"+onepath+"' INTO TABLE decompose.semsixdata FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\n';";
conn.createStatement().executeUpdate(query);
        }
        catch(Exception e){
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Exception");
alert.setContentText(e.toString());
        }
    }

    @FXML
    void loadthree(ActionEvent event) {
try{
File file = fileChooser.showOpenDialog(root.getScene().getWindow());
String onepath = file.getAbsolutePath();
String query = "LOAD DATA LOCAL INFILE '"+onepath+"' INTO TABLE decompose.semthreedata FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\n';";
conn.createStatement().executeUpdate(query);

        }
        catch(Exception e){
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Exception");
alert.setContentText(e.toString());
        }
    }

    @FXML
    void loadtwo(ActionEvent event) {
try{
File file = fileChooser.showOpenDialog(root.getScene().getWindow());
String onepath = file.getAbsolutePath();
String query = "LOAD DATA LOCAL INFILE '"+onepath+"' INTO TABLE decompose.semtwodata FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\n';";
conn.createStatement().executeUpdate(query);
        }
        catch(Exception e){
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Exception");
alert.setContentText(e.toString());
        }
    }

    @FXML
    void opengpa(ActionEvent event) {
        try{
                           Node node=(Node) event.getSource();
  Stage stage=(Stage) node.getScene().getWindow();
  Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));/* Exception */
  Scene scene = new Scene(root);
  stage.setScene(scene);
  stage.show();}
    catch(Exception e){
    System.out.print(e);
    }

    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnect();
    }    
    
}
