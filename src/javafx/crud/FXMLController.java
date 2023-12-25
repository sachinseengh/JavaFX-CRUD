/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafx.crud;

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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


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
    private ComboBox<?> gender;

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

    private String[] combogender={"Male","Female","Others"};
    
    
    public void comboBox(){
    
    List<String> list = new ArrayList<>();
    
    for(String data: combogender){
        list.add(data);
    }
    
    ObservableList datalist = FXCollections.observableArrayList(list);
    gender.setItems(datalist);
    
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
//        For calling Combox box

        comboBox();
        defaultId();
       
        showData();
        
    }    
    
}
