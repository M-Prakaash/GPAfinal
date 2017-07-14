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


public class sixthsemController implements Initializable {    
    @FXML
    private JFXTextField filterField;

    @FXML
    private JFXButton thirdsems;

    @FXML
    private AnchorPane scndanchorpane;

    @FXML
    private TableView<sixthsemdetails> sixthsem;

    @FXML
    private TableColumn<sixthsemdetails, String> colreg;

    @FXML
    private TableColumn<sixthsemdetails, String> colname;

    @FXML
    private TableColumn<sixthsemdetails, String> subone;

    @FXML
    private TableColumn<sixthsemdetails, String> subject2;

    @FXML
    private TableColumn<sixthsemdetails, String> subjectthree;
    
    @FXML
    private TableColumn<sixthsemdetails, String> subjectfour;

    @FXML
    private TableColumn<sixthsemdetails, String> subjectfive;

    @FXML
    private TableColumn<sixthsemdetails, String> subjectsix;
        @FXML
    private TableColumn<sixthsemdetails, String> subjectsvn;

    @FXML
    private TableColumn<sixthsemdetails, String> subjecteight;

    @FXML
    private TableColumn<sixthsemdetails, String> subjectnine;

    @FXML
    private TableColumn<sixthsemdetails, String> arrears;

   

     @FXML
    private TableColumn<sixthsemdetails, String> total;

    @FXML
    private TableColumn<sixthsemdetails, String> credits;

    @FXML
    private TableColumn<sixthsemdetails, String> gpa;
    
    private ObservableList<sixthsemdetails> datas;
    
private DbConnect dc;
    private void initFilter() {
      
        filterField.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {
                if(filterField.textProperty().get().isEmpty()) {
                    sixthsem.setItems(datas);
                    return;
                }
                
                ObservableList<sixthsemdetails> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<sixthsemdetails, ?>> cols = sixthsem.getColumns();
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
                sixthsem.setItems(tableItems);
            }
        });
    }
    @FXML
    private void loadfinal(ActionEvent event) {
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] sub = {"CS6001","CS6601","CS6659","CS6660","IT6601","IT6602","GE6674","IT6611","IT6612"};
            int j = sub.length;
            for (int i = 0; i < j; i++) {
                String querys = "update semsixdata, gradevaluetable set " + sub[i] + " = gradevaluetable.points  where " + sub[i] + "= gradevaluetable.grade";
               conn.createStatement().executeUpdate(querys);
            }
        }
    catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6001marks","cs6601marks","cs6659marks","cs6660marks","it6601marks","it6602marks","ge6674marks","it6611marks","it6612marks"};
            String[] sub = {"CS6001","CS6601","CS6659","CS6660","IT6601","IT6602","GE6674","IT6611","IT6612"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight","subjectnine"};
            for(int i=0;i<9;i++){
            String queryss = "INSERT INTO decompose."+tables[i]+"(register,"+cols[i]+") SELECT id,"+sub[i]+" FROM decompose.semsixdata";
               conn.createStatement().executeUpdate(queryss);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }    
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6001marks","cs6601marks","cs6659marks","cs6660marks","it6601marks","it6602marks","ge6674marks","it6611marks","it6612marks"};
            String[] sub = {"CS6001","CS6601","CS6659","CS6660","IT6601","IT6602","GE6674","IT6611","IT6612"};
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
            
            String queryss = "insert into semsix(id,name) select id,name from semsixdata;";
               conn.createStatement().executeUpdate(queryss);
                      } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6001marks","cs6601marks","cs6659marks","cs6660marks","it6601marks","it6602marks","ge6674marks","it6611marks","it6612marks"};
            String[] sub = {"CS6001","CS6601","CS6659","CS6660","IT6601","IT6602","GE6674","IT6611","IT6612"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight","subjectnine"};
            
            for(int i=0;i<9;i++){
            String querysss = "UPDATE decompose.semsix SET semsix."+sub[i]+ "= (select studmarks from decompose."+tables[i]+" where semsix.id = "+tables[i]+".register);";
               conn.createStatement().executeUpdate(querysss);
               
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6001marks","cs6601marks","cs6659marks","cs6660marks","it6601marks","it6602marks","ge6674marks","it6611marks","it6612marks"};
            String[] sub = {"CS6001","CS6601","CS6659","CS6660","IT6601","IT6602","GE6674","IT6611","IT6612"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight","subjectnine"};
            
            String querysss = "update decompose.semsix set semsix.TOTAL = semsix.CS6001 + semsix.CS6601 + semsix.CS6659 + semsix.CS6660 + semsix.IT6601 + semsix.IT6602 + semsix.GE6674 + semsix.IT6611 + semsix.IT6612 , semsix.GPA = semsix.TOTAL / semsix. SemSixCredits;";
               conn.createStatement().executeUpdate(querysss);
            String querys = "update semsix set semsix.GPA = truncate(GPA,3)";
            conn.createStatement().executeUpdate(querys);

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            ResultSet rs = (ResultSet) conn.createStatement().executeQuery("SELECT * FROM decompose.semsix;");
            while (rs.next()) {
                System.out.println("success");
                 datas.add(new sixthsemdetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
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
        sixthsem.setItems(null);
        sixthsem.setItems(datas);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnect();
           
           initFilter();
                
    }
    }
