/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaDeAgendaElectronica.BD;

import java.sql.Connection;

//Usuarios
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Usuarios {
 
    Boolean logueado = null;
    PreparedStatement instruccion = null;
    ResultSet resultado = null;
    Boolean registrado=null;
    
          Conexion conexion=new Conexion();
     Connection sl=conexion.conectar();
     
    public Boolean agregarLogin(String usuario,String correo, String contraseña){
    
        try {
             
       
   
           
             String consulta="INSERT INTO user (usuario,correo,contraseña) VALUES (?, ?, ?)";
             instruccion=sl.prepareStatement(consulta);
             
             instruccion.setString(1, usuario);
             instruccion.setString(2, correo);
             instruccion.setString(3, contraseña);

             instruccion.executeUpdate();
             
             System.out.println("Se creo correctamente");
             
             registrado=true;
             return registrado;
        
        } catch (SQLException e) {
            
            registrado=false;
            return registrado;
        }finally{
 
           try {
             if (instruccion != null) instruccion.close();
                if (sl != null) sl.close();    
            } catch (SQLException e) {
               System.out.println("Error de cerrar conexión BD");
            }
        } 
}
    
    public Boolean verifiacionLogin(String usuario,String contraseña){
        
        try {
         
            
         
         String query="SELECT * FROM user WHERE usuario = ? AND contraseña = ?";
        
         instruccion=sl.prepareStatement(query);
        
         instruccion.setString(1, usuario);
         instruccion.setString(2, contraseña);
     
             
        //guardamos el resultado de la busqueda
        resultado = instruccion.executeQuery(); 
        
        if (resultado.next()) {
        
            JOptionPane.showMessageDialog(null,"!Se Inicio de Sesion Correctamente!");
            this.logueado=true;
            return logueado;
        
        }else{
        
            JOptionPane.showMessageDialog(null,"!Error de Inicio de Sesion!");
            this.logueado=false;
            return logueado;                
        
        }
        
        
        } catch (SQLException e) {
        
            System.out.println("  !ERROR!  "+e);
        
        } finally {
            
            try {
             if(resultado != null ) resultado.close();
             if (instruccion != null) instruccion.close();
                if (sl != null) sl.close();    
                return logueado;
                
            } catch (SQLException e) {
               System.out.println("Error de cerrar conexión BD");
               return logueado;
            }
        }   
    }
    
    public static void main(String[] args) {
     Usuarios user=new Usuarios();
     
     
          //user.agregarLogin("Fran","franquito@gmail.com","123");
            
          user.verifiacionLogin("Fran","123");
     
    }
    
}

   
   
   

