/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Relaciones;

import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class Comprobante {

    private char tipo;
    private int numero;
    private Fecha fecha;

    //Constructor lleno
    public Comprobante(int numero, char tipo) {
        this.tipo = tipo;
        this.numero = numero;

    }
   
//Constructor vacio 
    public Comprobante() {

    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char val) {
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int val) {
        this.numero = numero;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha val) {
        this.fecha = fecha;
    }

    public static class Factura extends Comprobante {

        private float total;
     private Cliente cliente;
        //Constructor lleno
        public Factura(float total) {
            super();
            this.total = total;
        }
        
       //Constructor vacio
       public Factura(){
           
       }
 
        
        public Cliente getCliente(){
            
            return cliente;
            
        }
        public void setCliente(Cliente val){
            
        }
        
        public float getTotal() {
            return total;
        }

        public void setTotal(float val) {

        }
        
    }

    public static class Fecha extends Comprobante {

        private int dia;
        private int mes;
        private int anio;

        //Constructor lleno
        public Fecha(int dia,int mes,int anio) {
             this.dia=dia;
             this.mes=mes;
             this.anio=anio;
        }
        //Constructor vacio
        public Fecha(){
            
        }
    public int getDia(){
        return dia;
    }
    
    public void setDia(int val){
        
    }
    public int getMes(){
        return mes;
    }
        
    public void setMes(int val){
        
    }
    public int getAnio(){
        return anio;
    }
        
    public void setAnio(int val){
        
    }
    }

    public static class Cliente extends Comprobante {

        private int codigo;
        private String razonSocial;

        //Constructor lleno
        public Cliente(int codigo, String razonSocial) {
            this.codigo = codigo;
            this.razonSocial = razonSocial;
        }
        //Constructor vacio
        public Cliente(){
            
        }
        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int val) {

        }

        public String getRazonSocial() {
            return razonSocial;
        }

        public void setRazonSocial(String val) {

        }

    }

    public static class Producto extends Comprobante {

        private int codigo;
        private String descripcion;
        private float precio;
      
        //Constructor lleno
        public Producto(int codigo, String descripcion, float precio) {
            this.codigo = codigo;
            this.descripcion = descripcion;
            this.precio = precio;
        }
        //Constructor vacio
        public Producto(){
            
        }

        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int val) {

        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String val) {

        }

        public float getPrecio() {
            return precio;
        }

        public void setPrecio(float val) {

        }
    }

    public static void mostrarInformacion(){
        Comprobante comprobante=new Comprobante(0012233,'F');
        Factura factura=new Factura(1250);
        Producto producto=new Producto(433456,"Paquete de galletas de OREO", (float) 49.99);
        Cliente cliente=new Cliente(1001,"Oreo S.A");
        Fecha fecha=new Fecha(24,05,2024);

       //Mostrar información completa de todas las clases
       JOptionPane.showMessageDialog(null,"El número del comprobante es: "+comprobante.getNumero());
       JOptionPane.showMessageDialog(null,"El tipo del comprobante es: "+comprobante.getTipo());
       JOptionPane.showMessageDialog(null,"El total de la factura: "+factura.getTotal());
       JOptionPane.showMessageDialog(null,"El código del producto: "+producto.getCodigo());
       JOptionPane.showMessageDialog(null,"La descripción del producto: "+producto.getDescripcion());
       JOptionPane.showMessageDialog(null,"El precio del producto: "+producto.getPrecio());
       JOptionPane.showMessageDialog(null,"El código del cliente: "+cliente.getCodigo());
       JOptionPane.showMessageDialog(null,"La razon Social del cliente: "+cliente.getRazonSocial());
       JOptionPane.showMessageDialog(null,"El dia es: "+fecha.getDia());
       JOptionPane.showMessageDialog(null,"El mes es: "+fecha.getMes());
       JOptionPane.showMessageDialog(null,"El año es: "+fecha.getAnio());

              

    }
    public static void main(String[] args) {

        mostrarInformacion();
        
        
    }
}
