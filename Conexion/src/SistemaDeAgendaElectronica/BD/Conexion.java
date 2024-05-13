package SistemaDeAgendaElectronica.BD;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Conexion {

    String url = "jdbc:mysql://localhost:3306/sistema_agenda_electronica";
    String user = "BDNico";
    String password = "";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection cx;

    public Conexion(Connection cx) {
        this.cx = cx;
    }

    public Conexion() {

    }

    public Connection conectar() {

        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url, user, password);

            JOptionPane.showMessageDialog(null, "SE CONECTO A BD");
          
        } catch (ClassNotFoundException | SQLException e) {

            JOptionPane.showMessageDialog(null, "NO CONECTO A BD");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);

        }
        return cx;
    }

    public void desconectar() {
        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static void main(String[] args) {
        
        Conexion conexion = new Conexion();
        conexion.conectar();
        
        
    }
}