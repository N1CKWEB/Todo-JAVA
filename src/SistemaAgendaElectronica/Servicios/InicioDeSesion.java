/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaAgendaElectronica.Servicios;

/**
 *
 * @author LENOVO
 */
public class InicioDeSesion {
     private String usuario;
    private String contraseña;
    private String correo;

    
    public InicioDeSesion(String usuario, char[] contraseña, String correo) {

        this.usuario = usuario;
        this.contraseña = new String(contraseña);
        this.correo = correo;
    }
    
    
    public InicioDeSesion(String user, String pass, String correo) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.correo = correo;
    }
    
    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario (String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    
}
