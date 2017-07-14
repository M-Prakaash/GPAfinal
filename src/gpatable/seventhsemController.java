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


public class seventhsemController implements Initializable {    
    @FXML
    private AnchorPane scndanchorpane;

    @FXML
    private TableView<seventhsemdetails> seventhsem;

    @FXML
    private TableColumn<seventhsemdetails, String> colreg;

    @FXML
    private TableColumn<seventhsemdetails, String> colname;

    @FXML
    private TableColumn<seventhsemdetails, String> subone;

    @FXML
    private TableColumn<seventhsemdetails, String> subject2;

    @FXML
    private TableColumn<seventhsemdetails, String> subjectthree;

    @FXML
    private TableColumn<seventhsemdetails, String> subjectfour;

    @FXML
    private TableColumn<seventhsemdetails, String> subjectfive;

    @FXML
    private TableColumn<seventhsemdetails, String> subjectsix;

    @FXML
    private TableColumn<seventhsemdetails, String> subjectsvn;

    @FXML
    private TableColumn<seventhsemdetails, String> subjecteight;

    @FXML
    private TableColumn<seventhsemdetails, String> total;

    @FXML
    private TableColumn<seventhsemdetails, String> credits;

    @FXML
    private TableColumn<seventhsemdetails, String> gpa;

    @FXML
    private TableColumn<seventhsemdetails, String> arrears;

    @FXML
    private JFXButton fourthsems;

    @FXML
    private JFXTextField filterField;

    
    private ObservableList<seventhsemdetails> datas;
    
private DbConnect dc;
    private void initFilter() {
      
        filterField.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {
                if(filterField.textProperty().get().isEmpty()) {
                    seventhsem.setItems(datas);
                    return;
                }
                
                ObservableList<seventhsemdetails> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<seventhsemdetails, ?>> cols = seventhsem.getColumns();
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
                seventhsem.setItems(tableItems);
            }
        });
    }
    @FXML
    private void loadfinal(ActionEvent event) {
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
                String[] sub = {"CS6003","CS6701","CS6703","IT6701","IT6702","IT6711","IT6712","IT6713"};
            int j = sub.length;
            for (int i = 0; i < j; i++) {
                String querys = "update semsevendata, gradevaluetable set " + sub[i] + " = gradevaluetable.points  where " + sub[i] + "= gradevaluetable.grade";
               conn.createStatement().executeUpdate(querys);
            }
        }
    catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6003marks","cs6701marks","cs6703marks","it6701marks","it6702marks","it6711marks","it6712marks","it6713marks"};
            String[] sub = {"CS6003","CS6701","CS6703","IT6701","IT6702","IT6711","IT6712","IT6713"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight"};
            for(int i=0;i<8;i++){
            String queryss = "INSERT INTO decompose."+tables[i]+"(register,"+cols[i]+") SELECT id,"+sub[i]+" FROM decompose.semsevendata";
               conn.createStatement().executeUpdate(queryss);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }    
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
              String[] tables = {"cs6003marks","cs6701marks","cs6703marks","it6701marks","it6702marks","it6711marks","it6712marks","it6713marks"};
            String[] sub = {"CS6003","CS6701","CS6703","IT6701","IT6702","IT6711","IT6712","IT6713"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight"};
            
            for(int i=0;i<8;i++){
            String querysss = "UPDATE "+tables[i]+" set "+tables[i]+".studmarks = "+tables[i]+"."+cols[i]+" * "+tables[i]+"."+sub[i];
               conn.createStatement().executeUpdate(querysss);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
       try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            
            String queryss = "insert into semseven(id,name) select id,name from semsevendata;";
               conn.createStatement().executeUpdate(queryss);
       } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
               String[] tables = {"cs6003marks","cs6701marks","cs6703marks","it6701marks","it6702marks","it6711marks","it6712marks","it6713marks"};
            String[] sub = {"CS6003","CS6701","CS6703","IT6701","IT6702","IT6711","IT6712","IT6713"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight"};
            
            for(int i=0;i<8;i++){
            String querysss = "UPDATE decompose.semseven SET semseven."+sub[i]+ "= (select studmarks from decompose."+tables[i]+" where semseven.id = "+tables[i]+".register);";
               conn.createStatement().executeUpdate(querysss);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
              String[] tables = {"cs6003marks","cs6701marks","cs6703marks","it6701marks","it6702marks","it6711marks","it6712marks","it6713marks"};
            String[] sub = {"CS6003","CS6701","CS6703","IT6701","IT6702","IT6711","IT6712","IT6713"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight"};
            
            String querysss = "update decompose.semseven set semseven.TOTAL = semseven.CS6003 + semseven.CS6701 + semseven.CS6703+semseven.IT6701 + semseven.IT6702 + semseven.IT6711+semseven.IT6712 + semseven.IT6713 , semseven.GPA = semseven.TOTAL / semseven. SemSevenCredits;";
               conn.createStatement().executeUpdate(querysss);
            String querys = "update semseven set GPA = round(GPA,3)";
            conn.createStatement().executeUpdate(querys);

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            ResultSet rs = (ResultSet) conn.createStatement().executeQuery("SELECT * FROM decompose.semseven;");
            while (rs.next()) {
                System.out.println("success");
                 datas.add(new seventhsemdetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)));
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
        total.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getten()));
        credits.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getelvn()));
        gpa.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().gettwlv()));
        seventhsem.setItems(null);
        seventhsem.setItems(datas);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnect();
           
           initFilter();
                
    }
    }
