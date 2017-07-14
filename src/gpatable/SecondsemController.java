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


public class SecondsemController implements Initializable {    
    @FXML
    private JFXTextField filterField;

    @FXML
    private JFXButton scndsems;

    @FXML
    private AnchorPane scndanchorpane;

    @FXML
    private TableView<scndsqldetails> scndsem;

    @FXML
    private TableColumn<scndsqldetails, String> colreg;

    @FXML
    private TableColumn<scndsqldetails, String> colname;

    @FXML
    private TableColumn<scndsqldetails, String> subone;

    @FXML
    private TableColumn<scndsqldetails, String> subject2;

    @FXML
    private TableColumn<scndsqldetails, String> subjectthree;
    
    @FXML
    private TableColumn<scndsqldetails, String> subjectfour;

    @FXML
    private TableColumn<scndsqldetails, String> subjectfive;

    @FXML
    private TableColumn<scndsqldetails, String> subjectsix;
        @FXML
    private TableColumn<scndsqldetails, String> subjectsvn;

    @FXML
    private TableColumn<scndsqldetails, String> subjecteight;

    @FXML
    private TableColumn<scndsqldetails, String> subjectnine;

    @FXML
    private TableColumn<scndsqldetails, String> arrears;

   

     @FXML
    private TableColumn<scndsqldetails, String> total;

    @FXML
    private TableColumn<scndsqldetails, String> credits;

    @FXML
    private TableColumn<scndsqldetails, String> gpa;
    
    private ObservableList<scndsqldetails> datas;
    
private DbConnect dc;
   private void initFilter() {
        datas = FXCollections.observableArrayList();
        filterField.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {
                if(filterField.textProperty().get().isEmpty()) {
                    scndsem.setItems(datas);
                    return;
                }
                ObservableList<scndsqldetails> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<scndsqldetails, ?>> cols = scndsem.getColumns();
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
                scndsem.setItems(tableItems);
            }
        });
    }
    @FXML
    private void loadfinal(ActionEvent event) {
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] sub = {"CS6201","CS6202","CY6251","GE6262","HS6251","IT6211","IT6212","MA6251","PH6251"};
            int j = sub.length;
            for (int i = 0; i < j; i++) {
                String querys = "update semtwodata, gradevaluetable set " + sub[i] + " = gradevaluetable.points  where " + sub[i] + "= gradevaluetable.grade";
               conn.createStatement().executeUpdate(querys);
            }
        }
    catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6201marks","cs6202marks","cy6251marks","ge6262marks","hs6251marks","it6211marks","it6212marks","ma6251marks","ph6251marks"};
            String[] sub = {"CS6201","CS6202","CY6251","GE6262","HS6251","IT6211","IT6212","MA6251","PH6251"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight","subjectnine"};
            for(int i=0;i<9;i++){
            String queryss = "INSERT INTO decompose."+tables[i]+"(register,"+cols[i]+") SELECT id,"+sub[i]+" FROM decompose.semtwodata";
               conn.createStatement().executeUpdate(queryss);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }    
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6201marks","cs6202marks","cy6251marks","ge6262marks","hs6251marks","it6211marks","it6212marks","ma6251marks","ph6251marks"};
            String[] sub = {"CS6201","CS6202","CY6251","GE6262","HS6251","IT6211","IT6212","MA6251","PH6251"};
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
            
            String queryss = "insert into semtwo(id,name) select id,name from semtwodata;";
               conn.createStatement().executeUpdate(queryss);
            

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6201marks","cs6202marks","cy6251marks","ge6262marks","hs6251marks","it6211marks","it6212marks","ma6251marks","ph6251marks"};
            String[] sub = {"CS6201","CS6202","CY6251","GE6262","HS6251","IT6211","IT6212","MA6251","PH6251"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight","subjectnine"};
            
            for(int i=0;i<9;i++){
            String querysss = "UPDATE decompose.semtwo SET semtwo."+sub[i]+ "= (select studmarks from decompose."+tables[i]+" where semtwo.id = "+tables[i]+".register);";
               conn.createStatement().executeUpdate(querysss);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cs6201marks","cs6202marks","cy6251marks","ge6262marks","hs6251marks","it6211marks","it6212marks","ma6251marks","ph6251marks"};
            String[] sub = {"CS6201","CS6202","CY6251","GE6262","HS6251","IT6211","IT6212","MA6251","PH6251"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight","subjectnine"};
            
            
            String querysss = "update decompose.semtwo set semtwo.TOTAL = semtwo.CS6201 + semtwo.CS6202 + semtwo.CY6251 + semtwo.GE6262 + semtwo.HS6251 + semtwo.IT6211 + semtwo.IT6212 + semtwo.MA6251 + semtwo.PH6251 , semtwo.GPA = semtwo.TOTAL / semtwo. SemTwoCredits;";
               conn.createStatement().executeUpdate(querysss);
            String querys = "update semtwo set GPA = truncate(GPA,3)";
            conn.createStatement().executeUpdate(querys);

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            ResultSet rs = (ResultSet) conn.createStatement().executeQuery("SELECT * FROM decompose.semtwo;");
            while (rs.next()) {
                System.out.println("success");
                 datas.add(new scndsqldetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
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
        scndsem.setItems(null);
        scndsem.setItems(datas);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnect();
           
           initFilter();
                
    }
    }
