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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Preethi
 */
public class FirstSemesterController implements Initializable {
        @FXML
    private AnchorPane anchor;

    @FXML
    private TableView<sqldetailsfirst> tableview;

    @FXML
    private TableColumn<sqldetailsfirst, String> colreg;

    @FXML
    private TableColumn<sqldetailsfirst, String> colname;

    @FXML
    private TableColumn<sqldetailsfirst, String> subone;

    @FXML
    private TableColumn<sqldetailsfirst, String> subject2;

    @FXML
    private TableColumn<sqldetailsfirst, String> subjectthree;

    @FXML
    private TableColumn<sqldetailsfirst, String> subjectfour;

    @FXML
    private TableColumn<sqldetailsfirst, String> subjectfive;

    @FXML
    private TableColumn<sqldetailsfirst, String> subjectsix;

    @FXML
    private TableColumn<sqldetailsfirst, String> subjectseven;

    @FXML
    private TableColumn<sqldetailsfirst, String> subjecteight;

    @FXML
    private TableColumn<sqldetailsfirst, String> subjectnine;

    @FXML
    private TableColumn<sqldetailsfirst, String> totalobtained;

    @FXML
    private TableColumn<sqldetailsfirst, String> semcredits;

    @FXML
    private TableColumn<sqldetailsfirst, String> semoneGPA;

    @FXML
    private TableColumn<sqldetailsfirst, String> arrears;
        @FXML
    private JFXTextField txtField;
    private ObservableList<sqldetailsfirst> datas;
    private DbConnect dc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          dc = new DbConnect();
          initFilter();
    }
    private void initFilter() {
      
        txtField.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {
                if(txtField.textProperty().get().isEmpty()) {
                    tableview.setItems(datas);
                    return;
                }
                ObservableList<sqldetailsfirst> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<sqldetailsfirst, ?>> cols = tableview.getColumns();
                for(int i=0; i<datas.size(); i++) {
                    for(int j=0; j<cols.size(); j++) {
                        TableColumn col = cols.get(j);
                        String cellValue = col.getCellData(datas.get(i)).toString();
                        cellValue = cellValue.toLowerCase();
                        if(cellValue.contains(txtField.textProperty().get().toLowerCase())) {
                            tableItems.add(datas.get(i));
                            break;
                        }                        
                    }

                }
                tableview.setItems(tableItems);
            }
        });
    }
    @FXML
    private void loaddetails(ActionEvent event) {
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] sub = {"CY6151","GE6151","GE6152","HS6151","MA6151","PH6151","GE6161","GE6162","GE6163"};
            int j = sub.length;
            for (int i = 0; i < j; i++) {
                String querys = "update semonedata, gradevaluetable set " + sub[i] + " = gradevaluetable.points  where " + sub[i] + "= gradevaluetable.grade";
               conn.createStatement().executeUpdate(querys);
            }
        }
    catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cy6151marks","ge6151marks","ge6152marks","hs6151marks","ma6151marks","ph6151marks","ge6161marks","ge6162marks","ge6163marks"};
            String[] sub = {"CY6151","GE6151","GE6152","HS6151","MA6151","PH6151","GE6161","GE6162","GE6163"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight","subjectnine"};
            for(int i=0;i<9;i++){
            String queryss = "INSERT INTO decompose."+tables[i]+"(register,"+cols[i]+") SELECT id,"+sub[i]+" FROM decompose.semonedata";
               conn.createStatement().executeUpdate(queryss);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }    
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cy6151marks","ge6151marks","ge6152marks","hs6151marks","ma6151marks","ph6151marks","ge6161marks","ge6162marks","ge6163marks"};
            String[] sub = {"CY6151","GE6151","GE6152","HS6151","MA6151","PH6151","GE6161","GE6162","GE6163"};
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
            
            String queryss = "insert into semone(id,name) select id,name from semonedata;";
               conn.createStatement().executeUpdate(queryss);
            

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            String[] tables = {"cy6151marks","ge6151marks","ge6152marks","hs6151marks","ma6151marks","ph6151marks","ge6161marks","ge6162marks","ge6163marks"};
            String[] sub = {"CY6151","GE6151","GE6152","HS6151","MA6151","PH6151","GE6161","GE6162","GE6163"};
            String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight","subjectnine"};
                       
            for(int i=0;i<9;i++){
            String querysss = "UPDATE decompose.semone SET semone."+sub[i]+ "= (select studmarks from decompose."+tables[i]+" where semone.id = "+tables[i]+".register);";
               conn.createStatement().executeUpdate(querysss);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
           String[] tables = {"cy6151marks","ge6151marks","ge6152marks","hs6151marks","ma6151marks","ph6151marks","ge6161marks","ge6162marks","ge6163marks"};
           String[] sub = {"CY6151","GE6151","GE6152","HS6151","MA6151","PH6151","GE6161","GE6162","GE6163"}; 
           String[] cols= {"subjectone","subjecttwo","subjectthree","subjectfour","subjectfive","subjectsix","subjectseven","subjecteight","subjectnine"};
           String querysss = "update decompose.semone set semone.TOTAL = semone.CY6151 + semone.GE6151 + semone.GE6152 + semone.HS6151 + semone.MA6151 + semone.PH6151 + semone.GE6161 + semone.GE6162 + semone.GE6163 , semone.GPA = semone.TOTAL / semone. SemOneCredits;";
               conn.createStatement().executeUpdate(querysss);
            String querys = "update semone set GPA = truncate(GPA,3)";
            conn.createStatement().executeUpdate(querys);

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        try {
            Connection conn = (Connection) dc.Connect();
            datas = FXCollections.observableArrayList();
            ResultSet rs = (ResultSet) conn.createStatement().executeQuery("SELECT * FROM decompose.semone;");
            while (rs.next()) {
                System.out.println("success");
                 datas.add(new sqldetailsfirst(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
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
        subjectseven.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().geteght()));
        subjecteight.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getnine()));
        subjectnine.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getten()));
        totalobtained.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getelvn()));
        semcredits.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().gettwlv()));
        semoneGPA.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getthteen()));
        tableview.setItems(null);
        tableview.setItems(datas);

    }
    
}
