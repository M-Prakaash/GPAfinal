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


public class fourthsemController implements Initializable {    
    @FXML
    private AnchorPane scndanchorpane;

    @FXML
    private TableView<foursemdetails> fourthsem;

    @FXML
    private TableColumn<foursemdetails, String> colreg;

    @FXML
    private TableColumn<foursemdetails, String> colname;

    @FXML
    private TableColumn<foursemdetails, String> subone;

    @FXML
    private TableColumn<foursemdetails, String> subject2;

    @FXML
    private TableColumn<foursemdetails, String> subjectthree;

    @FXML
    private TableColumn<foursemdetails, String> subjectfour;

    @FXML
    private TableColumn<foursemdetails, String> subjectfive;

    @FXML
    private TableColumn<foursemdetails, String> subjectsix;

    @FXML
    private TableColumn<foursemdetails, String> subjectsvn;

    @FXML
    private TableColumn<foursemdetails, String> subjecteight;

    @FXML
    private TableColumn<foursemdetails, String> total;

    @FXML
    private TableColumn<foursemdetails, String> credits;

    @FXML
    private TableColumn<foursemdetails, String> gpa;

    @FXML
    private TableColumn<foursemdetails, String> arrears;

    @FXML
    private JFXButton fourthsems;

    @FXML
    private JFXTextField filterField;

    
    private ObservableList<foursemdetails> datas;
    
private DbConnect dc;
    private void initFilter() {
      
        filterField.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {
                if(filterField.textProperty().get().isEmpty()) {
                    fourthsem.setItems(datas);
                    return;
                }
                
                ObservableList<foursemdetails> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<foursemdetails, ?>> cols = fourthsem.getColumns();
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
                fourthsem.setItems(tableItems);
            }
        });
    }
    @FXML
    private void loadfinal(ActionEvent event) {
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] sub = {"CS6401","CS6402","CS6403","EC6504","IT6411","IT6412","IT6413","MA6453"};
            int j = sub.length;
            for (int i = 0; i < j; i++) {
                String querys = "update semfourdata, gradevaluetable set " + sub[i] + " = gradevaluetable.points  where " + sub[i] + "= gradevaluetable.grade";
               conn.createStatement().executeUpdate(querys);
            }
        }
    catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6401marks","cs6402marks","cs6403marks","ec6504marks","it6411marks","it6412marks","it6413marks","ma6453marks"};
            String[] sub = {"CS6401","CS6402","CS6403","EC6504","IT6411","IT6412","IT6413","MA6453"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectsix","subjectseven","subjecteight","subjectfive",};
            for(int i=0;i<8;i++){
            String queryss = "INSERT INTO decompose."+tables[i]+"(register,"+cols[i]+") SELECT id,"+sub[i]+" FROM decompose.semfourdata";
               conn.createStatement().executeUpdate(queryss);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }    
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
           String[] tables = {"cs6401marks","cs6402marks","cs6403marks","ec6504marks","it6411marks","it6412marks","it6413marks","ma6453marks"};
            String[] sub = {"CS6401","CS6402","CS6403","EC6504","IT6411","IT6412","IT6413","MA6453"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectsix","subjectseven","subjecteight","subjectfive",};
            
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
            
            String queryss = "insert into semfour(id,name) select id,name from semfourdata;";
               conn.createStatement().executeUpdate(queryss);
       } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
          String[] tables = {"cs6401marks","cs6402marks","cs6403marks","ec6504marks","it6411marks","it6412marks","it6413marks","ma6453marks"};
            String[] sub = {"CS6401","CS6402","CS6403","EC6504","IT6411","IT6412","IT6413","MA6453"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectsix","subjectseven","subjecteight","subjectfive",};
            
            for(int i=0;i<8;i++){
            String querysss = "UPDATE decompose.semfour SET semfour."+sub[i]+ "= (select studmarks from decompose."+tables[i]+" where semfour.id = "+tables[i]+".register);";
               conn.createStatement().executeUpdate(querysss);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6401marks","cs6402marks","cs6403marks","ec6504marks","it6411marks","it6412marks","it6413marks","ma6453marks"};
            String[] sub = {"CS6401","CS6402","CS6403","EC6504","IT6411","IT6412","IT6413","MA6453"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectsix","subjectseven","subjecteight","subjectfive",};
            
            String querysss = "update decompose.semfour set semfour.TOTAL = semfour.CS6401 + semfour.CS6402 + semfour.CS6403+semfour.EC6504 + semfour.MA6453 + semfour.IT6411+semfour.IT6412 + semfour.IT6413 , semfour.GPA = semfour.TOTAL / semfour. SemFourCredits;";
               conn.createStatement().executeUpdate(querysss);
            String querys = "update semfour set GPA = truncate(GPA,3)";
            conn.createStatement().executeUpdate(querys);

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            ResultSet rs = (ResultSet) conn.createStatement().executeQuery("SELECT * FROM decompose.semfour;");
            while (rs.next()) {
                System.out.println("success");
                 datas.add(new foursemdetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)));
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
        fourthsem.setItems(null);
        fourthsem.setItems(datas);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnect();
           
           initFilter();
                
    }
    }
