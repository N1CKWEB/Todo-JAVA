package paquete.todo.java;

public class Clase02 {

    public static void main(String[] args) {

        Empleado obj1 = new Empleado(2378911, "Claudio", "Fernandez", "4264", "23/01/1934", 20100);
        Empleado obj2 = new Empleado(4719209, "Lionel", "Reginato", "9422", "13/07/2014", 2300);
        Empleado obj3 = new Empleado(4790223, "Cristian", "Sagasto", "4244", "31/04/1834", 100000);

        obj1.calcular_edad();
    }
}
