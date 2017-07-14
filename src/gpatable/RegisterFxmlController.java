/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpatable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Preethi
 */

public class RegisterFxmlController implements Initializable {

    /**
     * Initializes the controller class.
     */
    

    @FXML
    private AnchorPane pon;

@FXML
    private ImageView img;

    @FXML
    private AnchorPane pane2;

    @FXML
    private JFXButton submit;

    @FXML
    private JFXTextField usname;
    @FXML
    private JFXTextField first;

    @FXML
    private JFXComboBox<String> desig;

    @FXML
    private JFXTextField initial;
    @FXML
    private JFXTextField psword;
   private DbConnect dc;
    @FXML
    void onsubmit(ActionEvent event) throws SQLException {
           
      String uname2=usname.getText();
      String pass2=psword.getText();
 
    try{
        Connection conn = (Connection) dc.Connect();
               
               PreparedStatement st;
              
                   st = (PreparedStatement) conn.prepareStatement("insert into login (name,password)values(?,?)");
                   st.setString(1,uname2);
                   st.setString(2,pass2);
                   st.executeUpdate();
                   Node node=(Node) event.getSource();
  Stage stage=(Stage) node.getScene().getWindow();
  Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));/* Exception */
  Scene scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
                   }
    catch(Exception e){
    System.out.println(""+e);}
               
    }

        
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         dc = new DbConnect();
    }    
    
}
