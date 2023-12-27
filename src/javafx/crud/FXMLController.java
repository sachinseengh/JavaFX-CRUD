/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafx.crud;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.sql.*;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class FXMLController implements Initializable {

    
    @FXML
    private Button clear;
    
    
       @FXML
    private TableView<Data> table_view;

    @FXML
    private TableColumn<Data, String> col_gender;

    @FXML
    private TableColumn<Data, String> col_given;

    @FXML
    private TableColumn<Data, Integer> col_id;

    @FXML
    private TableColumn<Data, String> col_image;

    @FXML
    private TableColumn<Data, String> col_surname;

    @FXML
    private Button delete;

    @FXML
    private Label file_path;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TextField given;

    @FXML
    private TextField id;

    @FXML
    private ImageView image_view;

    @FXML
    private Button insert;

    @FXML
    private Button insert_image;

    @FXML
    private Button print;

    @FXML
    private TextField surname;

    @FXML
    private Button update;
    
    
    @FXML
    private AnchorPane left_main;

    private String[] combogender={"Male","Female","Others"};
    
    
    public void comboBox(){
    
//    List<String> list = new ArrayList<>();
//    
//    for(String data: combogender){
//        list.add(data);
//    }
//    
//    ObservableList datalist = FXCollections.observableArrayList(list);
//    gender.setItems(datalist);;

gender.getItems().addAll(combogender);
   
    
    }
    
  
    public void textFieldDesign(){
        
        if(id.isFocused()){
            id.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            surname.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
             gender.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
              given.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
        }else if(surname.isFocused()){
            surname.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            id.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
             gender.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
              given.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
        }else if(gender.isFocused()){
            gender.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            surname.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
             id.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
              given.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
        }else if(given.isFocused()){
            given.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            surname.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
             gender.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
              id.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
        }
    }
    
    
    
    public void defaultId(){
         id.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
    }
//    -------------------------for Adding Image---------------
    
   
    
    public void insertImage(){
        
        FileChooser open = new FileChooser();
        
        Stage stage =(Stage) left_main.getScene().getWindow();
        
        File file = open.showOpenDialog(stage);
        
        if(file != null){
            
            String path = file.getAbsolutePath();
            
            path = path.replace("\\", "\\\\");
            file_path.setText(path);
            

            
            //                                                  FILE PATH , WIDTH ,HEIGHT ,RATION,SMOOTH
            
            Image image = new Image(file.toURI().toString(),207,114,false,true);
            image_view.setImage(image);
            
            
        }
        
        
    }
//    '----------------------------------------Inserting into database---------------------------------------'
    public void insert(){
        Conn c = new Conn();
        
        String sql = "insert into account (id,surname,given,gender,picture) values('"+id.getText()+"','"+surname.getText()+"',"
                + "'"+given.getText()+"','"+(String)gender.getSelectionModel().getSelectedItem()+"','"+file_path.getText()+"')";
        
        
        try{
            
            c.s.executeUpdate(sql);
            showData();
            
        }catch(Exception e){
            
        }
    }
//    ---------------------------------------------Inserting into database-------------------------------------------
    
    
    public void selectData(){
    
    Data data = table_view.getSelectionModel().getSelectedItem();
    
    int num = table_view.getSelectionModel().getSelectedIndex();
    
    if((num-1) < -1)
        return;
    
    
    id.setText(String.valueOf(data.getId()));
    surname.setText(String.valueOf(data.getSurname()));
    given.setText(String.valueOf(data.getGiven()));
    
//   System.out.println(data.getGender());
 gender.setValue(data.getGender());
   
 
//System.out.print(data.getGender().getClass().getSimpleName());

   
    String picture ="file:"+data.getPicture();
        
    Image image = new Image(picture,207,114,false,true);
    
    image_view.setImage(image);
    String path = data.getPicture();
//      path = path.replace("\\", "\\\\");
    file_path.setText(path);
    
    
    }
    
//   ----------------Closing Select Data---------------------------------------------------------------------------------//
    
    
    
    
    //-----------------------------------Update-----------------------------------------------------------------------------
    public void update(){
        Conn c= new Conn();
        String path = file_path.getText();
        path = path.replace("\\","\\\\");
        
           String sql = "update account set surname='"+surname.getText()+"',"
                   + "given ='"+given.getText()+"',gender='"+gender.getSelectionModel().getSelectedItem()+"', picture='"+path+"' where id ='"+id.getText()+"' ";
           
           if(id.getText().isEmpty() || surname.getText().isEmpty() || given.getText().isEmpty()
                   ||gender.getSelectionModel().isEmpty() || image_view.getImage()==null){
               
               Alert alert = new Alert(AlertType.ERROR);
               
               alert.setTitle("Error Message");
               alert.setHeaderText(null);
               alert.setContentText("Enter all blanks fields!");
               alert.showAndWait();
           }
           
           try{
               
               c.s.executeUpdate(sql);
               
               Alert alert = new Alert(AlertType.INFORMATION);

                alert.setTitle("Update message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Update the data!");
                alert.showAndWait();

               showData();
               clear();
               
           }catch(Exception e){
               e.printStackTrace();
           }
    }
//    --------------------------------------------Closing Update --------------------------------------------------------//
    
    
//    ---------------------------------------------Clear ------------------------------------------------------------------//
        public void clear(){
    
    
    id.setText("");
    surname.setText("");
    given.setText("");
    gender.getSelectionModel().clearSelection();
    
    image_view.setImage(null);
    file_path.setText("");
    
    
    }
    
//   -------------------------------------     Closing clear -------------------------------------------------------------    
    

        
//--------------------------------------------Delete -----------------------------------------------------------------
        
public void delete(){
    
    
    
    
    
    
    
     if(id.getText().isEmpty() || surname.getText().isEmpty() || given.getText().isEmpty()
                   ||gender.getSelectionModel().isEmpty() || image_view.getImage()==null){
               
               Alert alert = new Alert(AlertType.ERROR);
               
               alert.setTitle("Error Message");
               alert.setHeaderText(null);
               alert.setContentText("No id or row Selected to Delete data!");
               alert.showAndWait();
           }else{
    Conn c  = new Conn();
    
    String sql = "delete from account where id ='"+id.getText()+"'";
    
    try{
    Alert alert = new Alert(AlertType.CONFIRMATION);
    
    alert.setTitle("Confimation");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure that you want to delete it");
    
    Optional<ButtonType> buttonType = alert.showAndWait();
    
    
    if(buttonType.get() == ButtonType.OK){
        c.s.executeUpdate(sql);
        
    }else{
        return;
    }
    showData();
    clear();
    
        }catch(Exception e){
            
        }
     }
}

    
//    --------------------------------Closing Delete----------------------------//
    
    
    
    
    
//    -------------------------For Table Data-----------------------------------
    
    public ObservableList<Data> dataList(){
        Conn conn= new Conn();
        
        ObservableList<Data> datalist= FXCollections.observableArrayList();
        
        String sql = "select *from account";
        
        try{
            
            ResultSet result = conn.s.executeQuery(sql);
            
            Data data;
            
            while(result.next()){
                data = new Data(result.getInt("id"),result.getString("surname"),result.getString("given"),result.getString("gender"),result.getString("picture"));
                
                datalist.add(data);
            }
        }catch(Exception e){
            
        }
        
        return datalist;
        
    }
    
    public void showData(){
        ObservableList<Data> showList = dataList();
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_given.setCellValueFactory(new PropertyValueFactory<>("given"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("picture"));
        
        table_view.setItems(showList);
    }
//    -------------------------------for Table data--------------------------------

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
//        For calling Combox box

        comboBox();

        defaultId();
       
        showData();

        
    }    
    
}
