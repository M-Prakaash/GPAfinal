package gpatable;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
public class FXMLDocumentController implements Initializable {
    
   
    @FXML
    private AnchorPane panes;

    @FXML
    private JFXHamburger ham;

    @FXML
    private JFXDrawer drawer;
    
    @FXML
     AnchorPane tablepane;
public static AnchorPane rpane;

    public static AnchorPane rootP;
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
            panes = rootP;
            drawer.open();
            VBox box = FXMLLoader.load(getClass().getResource("FXMLContent.fxml"));
            drawer.setSidePane(box);
                    for(Node node : box.getChildren()) {
                            if (node.getAccessibleText() != null){
                                node.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent e) {
                                        switch (node.getAccessibleText()) {
                                            case "semesterone":
                                        try {
                                                AnchorPane pane = FXMLLoader.load(getClass().getResource("FirstSemester.fxml"));
                                                tablepane.getChildren().setAll(pane);
                                            } catch (IOException ex) {
                                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                                break;
                                            case "semestertwo" :
                                                try {
                                                AnchorPane panes = FXMLLoader.load(getClass().getResource("secondsem.fxml"));
                                                tablepane.getChildren().setAll(panes);
                                            } catch (IOException ex) {
                                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                                break;
                                            case "semesterthree" :
                                                    try {
                                                AnchorPane panes = FXMLLoader.load(getClass().getResource("thirdsem.fxml"));
                                                tablepane.getChildren().setAll(panes);
                                            } catch (IOException ex) {
                                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                                break;
                                            case "semesterfour" :
                                                        try {
                                                AnchorPane panes = FXMLLoader.load(getClass().getResource("fourthsem.fxml"));
                                                tablepane.getChildren().setAll(panes);
                                            } catch (IOException ex) {
                                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                                break;
                                            case "semesterfive" :
                                                        try {
                                                AnchorPane panes = FXMLLoader.load(getClass().getResource("fifthsem.fxml"));
                                                tablepane.getChildren().setAll(panes);
                                            } catch (IOException ex) {
                                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                                break;
                                                case "semestersix" :
                                                        try {
                                                AnchorPane panes = FXMLLoader.load(getClass().getResource("sixthsem.fxml"));
                                                tablepane.getChildren().setAll(panes);
                                            } catch (IOException ex) {
                                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                                break;case "semesterseven" :
                                                        try {
                                                AnchorPane panes = FXMLLoader.load(getClass().getResource("seventhsem.fxml"));
                                                tablepane.getChildren().setAll(panes);
                                            } catch (IOException ex) {
                                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                                break;
                                        }
                                    }
                                });
            }
               
           }
           
                             
            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(ham);
            transition.setRate(-1);
            ham.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
                transition.setRate(transition.getRate()*-1);
                transition.play();
                
                if(drawer.isShown())
                {
                    drawer.close();
                }else
                    drawer.open();
                
            });
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
