package SistemaAgendaElectronica.BD;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Contactos {

    Boolean logueado = null;
    PreparedStatement instruccion = null;
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

public boolean modificarContacto(String dni, String nombre, String apellido, String correo, String direccion, String localidad) {

    String consulta = "UPDATE contactosdeusuarios SET nombre = ?, apellido = ?, correo = ?, direccion = ?, localidad = ? WHERE dni = ?";
  
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

    public static void main(String[] args) {

        Contactos contactos = new Contactos();

        //contactos.agregarContactos("46327750","Nicolás","Díaz","nicolasdiazgarrido@gmail.com","Luzuariga 8421","Maipu");
        //contactos.consultarContacto("46327750","Nicolás");
        contactos.modificarContacto("46327750", "Emilio", "Díaz", "nicolaszgaxxxrrido649@gmail.com", "Luzuariga 3319 23", "Acongua 111");
        //contactos.eliminarContacto("46327750", "Emilio");
    }

}
