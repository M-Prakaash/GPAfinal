/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpatable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;


public class ThirdsemController implements Initializable {    
    @FXML
    private JFXTextField filterField;

    @FXML
    private JFXButton scndsems;

    @FXML
    private AnchorPane scndanchorpane;

    @FXML
    private TableView<thirdsemdetails> thirdsem;

    @FXML
    private TableColumn<thirdsemdetails, String> colreg;

    @FXML
    private TableColumn<thirdsemdetails, String> colname;

    @FXML
    private TableColumn<thirdsemdetails, String> subone;

    @FXML
    private TableColumn<thirdsemdetails, String> subject2;

    @FXML
    private TableColumn<thirdsemdetails, String> subjectthree;
    
    @FXML
    private TableColumn<thirdsemdetails, String> subjectfour;

    @FXML
    private TableColumn<thirdsemdetails, String> subjectfive;

    @FXML
    private TableColumn<thirdsemdetails, String> subjectsix;
        @FXML
    private TableColumn<thirdsemdetails, String> subjectsvn;

    @FXML
    private TableColumn<thirdsemdetails, String> subjecteight;

    @FXML
    private TableColumn<thirdsemdetails, String> subjectnine;

    @FXML
    private TableColumn<thirdsemdetails, String> arrears;

   

     @FXML
    private TableColumn<thirdsemdetails, String> total;

    @FXML
    private TableColumn<thirdsemdetails, String> credits;

    @FXML
    private TableColumn<thirdsemdetails, String> gpa;
    
    private ObservableList<thirdsemdetails> datas;
    
private DbConnect dc;
    private void initFilter() {
      
        filterField.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {
                if(filterField.textProperty().get().isEmpty()) {
                    thirdsem.setItems(datas);
                    return;
                }
                
                ObservableList<thirdsemdetails> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<thirdsemdetails, ?>> cols = thirdsem.getColumns();
                for(int i=0; i<datas.size(); i++) {
                    
                    for(int j=0; j<cols.size(); j++) {
                        TableColumn col = cols.get(j);
                        String cellValue = col.getCellData(datas.get(i)).toString();
                        cellValue = cellValue.toLowerCase();
                        if(cellValue.contains(filterField.textProperty().get().toLowerCase())) {
                            tableItems.add(datas.get(i));
                            break;
                        }                        
                    }

                }
                thirdsem.setItems(tableItems);
            }
        });
    }
    @FXML
    private void loadfinal(ActionEvent event) {
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] sub = {"CS6301","CS6302","CS6303","CS6304","GE6351","MA6351","IT6311","IT6312","IT6313"};
            int j = sub.length;
            for (int i = 0; i < j; i++) {
                String querys = "update semtthreedata, gradevaluetable set " + sub[i] + " = gradevaluetable.points  where " + sub[i] + "= gradevaluetable.grade";
               conn.createStatement().executeUpdate(querys);
            }
        }
    catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"CS6301marks","cs6302marks","cs6303marks","cs6304marks","ge6351marks","ma6351marks","it6311marks","it6312marks","it6313marks"};
            String[] sub = {"CS6301","CS6302","CS6303","CS6304","GE6351","MA6351","IT6311","IT6312","IT6313"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight","subjectnine"};
            for(int i=0;i<9;i++){
            String queryss = "INSERT INTO decompose."+tables[i]+"(register,"+cols[i]+") SELECT id,"+sub[i]+" FROM decompose.semtthreedata";
               conn.createStatement().executeUpdate(queryss);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }    
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6301marks","cs6302marks","cs6303marks","cs6304marks","ge6351marks","ma6351marks","it6311marks","it6312marks","it6313marks"};
            String[] sub = {"CS6301","CS6302","CS6303","CS6304","GE6351","MA6351","IT6311","IT6312","IT6313"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight","subjectnine"};
            
            for(int i=0;i<9;i++){
            String querysss = "UPDATE "+tables[i]+" set "+tables[i]+".studmarks = "+tables[i]+"."+cols[i]+" * "+tables[i]+"."+sub[i];
               conn.createStatement().executeUpdate(querysss);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
       try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            
            String queryss = "insert into semthree(id,name) select id,name from semtthreedata;";
               conn.createStatement().executeUpdate(queryss);
       } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6301marks","cs6302marks","cs6303marks","cs6304marks","ge6351marks","ma6351marks","it6311marks","it6312marks","it6313marks"};
            String[] sub = {"CS6301","CS6302","CS6303","CS6304","GE6351","MA6351","IT6311","IT6312","IT6313"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight","subjectnine"};
            
            for(int i=0;i<9;i++){
            String querysss = "UPDATE decompose.semthree SET semthree."+sub[i]+ "= (select studmarks from decompose."+tables[i]+" where semthree.id = "+tables[i]+".register);";
               conn.createStatement().executeUpdate(querysss);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6301marks","cs6302marks","cs6303marks","cs6304marks","ge6351marks","ma6351marks","it6311marks","it6312marks","it6313marks"};
            String[] sub = {"CS6301","CS6302","CS6303","CS6304","GE6351","MA6351","IT6311","IT6312","IT6313"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight","subjectnine"};
            
            String querysss = "update decompose.semthree set semthree.TOTAL = semthree.CS6301 + semthree.CS6302 + semthree.CS6303+semthree.CS6304 + semthree.GE6351 + semthree.MA6351+semthree.IT6311 + semthree.IT6312 + semthree.IT6313 , semthree.GPA = semthree.TOTAL / semthree. SemThreeCredits;";
               conn.createStatement().executeUpdate(querysss);
            String querys = "update semthree set GPA = truncate(GPA,3)";
            conn.createStatement().executeUpdate(querys);

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            ResultSet rs = (ResultSet) conn.createStatement().executeQuery("SELECT * FROM decompose.semthree;");
            while (rs.next()) {
                System.out.println("success");
                 datas.add(new thirdsemdetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        colreg.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getReg()));
        colname.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getOne()));
        subone.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTwo()));
        subject2.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getThree()));
        subjectthree.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFour()));
        subjectfour.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getfive()));
        subjectfive.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getsix()));
        subjectsix.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getseven()));
         subjectsvn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().geteght()));
        subjecteight.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getnine()));
        subjectnine.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getten()));
        total.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getelvn()));
        credits.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().gettwlv()));
        gpa.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getthteen()));
        thirdsem.setItems(null);
        thirdsem.setItems(datas);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnect();
           
           initFilter();
                
    }
    }
