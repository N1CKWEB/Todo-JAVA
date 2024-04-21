

package paquete.ventana;


import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Ventana extends JFrame {

    public Ventana(){
        
        
        //setSize(500,500);//Establecemos los tamaños de las ventanas
        setDefaultCloseOperation(EXIT_ON_CLOSE);//Con esto hacemos que finalice el programa
        //setLocation(100, 200);//Establecemos la posición (inicial) de la ventana
        setBounds(100, 200, 500,500);//En globa los dos metodos tanto del tamaño y la posición
        setLocationRelativeTo(null);//Establecemos la ventana en el centro de la pantalla
          iniciarComponentes();
    }
        private void iniciarComponentes(){
            JPanel panel=new JPanel();//Creación de un panel
            
            panel.setLayout(null);
            this.getContentPane().add(panel);
            JLabel etiqueta=new JLabel("Holaa", SwingConstants.CENTER);
            
            etiqueta.setBounds(24,25, 80,20);
            etiqueta.setForeground(Color.blue);
            etiqueta.setOpaque(true);
            etiqueta.setBackground(Color.GREEN);
            etiqueta.setFont( new Font("arial",Font.PLAIN,20));//Establecemos la fuente del texto
            
            
            panel.add(etiqueta);
            //panel.setBackground(Color.blue);//Establecemos el color del panel
          
            //Etiqueta-2
            
            
        }
    }
