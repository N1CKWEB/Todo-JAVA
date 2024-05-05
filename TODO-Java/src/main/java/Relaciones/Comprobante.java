package Relaciones;

import java.util.ArrayList;
import java.util.Scanner;

public class Comprobante {

    protected static ArrayList<Comprobante> info1 = new ArrayList<>();
    protected static ArrayList<Factura> info2 = new ArrayList<>();
    protected static ArrayList<Fecha> info3 = new ArrayList<>();
    protected static ArrayList<Cliente> info4 = new ArrayList<>();
    protected static ArrayList<Producto> info5 = new ArrayList<>();

    private char tipo;
    private int numero;
    private Fecha fecha;

    //Constructor lleno
    public Comprobante(char tipo, int numero) {
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

    @Override
    public String toString() {
        return "Tipo: " + tipo + ", Número: " + numero;
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
        public Factura() {

        }

        public Cliente getCliente() {
            return cliente;
        }

        public void setCliente(Cliente val) {

        }

        public float getTotal() {
            return total;
        }

        public void setTotal(float total) {
            this.total = total;
        }

        @Override
        public String toString() {
            return super.toString() + ", Total: " + total;
        }
    }

    public static class Fecha extends Comprobante {

        private int dia;
        private int mes;
        private int anio;

        //Constructor lleno
        public Fecha(int dia, int mes, int anio) {
            this.dia = dia;
            this.mes = mes;
            this.anio = anio;
        }

        //Constructor vacio
        public Fecha() {

        }

        public int getDia() {
            return dia;
        }

        public int getMes() {
            return mes;
        }

        public int getAnio() {
            return anio;
        }

        public void setDia(int dia) {
            this.dia = dia;
        }

        public void setMes(int mes) {
            this.mes = mes;
        }

        public void setAnio(int anio) {
            this.anio = anio;
        }

        @Override
        public String toString() {
            return "Día: " + dia + ", Mes: " + mes + ", Año: " + anio;
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
        public Cliente() {

        }

        public int getCodigo() {
            return codigo;
        }

        public String getRazonSocial() {
            return razonSocial;
        }

        public void setCodigo(int codigo) {
            this.codigo = codigo;
        }

        public void setRazonSocial(String razonSocial) {
            this.razonSocial = razonSocial;
        }

        @Override
        public String toString() {
            return "Código: " + codigo + ", Razón Social: " + razonSocial;
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
        public Producto() {

        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public float getPrecio() {
            return precio;
        }

        public void setCodigo(int codigo) {
            this.codigo = codigo;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public void setPrecio(float precio) {
            this.precio = precio;
        }

        @Override
        public String toString() {
            return "Código: " + codigo + ", Descripción: " + descripcion + ", Precio: " + precio;
        }
    }

    public static void obtenerInformacionGlobal() {
        Scanner nd = new Scanner(System.in);

        Comprobante comprobante = new Comprobante();
        Factura factura = new Factura();
        Fecha fecha = new Fecha();
        Cliente cliente = new Cliente();
        Producto producto = new Producto();

        int numero = comprobante.getNumero();
        char tipo = comprobante.getTipo();
        float total = factura.getTotal();
        int dia = fecha.getDia();
        int mes = fecha.getMes();
        int anio = fecha.getAnio();
        int codigo1 = cliente.getCodigo();
        String razonSocial = cliente.getRazonSocial();
        int codigo2 = producto.getCodigo();
        String descripcion = producto.getDescripcion();
        float precio = producto.getPrecio();

        System.out.println("Ingresa el tipo: ");
        tipo = nd.nextLine().charAt(0);

        System.out.println("Ingresa el número: ");
        numero = nd.nextInt();

        System.out.println("Ingresa el día: ");
        dia = nd.nextInt();

        System.out.println("Ingresa el mes: ");
        mes = nd.nextInt();

        System.out.println("Ingresa el año: ");
        anio = nd.nextInt();

        System.out.println("Ingresa el código del cliente: ");
        codigo1 = nd.nextInt();

        // Consumir la nueva línea pendiente
        nd.nextLine();

        System.out.println("Ingresa la razon social del cliente:");
        razonSocial = nd.nextLine();

        System.out.println("Ingresa el código del producto: ");
        codigo2 = nd.nextInt();

        // Consumir la nueva línea pendiente
        nd.nextLine();

        System.out.println("Ingresa la descripción del producto:");
        descripcion = nd.nextLine();

        System.out.println("Ingresa el precio del producto: ");
        precio = nd.nextFloat();

        // Agregar los datos a los ArrayLists globales
        info1.add(new Comprobante(tipo, numero));
        info2.add(new Factura(total));
        info3.add(new Fecha(dia, mes, anio));
        info4.add(new Cliente(codigo1, razonSocial));
        info5.add(new Producto(codigo2, descripcion, precio));
    }

    public static void main(String[] args) {
        obtenerInformacionGlobal();

        // Imprimir los contenidos de los ArrayLists
        System.out.println("Contenido de info1:");
        for (Comprobante c : info1) {
            System.out.println(c);
        }

        System.out.println("Contenido de info2:");
        for (Factura f : info2) {
            System.out.println(f);
        }

        System.out.println("Contenido de info3:");
        for (Fecha fecha : info3) {
            System.out.println(fecha);
        }

        System.out.println("Contenido de info4:");
        for (Cliente cliente : info4) {
            System.out.println(cliente);
        }

        System.out.println("Contenido de info5:");
        for (Producto producto : info5) {
            System.out.println(producto);
        }
    }
}
