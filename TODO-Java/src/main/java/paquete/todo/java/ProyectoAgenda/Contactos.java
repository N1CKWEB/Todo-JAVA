/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete.todo.java.ProyectoAgenda;

/**
 *
 * @author LENOVO
 */
public class Contactos {
    
    
   private String nombre;
   private int telefono,id;

   private static int cantidad = 0;
    
    
    public Contactos (String nombre, int telefono){
        this.nombre=nombre;
         this.telefono=telefono;
         cantidad++;
         this.id=cantidad;
         System.out.println(getId());
         

    }


    public static int getCantidad() {
        return cantidad;
   }

    public static void setCantidad(int cantidad) {
        Contactos.cantidad = cantidad;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
