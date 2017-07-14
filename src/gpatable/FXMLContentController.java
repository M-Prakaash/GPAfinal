/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpatable;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

 

public class FXMLContentController implements Initializable {
   @FXML
    private VBox content;

    @FXML
    private JFXButton btn1;

    @FXML
    private JFXButton bttwo;

    @FXML
    private JFXButton btthree;

    @FXML
    private JFXButton btfour;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
  
    }    
        @FXML
    void openSemesterThree(ActionEvent event) {

        

    }

    @FXML
    void openSemesterfour(ActionEvent event) {

    }

    @FXML
    void openSemestertwo(ActionEvent event) {

    }

    @FXML
    void openSemsesterone(ActionEvent event) {

    }
    
}
