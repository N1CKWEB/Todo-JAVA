/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaAgendaElectronica.Servicios;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class Encriptado {
    
    public void encriptarContraseña(String contraseña) {
        // Solicita al usuario que ingrese un texto
        if (contraseña != null && !contraseña.isEmpty()) {
            try {
                // Crea una instancia de MessageDigest para SHA-256
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                // Calcula el hash del texto original
                byte[] encodedHash = digest.digest(contraseña.getBytes(StandardCharsets.UTF_8));

                // Convierte el resultado a una cadena hexadecimal
                String hashedValue = bytesToHex(encodedHash);

                // Muestra el hash utilizando JOptionPane
                JOptionPane.showMessageDialog(null, "SHA-256 Hash: " + hashedValue);
            } catch (NoSuchAlgorithmException e) {
                System.out.println("El error es:" + e);
            }
        } else {
            // Muestra un mensaje si no se proporciona ningún texto
            JOptionPane.showMessageDialog(null, "No se proporcionó ningún texto.");
        }
        System.out.println("La contraseña encriptada es:" + contraseña);
    }

    
    public void validarContraseña(String contraseña) {

        if (contraseñaValida(contraseña)) {
            JOptionPane.showMessageDialog(null, "Sí es valida la contraseña!");
        } else {
            JOptionPane.showMessageDialog(null, "!Error!");

        }
    }

    public static boolean contraseñaValida(String contraseña) {
        // Expresión regular para verificar la contraseña
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).{12,}$";

        // Compila la expresión regular en un patrón
        Pattern pattern = Pattern.compile(regex);

        // Crea un matcher para la contraseña dada
        Matcher matcher = pattern.matcher(contraseña);
        // Devuelve verdadero si la contraseña cumple con la expresión regular
        return matcher.matches();

    }

    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    
    
    public static void main(String[] args) {
        
    }
}
