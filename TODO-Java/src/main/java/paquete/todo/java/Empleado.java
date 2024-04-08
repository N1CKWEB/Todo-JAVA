/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete.todo.java;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Empleado {

    public Empleado(int par, String claudio, String fernandez, String string, String string1, int par2) {
    }

    public Empleado(String dni, String nombre, String apellido, String domicilio, String fechaNac, double sueldoBruto) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.fechaNac = fechaNac;
        this.sueldoBruto = sueldoBruto;
    }

    private String dni, nombre, apellido, domicilio, fechaNac;
    private double sueldoBruto;

    public void calcular_edad() {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNacimiento = LocalDate.parse("23/01/1934", fmt);
        LocalDate hoy = LocalDate.now();

        Period periodo = Period.between(fechaNacimiento, hoy);
        System.out.printf("Tu edad es: %s años", periodo.getYears(), periodo.getMonths(), periodo.getDays());

    }

    public void mostrar_datos() {

        System.out.println("Mi nombre es: " + this.nombre + "y mi apellido es:" + this.apellido + "y el domicilio donde vivo es: " + this.domicilio
                + "y mi fecha de nacimiento es: " + this.fechaNac + "y el sueldo bruto es de " + this.sueldoBruto);
    }

}
