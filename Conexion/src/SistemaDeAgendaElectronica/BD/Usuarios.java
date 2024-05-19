/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaDeAgendaElectronica.BD;

import SistemaDeAgendaElectronica.GUI.Ventana1;
import java.sql.Connection;

//Usuarios
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Usuarios {

    Boolean logueado = null;
    PreparedStatement instruccion = null;
    ResultSet resultado = null;
    Boolean registrado = null;
    boolean existe = false;
    Conexion conexion = new Conexion();
    Connection sl = conexion.conectar();

    public Usuarios() {
    }

    
    //METODOS DE USUARIO PARA INICIAR SESION Y REGISTRARSE
    
    public Boolean agregarRegistrarse(String 	nombredeusuario, String correo, String contraseña) {
  
//    // Comprobar si el nombre de usuario ya existe
//    if (existeNombreUsuario(nombredeusuario)) {
//        JOptionPane.showMessageDialog(null, "El nombre de usuario ya está en uso. Por favor, elige otro nombre.");
//        return false;
//    }
//
//    // Comprobar si el correo ya existe
//    if (existeCorreo(correo)) {
//        JOptionPane.showMessageDialog(null, "El correo electrónico ya está en uso. Por favor, utiliza otro correo.");
//        return false;
//    }
        try {

            String consulta = "INSERT INTO usuarios (nombredeusuario,correo,contraseña) VALUES (? , ? , ?)";
            instruccion = sl.prepareStatement(consulta);

            instruccion.setString(1,nombredeusuario);
            instruccion.setString(2, correo);
            instruccion.setString(3, contraseña);

            instruccion.executeUpdate();

            System.out.println("Se creo correctamente");

            registrado = true;
            return registrado;

        } catch (SQLException e) {

            registrado = false;
            return registrado;
        } 
    }

    public Boolean verificacionInicioDeSesion(String nombredeusuario, String contraseña) {

        try {

            String query = "SELECT * FROM usuarios WHERE nombredeusuario = ? AND contraseña = ?";

            instruccion = sl.prepareStatement(query);

            instruccion.setString(1, nombredeusuario);
            instruccion.setString(2, contraseña);

            //guardamos el resultado de la busqueda
            resultado = instruccion.executeQuery();

            if (resultado.next()) {

                JOptionPane.showMessageDialog(null, "!Se Inicio de Sesion Correctamente!");
                this.logueado = true;
                return logueado;

            } else {

                JOptionPane.showMessageDialog(null, "!Error de Inicio de Sesion!");
                this.logueado = false;
                return logueado;

            }

        } catch (SQLException e) {

            System.out.println("  !ERROR!  " + e);
            return false;
        } 
    }

    public boolean existeNombreUsuario(String 	nombredeusuario) {
    String consulta = "SELECT COUNT(*) FROM usuarios WHERE nombredeusuario = ?";
    boolean existe = false;

    try (
         PreparedStatement ps = sl.prepareStatement(consulta)) {
        ps.setString(1,nombredeusuario);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        }
                System.out.println("Si existe el usuario: "+nombredeusuario+" use otro usuario por favor");

    } catch (SQLException e) {
        System.out.println("El error es:"+e);
    }

    return existe;
}
    public boolean existeCorreo (String correo) {
    String consulta = "SELECT COUNT(*) FROM usuarios WHERE correo = ?";
    boolean existe = false;

    try (
         PreparedStatement ps = sl.prepareStatement(consulta)) {
        ps.setString(1, correo);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        }
        System.out.println("Si existe el correo: "+correo+" use otro correo por favor");
    } catch (SQLException e) {
        System.out.println("El error es:"+e);
    }

    return existe;
}

    
    

    public static void main(String[] args) {
        Usuarios user = new Usuarios();
        Ventana1 v1=new Ventana1();
        
        v1.setVisible(true);
        v1.setLocationRelativeTo(null);
        
        
//        user.agregarRegistrarse("Enrique","franquito@gmail.com","123");
       // user.existeNombreUsuario("Luis");
       // user.existeCorreo("franquito@gmail.com");
     }

}
