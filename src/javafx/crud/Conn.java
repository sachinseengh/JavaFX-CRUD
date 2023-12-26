/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package javafx.crud;

import java.sql.*;
public class Conn {
    Connection c;
    Statement s;
        
    public Conn(){
        try{
       
       c= DriverManager.getConnection("jdbc:mysql://localhost/CRUDOperation","root","");
       s=c.createStatement();
//       System.out.println(s);

    }catch(Exception e){
        System.out.println(e);
    }
    }
    
    public static void main(String args[]) {
        new Conn();
    }
}
