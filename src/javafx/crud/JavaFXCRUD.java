/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafx.crud;

import java.io.IOException;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author sachi
 */
public class JavaFXCRUD extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        
        try{

        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        
        
        
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Crud Operation");
        primaryStage.setScene(scene);
        primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
