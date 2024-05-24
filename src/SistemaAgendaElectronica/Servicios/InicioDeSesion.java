/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaAgendaElectronica.Servicios;


import SistemaAgendaElectronica.BD.Conexion;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author LENOVO
 */
public class InicioDeSesion {
     private String usuario;
    private String contraseña;
    private String correo;

       private PreparedStatement instruccion;
    private static java.sql.Connection sl;
     Boolean logueado = null;
    ResultSet resultado = null;
    Boolean registrado = null;
    boolean existe = false;
    Conexion conexion = new Conexion();
    
    public InicioDeSesion(String usuario, char[] contraseña, String correo) {

        this.usuario = usuario;
        this.contraseña = new String(contraseña);
        this.correo = correo;
    }
    
       
    public InicioDeSesion(){
        
    }
    
    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario (String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    

     
     public static void main(String[] args) {
        
    }
    
    
}
