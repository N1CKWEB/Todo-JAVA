/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package paquete.atsventana;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author LENOVO
 */
public class ATSVentana extends JFrame {

    public JPanel panel;

    public ATSVentana() {
        this.setTitle("Ventana Emergente");
        this.setLocationRelativeTo(null);

        this.setBounds(40, 40, 400, 400);
        this.setMinimumSize(new Dimension(200, 200));
        iniciarComponente();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void iniciarComponente() {
        colocarPaneles();
        // colocarEtiqueta();
        colocarBotones();
    }
 
    private void colocarPaneles() {
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);

    }

    private void colocarEtiqueta() {

        //Etiqueta 1- etiqueta tipo de texto
        JLabel label = new JLabel("Nombre");
        label.setBounds(30, 50, 80, 30);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.blue);
        label.setOpaque(true);
        label.setFont(new Font("cooper black", Font.BOLD, 20));

        panel.add(label);

        //Etiqueta 2- etiqueta tipo imagen
        ImageIcon imagen = new ImageIcon("fifaQatar2022.jpg");
        JLabel label2 = new JLabel();
        label2.setBounds(10, 80, 170, 190);
        panel.add(label2);

        //Cambiar el tamaño de la imagen     
        label2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(label2.getWidth(), label2.getHeight(), Image.SCALE_SMOOTH)));
    }
    
    private void colocarBotones() {
    Button boton=new Button("Enviar");
    boton.setEnabled(true);//Establecemos el encendido del boton
    boton.setBounds(30,50,80,30);
    
    panel.add(boton);
    
    }

    }


