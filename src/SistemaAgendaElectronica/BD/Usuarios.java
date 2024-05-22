/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaAgendaElectronica.BD;

import SistemaAgendaElectronica.Servicios.EnviarCorreoElectronico;
import java.sql.ResultSet;
 import java.io.IOException;
import java.security.InvalidParameterException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;

public class Usuarios {
    
    private PreparedStatement instruccion;
    private static java.sql.Connection sl;
     Boolean logueado = null;
    ResultSet resultado = null;
    Boolean registrado = null;
    boolean existe = false;
    Conexion conexion = new Conexion();
    private static final String[] mayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    private static final String[] minusculas = "abcdefghijklmnopqrstuvwyz".split("");  
    private static final String[] numeros = "123456789101112".split("");
    private static final String[] caracteresEspeciales="!@#$%^&*()_-+={}[]|;:<>,.?/~".split("");

    
    
    
    public Usuarios() {
         try {
            String url = "jdbc:mysql://localhost:3306/sistema_agenda_electronica";
            String usuario = "root";
            String contraseña = "";
            sl = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos.");
            System.exit(1); // Salir del programa si no se puede conectar
        }
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
public boolean recuperarContraseña(String nombredeusuario) {
        if (sl == null) {
            JOptionPane.showMessageDialog(null, "No hay conexión a la base de datos.");
            return false;
        }
        try {
            String consulta = "SELECT contraseña FROM usuarios WHERE nombredeusuario = ?";
            instruccion = sl.prepareStatement(consulta);
            instruccion.setString(1, nombredeusuario);

            int longitud=23;
            String nuevaContraseña = generarContraseñaAleatoria(longitud);
            actualizarContraseña(nombredeusuario, nuevaContraseña);

            String remitente = "nicolasdiazgarrido649@gmail.com"; 
            String password = "jddi rcfn vbdi cusb";

            EnviarCorreoElectronico correo = new EnviarCorreoElectronico(remitente, password);
            correo.enviarGmail("Recuperación de contraseña:", nuevaContraseña, "marilidiagarrido10@gmail.com"); 

            JOptionPane.showMessageDialog(null, "Se envió con éxito el correo electrónico.");
            JOptionPane.showMessageDialog(null, "Contraseña recuperada exitosamente!");
        } catch (InvalidParameterException | SQLException | MessagingException ex) {
            System.out.println("No se pudo enviar el correo electrónico.");
            System.out.println("El error es: " + ex);
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public String generarContraseñaAleatoria(int longitud) {
        Random random = new Random();
        StringBuilder contraseña = new StringBuilder();
        char[] mayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] minusculas = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] numeros = "0123456789".toCharArray();
        char[] caracteresEspeciales = "!@#$%^&*()_+-=".toCharArray();

        for (int i = 0; i < longitud; i++) {
            int grupo = random.nextInt(4);

            switch (grupo) {
                case 0:
                    contraseña.append(mayusculas[random.nextInt(mayusculas.length)]);
                    break;
                case 1:
                    contraseña.append(minusculas[random.nextInt(minusculas.length)]);
                    break;
                case 2:
                    contraseña.append(numeros[random.nextInt(numeros.length)]);
                    break;
                case 3:
                    contraseña.append(caracteresEspeciales[random.nextInt(caracteresEspeciales.length)]);
                    break;
            }
        }
        contraseña.append("#");
        return contraseña.toString();
    }

    public boolean actualizarContraseña(String nombredeusuario, String nuevaContraseña) {
        try {
            String consulta = "UPDATE usuarios SET contraseña = ? WHERE nombredeusuario = ?";
            instruccion = sl.prepareStatement(consulta);
            instruccion.setString(1, nuevaContraseña);
            instruccion.setString(2, nombredeusuario);
            int modificacion = instruccion.executeUpdate();
            return modificacion > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar la contraseña: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
//        Usuarios user = new Usuarios();
//        String nuevaContraseña = user.generarContraseñaAleatoria(10);
//        user.recuperarContraseña("Mario");
//        user.actualizarContraseña("Mario", nuevaContraseña);
//
//        user.cerrarConexion();
    }

    public void cerrarConexion() {
        try {
            if (sl != null && !sl.isClosed()) {
                sl.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    
    }
}