/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaAgendaElectronica.BD;

import SistemaAgendaElectronica.Servicios.EnviarCorreoElectronico;
import java.sql.ResultSet;
import java.security.InvalidParameterException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static final String[] caracteresEspeciales = "!@#$%^&*()_-+={}[]|;:<>,.?/~".split("");

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
    public Boolean agregarRegistrarse(String nombredeusuario, String correo, String contraseña) {

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

            instruccion.setString(1, nombredeusuario);
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

    public Boolean verificacionInicioDeSesion(String correo, String contraseña) {

        if (!elCorreoEsValidoParaIniciarSesion(correo)) {
            JOptionPane.showMessageDialog(null, "El correo no es valido. Debe contener '@' y terminar 'gmail.com'");
            return false;
        }

        try {

            String query = "SELECT * FROM usuarios WHERE correo = ? AND contraseña = ?";

            instruccion = sl.prepareStatement(query);

            instruccion.setString(1, correo);
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

    public boolean elCorreoEsValidoParaIniciarSesion(String correo) {
        String regex = "^[\\w-\\.]+@((gmail\\.com))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);

        return matcher.matches();
    }

    public boolean verificacionDeRegistrarse(String correo, String nombredeusuario, String contraseña) {

        // Verificar si el correo es válido
        if (!elCorreEsValidoParaRegistrarse(correo)) {
            JOptionPane.showMessageDialog(null, "El correo no es válido. Debe contener '@' y terminar en 'gmail.com'");
            return false;
        }

        // Verificar si el correo ya existe
        if (correoExisteDeRegistrarse(correo)) {
            JOptionPane.showMessageDialog(null, "El correo ya está registrado");
            return false;
        }

        // Verificar si el nombre de usuario ya existe
        if (nombreDeUSuarioExisteDeRegistrarse(nombredeusuario)) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario ya está registrado");
            return false;
        }

        // Verificar si el nombre de usuario ya existe
        if (contraseñaExisteDeRegistrarse(contraseña)) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario ya está registrado");
            return false;
        }

        try (PreparedStatement instruccion = sl.prepareStatement("SELECT 1 FROM usuarios WHERE correo = ? AND nombredeusuario = ? AND contraseña = ?")) {

            instruccion.setString(1, correo);
            instruccion.setString(2, nombredeusuario);
            instruccion.setString(3, contraseña);

            try (ResultSet resultado = instruccion.executeQuery()) {
                if (resultado.next()) {
                    JOptionPane.showMessageDialog(null, "El usuario con este correo, nombre de usuario y contraseña ya está registrado.");
                    return false;
                }
            }

            // Si no se encontró un usuario existente, se procede a registrarlo
            try (PreparedStatement insertInstruccion = sl.prepareStatement("INSERT INTO usuarios (correo, nombredeusuario, contraseña) VALUES (?, ?, ?)")) {

                insertInstruccion.setString(1, correo);
                insertInstruccion.setString(2, nombredeusuario);
                insertInstruccion.setString(3, contraseña);
                insertInstruccion.executeUpdate();
            }
            return true;

        } catch (SQLException e) {
            System.out.println("El error es: " + e);
            return false;
        }
    }

    public boolean elCorreEsValidoParaRegistrarse(String correo) {
        String regex = "^[\\w-\\.]+@((gmail\\.com))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    public boolean correoExisteDeRegistrarse(String correo) {
        try {
            String consulta = "SELECT 1 FROM usuarios WHERE correo = ?";
            instruccion = sl.prepareStatement(consulta);

            instruccion.setString(1, correo);
            resultado = instruccion.executeQuery();

            return resultado.next();

        } catch (SQLException e) {
            System.out.println("EL error es: " + e);
            return false;
        }

    }

    public boolean nombreDeUSuarioExisteDeRegistrarse(String nombredeusuario) {
        try {
            String consulta = "SELECT 1 FROM usuarios WHERE nombredeusuario = ?";
            instruccion = sl.prepareStatement(consulta);

            instruccion.setString(1, nombredeusuario);
            resultado = instruccion.executeQuery();

            return resultado.next();
        } catch (SQLException e) {
            System.out.println("EL error es: " + e);
            return false;
        }
    }

    public boolean contraseñaExisteDeRegistrarse(String contraseña) {

        try {
            String consulta = "SELECT 1 FROM usuarios WHERE contraseña = ?";
            instruccion = sl.prepareStatement(consulta);

            instruccion.setString(1, contraseña);
            resultado = instruccion.executeQuery();

            return resultado.next();

        } catch (SQLException e) {
            System.out.println("El error es: " + e);
            return false;
        }
    }

    public boolean recuperarContraseña(String correoDelDestinatario) {
        if (sl == null) {
            JOptionPane.showMessageDialog(null, "No hay conexión a la base de datos.");
            return false;
        }
        try {
            String consulta = "SELECT contraseña FROM usuarios WHERE correo = ?";
            instruccion = sl.prepareStatement(consulta);
            instruccion.setString(1, correoDelDestinatario);

            int longitud = 23;
            String nuevaContraseña = generarContraseñaAleatoria(longitud);

            actualizarContraseña(correoDelDestinatario, nuevaContraseña);

            String remitente = "nicolasdiazgarrido649@gmail.com";
            String password = "jddi rcfn vbdi cusb";

            EnviarCorreoElectronico correo = new EnviarCorreoElectronico(remitente, password);
            correo.enviarGmail("Recuperación de contraseña:", nuevaContraseña, correoDelDestinatario);

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

    public boolean actualizarContraseña(String correoDelDestinatario, String nuevaContraseña) {
        try {
            String consulta = "UPDATE usuarios SET contraseña = ? WHERE correo = ?";
            instruccion = sl.prepareStatement(consulta);
            instruccion.setString(1, nuevaContraseña);
            instruccion.setString(2, correoDelDestinatario);
            int modificacion = instruccion.executeUpdate();
            return modificacion > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar la contraseña: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        Usuarios user = new Usuarios();
        String nuevaContraseña = user.generarContraseñaAleatoria(10);
        user.recuperarContraseña("Mario");
        user.actualizarContraseña("Mario", nuevaContraseña);

        user.cerrarConexion();
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
