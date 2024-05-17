package SistemaDeAgendaElectronica.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Contactos {
    Boolean logueado = null;
    PreparedStatement instruccion = null;
    ResultSet resultado = null;
    Boolean registrado=null;
    Conexion conexion=new Conexion();
    Connection sl=conexion.conectar();


public Boolean agregarContactos(String dni,String nombre, String apellido,String correo, String direccion, String localidad){
    
    
   try {
   
       String consulta="INSERT INTO contactos (dni,nombre,apellido,correo,direccion,localidad) VALUES (? , ? , ? , ? , ? , ?)";
     
    instruccion=sl.prepareStatement(consulta);
    
     instruccion.setString(1,dni);
     instruccion.setString(2, nombre);
     instruccion.setString(3, apellido);
     instruccion.setString(4, correo);
     instruccion.setString(5, direccion);
     instruccion.setString(6,localidad);    


      instruccion.executeUpdate();
      
       System.out.println("Se creo correctamente");
       
       registrado=true;
       return registrado;
    
    } catch (SQLException e) {
       System.out.println(""+e);
       
       registrado=false;
       return registrado;
       
    } finally {
       
       try {
           if (instruccion != null) instruccion.close();
               if (sl != null) sl.close();
           
       } catch (SQLException e) {
           System.out.println("No sé cerro la BD, y el error es: "+e);
       }
    }       
 }

  public Boolean verificacionContacto(String nombre, String apellido){
     
      
      try {
       
      String query="SELECT * FROM contactos WHERE nombre = ? AND apellido = ?";
      instruccion=sl.prepareStatement(query);    
      
      instruccion.setString(1, nombre);
      instruccion.setString(2, apellido);
      
      resultado=instruccion.executeQuery();
      
          if (resultado.next()) {
              JOptionPane.showMessageDialog(null,"Se inicio de correctamente!");
              this.logueado=true;
              return logueado;
          }else{
          
              JOptionPane.showMessageDialog(null,"!Error de Inicio de Sesion!");
              this.logueado=false;
              return logueado;
          }
      
      
      } catch (SQLException e) {
          
          System.out.println("!ERROR!"+e);
          
      }finally{
          
          try {
             
             if(resultado != null ) resultado.close();
             if (instruccion != null) instruccion.close();
             if (sl != null) sl.close();    
             
             return logueado;
              
          } catch (SQLException e) {
 
              System.out.println("!Error de inicio de sesion!"+e);
              return logueado;
          
          }
      }
   }
  
    public static void main(String[] args) {
     
        Contactos contactos=new Contactos();
         
        //contactos.agregarContactos("46327750","Nicolás","Díaz","nicolasdiazgarrido@gmail.com","Luzuariga 8421","Maipu");
        contactos.verificacionContacto("Nicolás", "Díaz");
    }
  
  
}
 