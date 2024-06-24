package SistemaAgendaElectronica.BD;

import SistemaAgendaElectronica.Servicios.Encriptado;
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
            String url = "jdbc:mysql://localhost:3306/app_sistema_agenda_electronica";
            String usuario = "root";
            String contraseña = "";
            sl = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos.");
            System.exit(1); // Salir del programa si no se puede conectar
        }
    }

    // METODOS DE USUARIO PARA REGISTRARSE
    public Boolean agregarRegistrarse(String nombredeusuarios, String correo, String contraseña) {
      
        try {
          
         Encriptado encriptar=new Encriptado();
         
        // 1. Encriptar la contraseña antes de guardarla
         String contraseñaEncriptada = encriptar.encriptarContraseña(contraseña);

        if (contraseñaEncriptada == null) {
            // Manejo de errores de encriptación
            registrado = false;
            return registrado;
        }

        String consulta = "INSERT INTO usuarios (nombredeusuarios, correo, contraseña) VALUES (?, ?, ?)";
        instruccion = sl.prepareStatement(consulta);
        
          System.out.println("La contraseña encriptada es: "+contraseña);
          
        instruccion.setString(1, nombredeusuarios);
        instruccion.setString(2, correo);
        instruccion.setString(3, contraseñaEncriptada); // Guardamos la contraseña encriptada

        instruccion.executeUpdate();
         
            registrado = true;
            return registrado;

            
        } catch (SQLException e) {
            registrado = false;
            return registrado;
        }
    }

    // METODOS DE USUARIO PARA VERIFICAR INICIO DE SESION
    public Boolean verificacionInicioDeSesion(String correo, String contraseña) {
        

        try {
            String query = "SELECT * FROM usuarios WHERE correo = ? AND contraseña = ?";
            instruccion = sl.prepareStatement(query);

            instruccion.setString(1, correo);
            instruccion.setString(2, contraseña);

            // guardamos el resultado de la busqueda
            resultado = instruccion.executeQuery();

            if (resultado.next()) {
                JOptionPane.showMessageDialog(null, "!Inicio de Sesion Correctamente!");
                this.logueado = true;
                return logueado;
            } else {
                JOptionPane.showMessageDialog(null, "!Error de Inicio de Sesion! ❌");
                this.logueado = false;
                return logueado;
            }

        } catch (SQLException e) {
            System.out.println("  !ERROR!  " + e);
            return false;
        }
    }

    // METODOS DE USUARIOS PARA VERIFICAR EL INICIO DE SESIÓN
    public boolean elCorreoEsValidoParaIniciarSesion(String correo) {
        String regex = "^[\\w-\\.]+@((gmail\\.com))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
          
        if (matcher.matches()) {
           JOptionPane.showMessageDialog(null, "El correo contiene lo pedido para Iniciar Sesión! ☑️");
        }else{
           JOptionPane.showMessageDialog(null, "El correo no es valido. Debe contener '@' y terminar 'gmail.com' ❌");
        }
        
        return matcher.matches();
    }

    // METODOS DE USUARIOS PARA VERIFICAR EL REGISTRARSE
    public boolean elCorreEsValidoParaRegistrarse(String correo) {
        String regex = "^[\\w-\\.]+@((gmail\\.com))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);

        if (correo.matches(regex)) {
            JOptionPane.showMessageDialog(null, "El correo es valido ☑");
        } else {
            JOptionPane.showMessageDialog(null, "El correo no es valido, no contiene lo pedido ❌");
        }
        return matcher.matches();
    }

    public boolean elNombreDeUSuarioEsValidoParaRegistrarse(String nombredeusuarios) {
        String regex = "^[a-zA-Z0-9]+$"; // Cambiado de zA-Z a a-zA-Z para incluir minúsculas
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombredeusuarios);

        if (nombredeusuarios.matches(regex)) {
            JOptionPane.showMessageDialog(null, "El usuario es valido ☑");
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no es valido, no contiene lo pedido ❌");
        }

        return matcher.matches();
    }

    

    public boolean correoExisteDeRegistrarse(String correo) {
        try {
            String consulta = "SELECT 1 FROM usuarios WHERE correo = ?";
            instruccion = sl.prepareStatement(consulta);

            instruccion.setString(1, correo);
            resultado = instruccion.executeQuery();

       if (resultado.next()) {
            System.out.println("El correo existe. ❌ ");
            return false;
        } else {
            System.out.println("El correo no existe. ☑");
            return true;
        }

        } catch (SQLException e) {
            System.out.println("EL error es: " + e);
            return false;
        }
    }

    public boolean nombreDeUSuarioExisteDeRegistrarse(String nombredeusuarios) {
        try {
            String consulta = "SELECT 1 FROM usuarios WHERE nombredeusuarios = ?";
            instruccion = sl.prepareStatement(consulta);

            instruccion.setString(1, nombredeusuarios);
            resultado = instruccion.executeQuery();

            if (resultado.next()) {
            System.out.println("El nombre de usuario existe. ❌ ");
            return false;
        } else {
            System.out.println("️ El nombre de usuario no existe. ☑");
            return true;
        }

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

    // MÉTODO PARA RECUPERAR CONTRASEÑA
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

        } catch (InvalidParameterException | SQLException | MessagingException ex) {
            System.out.println("No se pudo enviar el correo electrónico.");
            System.out.println("El error es: " + ex);
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    // MÉTODO PARA GENERAR CONTRASEÑA ALEATORIA NUEVA
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

    // MÉTODO PARA ACTUALIZAR CONTRASEÑA
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
        // Método main vacío

    }
}
