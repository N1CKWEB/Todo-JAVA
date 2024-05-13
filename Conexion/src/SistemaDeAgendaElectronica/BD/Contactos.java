/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaDeAgendaElectronica.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class Contactos {
    Boolean logueado = null;
    PreparedStatement instruccion = null;
    ResultSet resultado = null;
    Boolean registrado=null;
    Conexion conexion=new Conexion();
    Connection sl=conexion.conectar();


public Boolean agregarContactos(String nombre,String telefono){
    
    
   try {
    String consulta="INSERT INTO user (nombre,telefono) VALUES (?,?,?)";
    instruccion=sl.prepareStatement(consulta);
    
    } catch (SQLException e) {
       System.out.println(""+e);
    } finally {
    }
        
         //Seguir mañana terminando todo esto!
   return true;
 }
}
