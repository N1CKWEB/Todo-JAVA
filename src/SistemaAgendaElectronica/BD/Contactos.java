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

    //METODOS DE CONTACTOS (CRUD)
    public Boolean agregarContactos(String dni, String nombre, String apellido, String correo, String direccion, String localidad) {

        try {

            String consulta = "INSERT INTO contactosdeusuarios (dni,nombre,apellido,correo,direccion,localidad) VALUES (? , ? , ? , ? , ? , ?)";

            instruccion = sl.prepareStatement(consulta);

            instruccion.setString(1, dni);
            instruccion.setString(2, nombre);
            instruccion.setString(3, apellido);
            instruccion.setString(4, correo);
            instruccion.setString(5, direccion);
            instruccion.setString(6, localidad);

            instruccion.executeUpdate();

            System.out.println("Se creo correctamente");

            registrado = true;
            return registrado;

        } catch (SQLException e) {
            System.out.println("" + e);

            registrado = false;
            return registrado;

        } finally {

            try {
                if (instruccion != null) {
                    instruccion.close();
                }
                if (sl != null) {
                    sl.close();
                }

            } catch (SQLException e) {
                System.out.println("No sé cerro la BD, y el error es: " + e);
            }
        }
    }

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
        } finally {

            try {

                if (resultado != null) {
                    resultado.close();
                }
                if (instruccion != null) {
                    instruccion.close();
                }
                if (sl != null) {
                    sl.close();
                }

                return logueado;

            } catch (SQLException e) {

                System.out.println("!Error de cerrar BD!" + e);
                return logueado;

            }
        }
    }

    public boolean modificarContacto(String dni, String nombre, String apellido, String correo, String direccion, String localidad, String dniUsuario) {

        String consulta = "UPDATE contactosdeusuarios SET nombre = ?, apellido = ?, correo = ?, direccion = ?, localidad = ?, dni= ? WHERE dni = ?";

        PreparedStatement instruccion = null;
        boolean actualizado = false;

        try {
            instruccion = sl.prepareStatement(consulta);
            instruccion.setString(1, nombre);
            instruccion.setString(2, apellido);
            instruccion.setString(3, correo);
            instruccion.setString(4, direccion);
            instruccion.setString(5, localidad);
            instruccion.setString(6, dni);
            instruccion.setString(7, dniUsuario);

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

    public boolean verificacionDeContacto(String dni, String correo) {

        if (!elDniEsValidoParaContacto(dni)) {
            JOptionPane.showMessageDialog(null, "El dni no es valido, no contiene entre 8 y 9 dígitos");
            return false;
        }

        if (!elCorreoEsValidoParaContacto(correo)) {
            JOptionPane.showMessageDialog(null, "El correo no es valido, no contiene '@' y el 'gmail.com' ");
            return false;
        }
        if (elCorreoExisteParaContacto(correo)) {
            JOptionPane.showMessageDialog(null, "El correo ya existe en contactos");
            return false;
        }

        if (elDniExisteParaContacto(dni)) {
            JOptionPane.showMessageDialog(null, "El dni ya existe en contacto");
            return false;
        }

        try {
            String consulta = "SELECT 1 FROM contactosdeusuarios WHERE dni = ? AND correo = ?";

            instruccion = sl.prepareStatement(consulta);

            instruccion.setString(1, dni);
            instruccion.setString(2, correo);

            try (ResultSet resultado = instruccion.executeQuery()) {
                if (resultado.next()) {
                    JOptionPane.showMessageDialog(null, "El usuario con este correo, nombre de usuario y contraseña ya está registrado.");
                    return false;
                }
            }
            // Si no se encontró un usuario existente, se procede a registrarlo
            try (PreparedStatement insertInstruccion = sl.prepareStatement("INSERT INTO contactosdeusuario (dni , correo) VALUES (? , ? ")) {

                insertInstruccion.setString(1, dni);
                insertInstruccion.setString(2, correo);
                insertInstruccion.executeUpdate();
            }
            return true;

        } catch (SQLException e) {
            System.out.println("El error es: " + e);
            return false;
        }
    }

    public boolean elDniEsValidoParaContacto(String dni) {

        if (dni.length() == 8 || dni.length() == 9) {
            JOptionPane.showMessageDialog(null, "El dni es valido");

        }
        return true;
    }

    public boolean elCorreoEsValidoParaContacto(String correo) {
        String regex = "^[\\w-\\.]+@((gmail\\.com))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);

        return matcher.matches();
    }

    public boolean elCorreoExisteParaContacto(String correo) {

        try {
            String consulta = "SELECT 1 FROM contactosdeusuarios WHERE correo = ?";

            instruccion = sl.prepareStatement(consulta);

            instruccion.setString(1, correo);

            resultado = instruccion.executeQuery();

            return resultado.next();
        } catch (SQLException e) {
            System.out.println("El error es: " + e);
            return false;
        }
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

    public static void main(String[] args) {

        // Contactos contactos = new Contactos();
        //contactos.agregarContactos("46327750","Nicolás","Díaz","nicolasdiazgarrido@gmail.com","Luzuariga 8421","Maipu");
        //contactos.consultarContacto("46327750","Nicolás");
        //contactos.modificarContacto("46327750", "Emilio", "Díaz", "nicolaszgarrido649@gmail.com", "Luzuariga 3319 23", "Acongua 111");
        //contactos.eliminarContacto("46327750", "Emilio");
    }

}
