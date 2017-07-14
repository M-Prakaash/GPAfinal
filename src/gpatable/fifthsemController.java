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


public class fifthsemController implements Initializable {    
    @FXML
    private JFXTextField filterField;

    @FXML
    private JFXButton thirdsems;

    @FXML
    private AnchorPane scndanchorpane;

    @FXML
    private TableView<fifthsemdetails> fifthsem;

    @FXML
    private TableColumn<fifthsemdetails, String> colreg;

    @FXML
    private TableColumn<fifthsemdetails, String> colname;

    @FXML
    private TableColumn<fifthsemdetails, String> subone;

    @FXML
    private TableColumn<fifthsemdetails, String> subject2;

    @FXML
    private TableColumn<fifthsemdetails, String> subjectthree;
    
    @FXML
    private TableColumn<fifthsemdetails, String> subjectfour;

    @FXML
    private TableColumn<fifthsemdetails, String> subjectfive;

    @FXML
    private TableColumn<fifthsemdetails, String> subjectsix;
        @FXML
    private TableColumn<fifthsemdetails, String> subjectsvn;

    @FXML
    private TableColumn<fifthsemdetails, String> subjecteight;

    @FXML
    private TableColumn<fifthsemdetails, String> subjectnine;

    @FXML
    private TableColumn<fifthsemdetails, String> arrears;

   

     @FXML
    private TableColumn<fifthsemdetails, String> total;

    @FXML
    private TableColumn<fifthsemdetails, String> credits;

    @FXML
    private TableColumn<fifthsemdetails, String> gpa;
    
    private ObservableList<fifthsemdetails> datas;
    
private DbConnect dc;
    private void initFilter() {
      
        filterField.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {
                if(filterField.textProperty().get().isEmpty()) {
                    fifthsem.setItems(datas);
                    return;
                }
                
                ObservableList<fifthsemdetails> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<fifthsemdetails, ?>> cols = fifthsem.getColumns();
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
                fifthsem.setItems(tableItems);
            }
        });
    }
    @FXML
    private void loadfinal(ActionEvent event) {
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] sub = {"CS6502","CS6551","EC6801","IT6501","IT6502","IT6503","IT6511","IT6512","IT6513"};
            int j = sub.length;
            for (int i = 0; i < j; i++) {
                String querys = "update semfivedata, gradevaluetable set " + sub[i] + " = gradevaluetable.points  where " + sub[i] + "= gradevaluetable.grade";
               conn.createStatement().executeUpdate(querys);
            }
        }
    catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6502marks","cs6551marks","ec6801marks","it6501marks","it6502marks","it6503marks","it6511marks","it6512marks","it6513marks"};
            String[] sub = {"CS6502","CS6551","EC6801","IT6501","IT6502","IT6503","IT6511","IT6512","IT6513"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight","subjectnine"};
            for(int i=0;i<9;i++){
            String queryss = "INSERT INTO decompose."+tables[i]+"(register,"+cols[i]+") SELECT id,"+sub[i]+" FROM decompose.semfivedata";
               conn.createStatement().executeUpdate(queryss);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }    
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6502marks","cs6551marks","ec6801marks","it6501marks","it6502marks","it6503marks","it6511marks","it6512marks","it6513marks"};
            String[] sub = {"CS6502","CS6551","EC6801","IT6501","IT6502","IT6503","IT6511","IT6512","IT6513"};
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
            
            String queryss = "insert into semfive(id,name) select id,name from semfivedata;";
               conn.createStatement().executeUpdate(queryss);
       } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6502marks","cs6551marks","ec6801marks","it6501marks","it6502marks","it6503marks","it6511marks","it6512marks","it6513marks"};
            String[] sub = {"CS6502","CS6551","EC6801","IT6501","IT6502","IT6503","IT6511","IT6512","IT6513"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight","subjectnine"};
            
            for(int i=0;i<9;i++){
            String querysss = "UPDATE decompose.semfive SET semfive."+sub[i]+ "= (select studmarks from decompose."+tables[i]+" where semfive.id = "+tables[i]+".register);";
               conn.createStatement().executeUpdate(querysss);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6502marks","cs6551marks","ec6801marks","it6501marks","it6502marks","it6503marks","it6511marks","it6512marks","it6513marks"};
            String[] sub = {"CS6502","CS6551","EC6801","IT6501","IT6502","IT6503","IT6511","IT6512","IT6513"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight","subjectnine"};
            
            String querysss = "update decompose.semfive set semfive.TOTAL = semfive.CS6502 + semfive.CS6551 + semfive.EC6801 + semfive.IT6501 + semfive.IT6502 + semfive.IT6503 + semfive.IT6511 + semfive.IT6512 + semfive.IT6513 , semfive.GPA = semfive.TOTAL / semfive. SemFiveCredits;";
               conn.createStatement().executeUpdate(querysss);
            String querys = "update semfive set GPA = truncate(GPA,3)";
            conn.createStatement().executeUpdate(querys);

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            ResultSet rs = (ResultSet) conn.createStatement().executeQuery("SELECT * FROM decompose.semfive;");
            while (rs.next()) {
                System.out.println("success");
                 datas.add(new fifthsemdetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
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
        fifthsem.setItems(null);
        fifthsem.setItems(datas);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnect();
           
           initFilter();
                
    }
    }
