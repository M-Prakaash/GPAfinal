package gpatable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.JFXFillTransition;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import gpatable.DbConnect;
import gpatable.sqldetailsfirst;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.*;
 import javafx.scene.image.Image;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.KeyValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.management.Notification;
import org.controlsfx.control.NotificationPane;

public class LoginPageController implements Initializable {
;
    @FXML
    private AnchorPane logpane;
    @FXML
    private AnchorPane bgpane;
    @FXML
    private ImageView image;
    private DbConnect dc;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try{ 
           
           
        FadeTransition fadeout = new FadeTransition(Duration.seconds(1), bgpane);
            fadeout.setFromValue(1);
            fadeout.setToValue(0);
            fadeout.play();

            fadeout.setOnFinished(event -> {

                bgpane.setStyle(" -fx-background-image: url(\"/src/grd.jpg\");");

                FadeTransition fadein = new FadeTransition(Duration.seconds(4.5), bgpane);
                fadein.setFromValue(0);
                fadein.setToValue(0.6);
                fadein.play();

                fadein.setOnFinished(e -> {

                    bgpane.setStyle(" -fx-background-image: url(\"/src/pex.jpeg\");");
                    FadeTransition fadein2 = new FadeTransition(Duration.seconds(4.5), bgpane);
                    fadein2.setFromValue(0);
                    fadein2.setToValue(1);
                    fadein2.play();

                    fadein2.setOnFinished(event2 -> {

                        bgpane.setStyle(" -fx-background-image: url(\"/src/students.jpg\");");

                        FadeTransition fadein3 = new FadeTransition(Duration.seconds(4.5), bgpane);
                        fadein3.setFromValue(1);
                        fadein3.setToValue(0);
                        fadein3.play();

                        fadein3.setOnFinished(event3 -> {
                            bgpane.setStyle(" -fx-background-image: url(\"/src/str.jpg\");");

                            FadeTransition fadein4 = new FadeTransition(Duration.seconds(4.5), bgpane);
                            fadein4.setFromValue(0);
                            fadein4.setToValue(1);
                            fadein4.play();

                        });    
                        });

                        });
                    });

           

           
           
           
           
           
           
       }
       catch(Exception e){
       
       System.out.println(e);
       }
        
        
        
      dc = new DbConnect();
                   
    }
    @FXML
    private JFXButton Signup;
    @FXML
    private NotificationPane ntfy;
   
    @FXML
    private JFXTextField uname;

    @FXML
    private JFXTextField pass;
    
    @FXML
    public JFXButton btn;
        @FXML
    private Label lbl3;
    @FXML
    private Label lable;
    @FXML
    private Label lable2;
    @FXML
    void start(ActionEvent event) throws IOException {

String uname1,pass1;


try {
            Connection conn = (Connection) dc.Connect();
            
            // Execute query and store result in a resultset
            String query = "Select * from decompose.login";
            ResultSet rs = (ResultSet) conn.createStatement().executeQuery(query);
            while (rs.next()) {
                //get string from db,whichever way
                uname1=uname.getText();
                pass1=pass.getText();
                if(uname1.isEmpty()){
                lable.setText("Required field cannot be empty");
                }
                if(pass1.isEmpty()){
                lable2.setText("Required field cannot be empty");
                }
                else {
                    if(uname1.equalsIgnoreCase(rs.getString(1)) && pass1.equalsIgnoreCase(rs.getString(2)))
               {
                     
                   Node node=(Node) event.getSource();
  Stage stage=(Stage) node.getScene().getWindow();
  Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));/* Exception */
  Scene scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
               btn.setDisable(true);
            
               }
               else
               {
                 btn.setDisable(false);    
                  lbl3.setText("Incorrect Details");
 
               }
                }
            }
            

        } 
    catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
    }
     @FXML
    void registers(ActionEvent event) throws IOException {
         Node node=(Node) event.getSource();
          Stage stage=(Stage) node.getScene().getWindow();
  Parent root = FXMLLoader.load(getClass().getResource("registerFxml.fxml"));/* Exception */
  Scene scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
        
    }

    
}
