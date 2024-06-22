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
                this.logueado = false;
                return logueado;
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "!ERROR! y su error es: " + e);
            return false;
        }

    }

    //MÉTODO PARA MODIFICAR CONTACTO
    public boolean modificarContacto(String dni, String nombre, String apellido, String direccion, String localidad, String dniUsuario) {
        StringBuilder consulta = new StringBuilder("UPDATE contactosdeusuarios SET ");
        boolean primero = true;

        if (nombre != null && !nombre.isEmpty()) {
            consulta.append("nombre = ?");
            primero = false;
        }
        if (apellido != null && !apellido.isEmpty()) {
            if (!primero) {
                consulta.append(", ");
            }
            consulta.append("apellido = ?");
            primero = false;
        }
        if (direccion != null && !direccion.isEmpty()) {
            if (!primero) {
                consulta.append(", ");
            }
            consulta.append("direccion = ?");
            primero = false;
        }
        if (localidad != null && !localidad.isEmpty()) {
            if (!primero) {
                consulta.append(", ");
            }
            consulta.append("localidad = ?");
            primero = false;
        }
        if (dni != null && !dni.isEmpty()) {
            if (!primero) {
                consulta.append(", ");
            }
            consulta.append("dni = ?");
        }

        consulta.append(" WHERE dni = ?");

        PreparedStatement instruccion = null;
        boolean actualizado = false;

        try {
            instruccion = sl.prepareStatement(consulta.toString());
            int index = 1;

            if (nombre != null && !nombre.isEmpty()) {
                instruccion.setString(index++, nombre);
            }
            if (apellido != null && !apellido.isEmpty()) {
                instruccion.setString(index++, apellido);
            }
            if (direccion != null && !direccion.isEmpty()) {
                instruccion.setString(index++, direccion);
            }
            if (localidad != null && !localidad.isEmpty()) {
                instruccion.setString(index++, localidad);
            }
            if (dni != null && !dni.isEmpty()) {
                instruccion.setString(index++, dni);
            }

            instruccion.setString(index, dniUsuario);

            int filasAfectadas = instruccion.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Contacto modificado correctamente.");
                actualizado = true;
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un contacto con el DNI especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar el contacto: " + e.getMessage());
        } finally {
            if (instruccion != null) {
                try {
                    instruccion.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el PreparedStatement: " + e.getMessage());
                }
            }
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
//MÉTODO PARA CONSULTAR TODO LOS CONTACTOS

    public boolean consultarTodosLosContactos() {
        try {
            String consulta = "SELECT * FROM contactosdeusuarios";
            instruccion = sl.prepareStatement(consulta);
            resultado = instruccion.executeQuery();

            if (resultado.next()) {
                JOptionPane.showMessageDialog(null, "Su consulta de todos los contactos fue correcta ☑");
                this.logueado = true;
                return logueado;
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron contactos ❌");
                this.logueado = false;
                return logueado;
            }
        } catch (SQLException e) {
            System.out.println("El error es: " + e);
            this.logueado = false;
            return logueado;
        }
    }

    //MÉTODO PARA ELIMINAR TODO LOS CONTACTOS
    public boolean eliminarTodosLosContactos() {
        try {
            String consulta = "DELETE FROM contactosdeusuarios";
            instruccion = sl.prepareStatement(consulta);

            int filasAfectadas = instruccion.executeUpdate();
            if (filasAfectadas > 0) {
                eliminado = true;
                JOptionPane.showMessageDialog(null, "Se eliminaron correctamente todos los contactos ☑️");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar a todos los contactos ❌");
                eliminado = false;
            }
            return eliminado;
        } catch (SQLException e) {
            System.out.println("El error es: " + e);
            return false;
        }
    }

    //Método para verificar todos los campos de la agenda si están bien o no.
    public boolean verificacionDeContacto(String dni, String nombre, String apellido, String direccion, String localidad) {

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

        Pattern patron = Pattern.compile("^\\d{7,8}$");
        Matcher matcher = patron.matcher(dni);

        if (matcher.matches()) {
            JOptionPane.showMessageDialog(null,"Correcto el DNI ☑️");
        } else {
            JOptionPane.showMessageDialog(null,"Incorrecto el DNI ❌");
        }
        return matcher.matches();
    }

    public boolean elNombreEsValidoParaContacto(String nombre) {

        String regex = ".*[a-zA-Z].*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombre);

        if (matcher.matches()) {
            JOptionPane.showMessageDialog(null,"Correcto el Nombre ☑️");
        } else {
            JOptionPane.showMessageDialog(null,"Incorrecto el Nombre ❌");
        }
        return matcher.matches();

    }

    public boolean elApellidoEsValidoParaContacto(String apellido) {
        String regex = ".*[a-zA-Z].*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(apellido);

        if (matcher.matches()) {
            JOptionPane.showMessageDialog(null,"Correcto el Apellido ☑️");
        } else {
            JOptionPane.showMessageDialog(null,"Incorrecto el Apellido ❌");
        }
        return matcher.matches();
    }

    public boolean laDireccionEsValidaParaContacto(String direccion) {

        // Verificar presencia de números y letras
        String regex = "^(?=.*[0-9])(?=.*[a-zA-Z]).*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(direccion);

        if (matcher.matches()) {
            JOptionPane.showMessageDialog(null,"Correcta la dirección ☑️");
        } else {
            JOptionPane.showMessageDialog(null,"Incorrecta la dirección ❌");
        }
        return matcher.matches();
    }

    public boolean laLocalidadEsValidaParaContacto(String localidad) {

        String regex = "^[a-zA-Z\s]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(localidad);

        if (matcher.matches()) {
            JOptionPane.showMessageDialog(null,"Correcta la localidad ☑️");
        } else {
            JOptionPane.showMessageDialog(null,"Incorrecta la dirección ❌");
        }
        return matcher.matches();
    }

    public boolean elDniExisteParaContacto(String dni) {

        try {
            String consulta = "SELECT 1 FROM contactosdeusuarios WHERE dni = ?";

            instruccion = sl.prepareStatement(consulta);
            instruccion.setString(1, dni);

            resultado = instruccion.executeQuery();

            if (resultado.next()) {
                System.out.println("El DNI existe. ❌ ");
                return false;
            } else {
                System.out.println("El DNI no existe. ☑");
                return true;
            }

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
            
            if (resultado.next()) {
                System.out.println("La dirreción existe. ❌ ");
                return false;
            } else {
                System.out.println("La dirección no existe. ☑");
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println("El error es: " + e);
            return false;
        }
    }

    public static void main(String[] args) {

    }

}
