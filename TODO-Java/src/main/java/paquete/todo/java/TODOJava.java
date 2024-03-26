package paquete.todo.java;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class TODOJava {

    public static void main(String[] args) {

        /*Realice un programa en JAVA, que pida al usuario que ingrese un número
         binario de 16 dígitos y muestre a que número octal,decimal y hexadecimal
         corresponde
         */
        
        Scanner nd = new Scanner(System.in);

        String binario;
        String numAElegir;

        binario = JOptionPane.showInputDialog("Ingrese un número binario de 16 dígitos");

        numAElegir = JOptionPane.showInputDialog("Ingrese una opción:\n"
                + "1-Decimal\n"
                + "2-Octal\n"
                + "3-Hexadecimal\n");

        switch (numAElegir) {
            case "1":
                if ("1".equals(binario) || "0".equals(binario)) {
                    long decimal = Long.parseLong(binario, 2);
                    JOptionPane.showMessageDialog(null, "El número binario " + binario + " en decimal es: " + decimal);
                } else {
                    JOptionPane.showMessageDialog(null, "No sé permiten otros números que 0 y 1");
                }

                break;
            case "2":
                if ("1".equals(binario) || "0".equals(binario)) {
                    while (binario.length() % 3 != 0) {
                        binario = "0" + binario;
                    }

                    StringBuilder octal = new StringBuilder();
                    for (int i = 0; i < binario.length(); i += 3) {
                        String tres = binario.substring(i, i + 3);
                        int decimal2 = Integer.parseInt(tres, 2);
                        octal.append(Integer.toOctalString(decimal2));
                    }
                    JOptionPane.showMessageDialog(null, "El número binario " + binario + " en octal es: " + octal);
                } else {
                    JOptionPane.showMessageDialog(null, "No sé permiten otros números que 0 y 1");

                }
                break;
            case "3":
                if ("1".equals(binario) || "0".equals(binario)) {
                    while (binario.length() % 4 != 0) {
                        binario = "0" + binario;
                    }

                    StringBuilder hexadecimal = new StringBuilder();
                    for (int i = 0; i < binario.length(); i += 4) {
                        String cuatro = binario.substring(i, i + 4);
                        int decimal3 = Integer.parseInt(cuatro, 2);
                        hexadecimal.append(Integer.toHexString(decimal3));
                    }
                    JOptionPane.showMessageDialog(null, "El número binario " + binario + " en hexadecimal es: " + hexadecimal);
                } else {
                    JOptionPane.showMessageDialog(null, "No sé permiten otros números que 0 y 1");

                }
                break;
            default:
                System.out.println("Opción Invalida");
        }
    }
}
