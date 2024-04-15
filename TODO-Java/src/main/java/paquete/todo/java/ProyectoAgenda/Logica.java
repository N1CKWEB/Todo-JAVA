/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete.todo.java.ProyectoAgenda;

import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class Logica {
    
    
private String nombre,apellido,email;
    
Ventana panel=new Ventana();

private static ArrayList<Contactos> Agenda = new ArrayList<>();

    public Logica() {
    }

    public void agregar_contacto(String nombre, int telefono) {
        
       Agenda.add(new Contactos(nombre,telefono));
    }

    
    public String buscarContacto(String nombre){
        StringBuilder contactoBuscado = new StringBuilder();

        for (Contactos contacto : Agenda) {
            if (nombre.equalsIgnoreCase(contacto.getNombre())){
                int id = contacto.getId();
                String buscado = contacto.getNombre();
                int telefono = contacto.getTelefono();
                System.out.println(contacto.getCantidad());
                contactoBuscado.append("id: ").append(id).append(" Nombre: ").append(buscado).append(" Telefono: ").append(telefono).append("\n");
                String contactoBuscadoStr = contactoBuscado.toString();

                return contactoBuscadoStr;
            }else {
            }
        }
         return null;
    }
    
    public boolean borrarContacto(String nombre){
        boolean borrado = false;
        for (Contactos contacto : Agenda) {
            if (nombre.equalsIgnoreCase(contacto.getNombre())){
                int id=contacto.getId();
                Agenda.remove(id-1);
                borrado = true;
                return borrado;
            }
        }
        return borrado;
    }
    
    public String cantidadContactos(){
         for (Contactos contacto : Agenda) {
        int cantidad = contacto.getCantidad();
        String cantidadStr= Integer.toString(cantidad);
        return cantidadStr;
    }
    return null;
    }




    public String listaCompleta(){
        StringBuilder lista = new StringBuilder();
        for (Contactos contacto : Agenda) {
            int id = contacto.getId();
            String nombre = contacto.getNombre();
            int telefono = contacto.getTelefono();
            
            lista.append("id: ").append(id).append(" Nombre: ").append(nombre).append(" Telefono: ").append(telefono).append("\n");
        }
        String listaCompleta = lista.toString();
        return listaCompleta;
    }

    
}

   
    
    

