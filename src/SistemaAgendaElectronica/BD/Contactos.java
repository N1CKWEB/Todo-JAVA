package SistemaAgendaElectronica.BD;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Contactos {

    Boolean logueado = null;
    PreparedStatement instruccion;
    ResultSet resultado = null;
    Boolean registrado = null;
    Conexion conexion = new Conexion();
    Connection sl = conexion.conectar();
    boolean eliminado = false;
    public Contactos() {

    }

    //METODOS DE CONTACTOS (CRUD)
    
    //MÉTODO PARA AGREGAR CONTACTOS y para agregar contactos a la base de datos
    

    public boolean agregarContactos(String dni, String nombre, String apellido, String direccion, String localidad) {
        if (dni == null || dni.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || elDniExisteParaContacto(dni)) {
            JOptionPane.showMessageDialog(null, dni == null || dni.trim().isEmpty() ? "El DNI es obligatorio." : "El DNI ya está registrado.");
            return false;
        }

        try (PreparedStatement instruccion = sl.prepareStatement("INSERT INTO contactosdeusuarios (dni, nombre, apellido, direccion, localidad) VALUES (?, ?, ?, ?, ?)")) {
            instruccion.setString(1, dni);
            instruccion.setString(2, nombre);
            instruccion.setString(3, apellido);
            instruccion.setString(4, direccion);
            instruccion.setString(5, localidad);
            instruccion.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se creó el contacto correctamente.");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el contacto: " + e.getMessage());
            return false;
        }
    }

    //MÉTODO PARA CONSULTAR CONTACTOS
    public Boolean consultarContacto(String dni, String nombre) {

        try {

            String consulta = "SELECT * FROM contactosdeusuarios WHERE dni = ? AND nombre = ?";
            instruccion = sl.prepareStatement(consulta);

            instruccion.setString(1, dni);
            instruccion.setString(2, nombre);

            resultado = instruccion.executeQuery();

            if (resultado.next()) {

                JOptionPane.showMessageDialog(null, "Su consulta fue correctamente! "
                        + "El dni es: " + dni + " y el nombre es: " + nombre);
                this.logueado = true;
                return logueado;
            } else {

                JOptionPane.showMessageDialog(null, "!Su consulta no existe!");
                this.logueado = true;
                return logueado;
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "!ERROR! y su error es: " + e);
           return false;
        }
        
    }
    
    //MÉTODO PARA MODIFICAR CONTACTOS
    public boolean modificarContacto(String dni, String nombre, String apellido,String direccion, String localidad, String dniUsuario) {

        String consulta = "UPDATE contactosdeusuarios SET nombre = ?, apellido = ?, direccion = ?, localidad = ?, dni= ? WHERE dni = ?";

        PreparedStatement instruccion = null;
        boolean actualizado = false;

        try {
            instruccion = sl.prepareStatement(consulta);
            instruccion.setString(1, nombre);
            instruccion.setString(2, apellido);
            instruccion.setString(3, direccion);
            instruccion.setString(4, localidad);
            instruccion.setString(5, dni);
            instruccion.setString(6, dniUsuario);

            int filasAfectadas = instruccion.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Contacto modificado correctamente.");
                actualizado = true;
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un contacto con el DNI especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar el contacto: " + e.getMessage());
        }

        return actualizado;
    }
    
  //MÉTODO PARA ELIMINAR CONTACTOS
    public boolean eliminarContacto(String dni, String nombre) {

        try {

            String consulta = "DELETE FROM contactosdeusuarios WHERE dni = ? AND nombre = ? ";

            instruccion = sl.prepareStatement(consulta);

            instruccion.setString(1, dni);
            instruccion.setString(2, nombre);

            int filasAfectadas = instruccion.executeUpdate();

            if (filasAfectadas > 0) {
                eliminado = true;
                JOptionPane.showMessageDialog(null, "Se elimino correctamente el usuario!");
            } else {
                JOptionPane.showMessageDialog(null, "No sé encontro el dni y el nombre especificado");
            }

        } catch (HeadlessException | SQLException e) {

            System.out.println("ERROR!" + e);
        }
        return eliminado;
    }
    //Método para verificar todos los campos de la agenda si están bien o no.
  public boolean verificacionDeContacto(String dni, String nombre, String apellido, String direccion, String localidad) {
    // Validar el formato del DNI
    if (!elDniEsValidoParaContacto(dni)) {
        JOptionPane.showMessageDialog(null, "El dni no es válido, debe contener entre 8 y 9 dígitos ❌");
        return false;
    }

    // Validar el nombre
    if (!elNombreEsValidoParaContacto(nombre)) {
        JOptionPane.showMessageDialog(null, "El nombre no es válido, debe contener letras ❌");
        return false;
    }

    // Validar el apellido
    if (!elApellidoEsValidoParaContacto(apellido)) {
        JOptionPane.showMessageDialog(null, "El apellido no es válido, debe contener letras ❌");
        return false;
    }

    // Validar la dirección
    if (!laDireccionEsValidaParaContacto(direccion)) {
        JOptionPane.showMessageDialog(null, "La dirección no es válida, debe contener letras y números ❌");
        return false;
    }

    // Validar la localidad
    if (!laLocalidadEsValidaParaContacto(localidad)) {
        JOptionPane.showMessageDialog(null, "La localidad no es válida, debe contener letras ❌");
        return false;
    }

    // Verificar si el DNI ya existe en la base de datos
    if (elDniExisteParaContacto(dni)) {
        JOptionPane.showMessageDialog(null, "El DNI ya está registrado ❌");
        return false;
    }

    // Verificar si la dirección ya existe en la base de datos
    if (laDireccionExisteParaContacto(direccion)) {
        JOptionPane.showMessageDialog(null, "La dirección ya está registrada ❌");
        return false;
    }

    // Si todas las validaciones pasan, se procede a agregar el contacto a la base de datos
    try {
        // Se verifica nuevamente si el usuario existe en la base de datos (por seguridad)
        String consulta = "SELECT 1 FROM contactosdeusuarios WHERE dni = ?";
        instruccion = sl.prepareStatement(consulta);
        instruccion.setString(1, dni);

        try (ResultSet resultado = instruccion.executeQuery()) {
            if (resultado.next()) {
                JOptionPane.showMessageDialog(null, "El usuario ya está registrado ❌");
                return false;
            }
        }

        // Si no se encontró un usuario existente, se procede a registrarlo
        try (PreparedStatement insertInstruccion = sl.prepareStatement("INSERT INTO contactosdeusuarios (dni, nombre, apellido, direccion, localidad) VALUES (?, ?, ?, ?, ?)")) {
            insertInstruccion.setString(1, dni);
            insertInstruccion.setString(2, nombre);
            insertInstruccion.setString(3, apellido);
            insertInstruccion.setString(4, direccion);
            insertInstruccion.setString(5, localidad);
            insertInstruccion.executeUpdate();
        }

        return true;

    } catch (SQLException e) {
        System.out.println("Error al registrar el contacto: " + e.getMessage());
        return false;
    }
}


    public boolean elDniEsValidoParaContacto(String dni) {

        if (dni.length() == 8 || dni.length() == 9) {
            JOptionPane.showMessageDialog(null, "El dni contiene entre 8 y 9 dígitos ☑️");

        }
        return true;
    }


    public boolean elNombreEsValidoParaContacto(String nombre) {
        
        
          String regex = ".*[a-zA-Z].*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombre);

        JOptionPane.showMessageDialog(null, "El nombre contiene letras ☑️");

        return matcher.matches();
        
    }

    public boolean elApellidoEsValidoParaContacto(String apellido) {
      String regex = ".*[a-zA-Z].*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(apellido);

        JOptionPane.showMessageDialog(null, "El apellido contiene letras ☑️");

        return matcher.matches();      
    }

    public boolean laDireccionEsValidaParaContacto(String direccion) {
    // Verificar presencia de números y letras
    if (!direccion.matches(".*[0-9].*") || !direccion.matches(".*[a-zA-Z].*")) {
       JOptionPane.showMessageDialog(null,"La dirección contiene letras y números ☑️");
    }
           return true;
    }

    public boolean laLocalidadEsValidaParaContacto(String localidad) {
        if(localidad.matches("[a-zA-Z\s]+") && !localidad.matches(".*[0-9].*")){
         JOptionPane.showMessageDialog(null, "La localidad contiene letras ☑");
        }
        return true;
    }


    public boolean elDniExisteParaContacto(String dni) {

        try {
            String consulta = "SELECT 1 FROM contactosdeusuarios WHERE dni = ?";

            instruccion = sl.prepareStatement(consulta);
            instruccion.setString(1, dni);

            resultado = instruccion.executeQuery();

            return resultado.next();
        } catch (SQLException e) {
            System.out.println("El error es: " + e);
            return false;
        }
    }

    public boolean laDireccionExisteParaContacto(String direccion) {
        try {
            String consulta = "SELECT 1 FROM contactosdeusuarios WHERE direccion = ?";

            instruccion = sl.prepareStatement(consulta);
            instruccion.setString(1, direccion);

            resultado = instruccion.executeQuery();

            return resultado.next();
        } catch (SQLException e) {
            System.out.println("El error es: " + e);
            return false;
        }
    }

    public static void main(String[] args) {

    }

}
