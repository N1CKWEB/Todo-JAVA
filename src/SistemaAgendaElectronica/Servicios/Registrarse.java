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
public class Registrarse {
  
    private PreparedStatement instruccion;
    private static java.sql.Connection sl;
     Boolean logueado = null;
    ResultSet resultado = null;
    Boolean registrado = null;
    boolean existe = false;
    Conexion conexion = new Conexion();
    

    public static void main(String[] args) {
        
    }
    
    
}

  