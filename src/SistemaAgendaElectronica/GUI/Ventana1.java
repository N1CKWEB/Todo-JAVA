/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package SistemaAgendaElectronica.GUI;

import SistemaAgendaElectronica.Servicios.Encriptado;
import SistemaAgendaElectronica.BD.Usuarios;
import SistemaAgendaElectronica.Servicios.EnviarCorreoElectronico;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class Ventana1 extends javax.swing.JFrame {

    /**
     * Creates new form Ventana1
     */
    public Ventana1() {
        initComponents();
        this.setLocationRelativeTo(null);
        btnRegistrarte.setEnabled(false);
        btnIniciarSesion.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbUsuarioSesion = new javax.swing.JLabel();
        txtUSuarioSesion = new javax.swing.JTextField();
        txtContraseñaSesion = new javax.swing.JTextField();
        btnRecuperarContraseñaSesion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnIniciarSesion = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbUsuarioSesion2 = new javax.swing.JLabel();
        txtCorreoRegistrarse = new javax.swing.JTextField();
        txtUSuarioRegistrarse = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtContraseñaRegistrarse = new javax.swing.JPasswordField();
        btnRegistrarte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane4.setBackground(new java.awt.Color(51, 0, 255));
        jTabbedPane4.setForeground(new java.awt.Color(0, 204, 204));

        jPanel1.setBackground(new java.awt.Color(51, 0, 255));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 204));
        jLabel2.setText("CONTRASEÑA");

        lbUsuarioSesion.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lbUsuarioSesion.setForeground(new java.awt.Color(0, 204, 204));
        lbUsuarioSesion.setText("CORREO");

        txtUSuarioSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUSuarioSesionActionPerformed(evt);
            }
        });
        txtUSuarioSesion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUSuarioSesionKeyReleased(evt);
            }
        });

        txtContraseñaSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseñaSesionActionPerformed(evt);
            }
        });
        txtContraseñaSesion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContraseñaSesionKeyReleased(evt);
            }
        });

        btnRecuperarContraseñaSesion.setBackground(new java.awt.Color(102, 102, 255));
        btnRecuperarContraseñaSesion.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnRecuperarContraseñaSesion.setText("RECUPERAR CONTRASEÑA");
        btnRecuperarContraseñaSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperarContraseñaSesionActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setText("Te has olvidado la contraseña?");

        btnIniciarSesion.setBackground(new java.awt.Color(102, 102, 255));
        btnIniciarSesion.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnIniciarSesion.setText("INICIAR SESIÓN");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 79, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lbUsuarioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtContraseñaSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUSuarioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRecuperarContraseñaSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(37, 37, 37))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUsuarioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUSuarioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContraseñaSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRecuperarContraseñaSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jTabbedPane4.addTab("INICIAR SESIÓN", jPanel1);

        jPanel2.setBackground(new java.awt.Color(51, 0, 255));

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 204));
        jLabel4.setText("CORREO");

        lbUsuarioSesion2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lbUsuarioSesion2.setForeground(new java.awt.Color(0, 204, 204));
        lbUsuarioSesion2.setText("USUARIO");

        txtCorreoRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoRegistrarseActionPerformed(evt);
            }
        });
        txtCorreoRegistrarse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoRegistrarseKeyReleased(evt);
            }
        });

        txtUSuarioRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUSuarioRegistrarseActionPerformed(evt);
            }
        });
        txtUSuarioRegistrarse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUSuarioRegistrarseKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 204));
        jLabel5.setText("CONTRASEÑA");

        txtContraseñaRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseñaRegistrarseActionPerformed(evt);
            }
        });
        txtContraseñaRegistrarse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContraseñaRegistrarseKeyReleased(evt);
            }
        });

        btnRegistrarte.setBackground(new java.awt.Color(102, 102, 255));
        btnRegistrarte.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnRegistrarte.setText("REGISTRARSE");
        btnRegistrarte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lbUsuarioSesion2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUSuarioRegistrarse, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(txtCorreoRegistrarse, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(txtContraseñaRegistrarse))
                .addGap(96, 96, 96))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(btnRegistrarte, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbUsuarioSesion2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtUSuarioRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCorreoRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtContraseñaRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btnRegistrarte, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        jTabbedPane4.addTab("REGISTRARSE", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRecuperarContraseñaSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperarContraseñaSesionActionPerformed

        String remitente = "nicolasdiazgarrido649@gmail.com";
        String password = "jddi rcfn vbdi cusb";
        EnviarCorreoElectronico correo = new EnviarCorreoElectronico(remitente, password);
        Usuarios user = new Usuarios();
        int longitud = 23;
        String contraseña = txtContraseñaSesion.getText();
        String destinatario = JOptionPane.showInputDialog("Introduce tu gmail, para recuperar la contraseña");
        String nuevaContraseña = user.generarContraseñaAleatoria(longitud);
        user.recuperarContraseña(destinatario);
        user.actualizarContraseña(destinatario, nuevaContraseña);
        try {
            correo.enviarGmail("Recuperación de contraseña: ", nuevaContraseña, destinatario);
            JOptionPane.showMessageDialog(null, " ☑ ️!Se envió con éxito el correo electrónico. ☑ ");
            JOptionPane.showMessageDialog(null, "☑  !Contraseña recuperada exitosamente! ☑ ");
            System.out.println("Contraseña nueva: "+nuevaContraseña);

        } catch (MessagingException ex) {
            Logger.getLogger(Ventana1.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtUSuarioSesion.setText(null);
        txtContraseñaSesion.setText(null);
    }//GEN-LAST:event_btnRecuperarContraseñaSesionActionPerformed

    private void txtCorreoRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoRegistrarseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoRegistrarseActionPerformed

    private void txtUSuarioRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUSuarioRegistrarseActionPerformed
    }//GEN-LAST:event_txtUSuarioRegistrarseActionPerformed

    private void txtContraseñaRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseñaRegistrarseActionPerformed
    }//GEN-LAST:event_txtContraseñaRegistrarseActionPerformed

    private void btnRegistrarteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarteActionPerformed
Ventana2 v2 = new Ventana2();
Usuarios user = new Usuarios();
Encriptado encriptar = new Encriptado();

String usuario = txtUSuarioRegistrarse.getText().trim();
String correo = txtCorreoRegistrarse.getText();
String contraseña = txtContraseñaRegistrarse.getText();




// Validaciones previas
if (user.verificacionDeRegistrarse(correo, usuario, contraseña)) {
    if (user.agregarRegistrarse(usuario, correo, contraseña)) {
    
        // Encriptar la contraseña
          encriptar.encriptarContraseña(contraseña); // Aquí se encripta la contraseña
          user.elNombreDeUSuarioEsValidoParaRegistrarse(usuario);
          user.laContraseñaEsValidoParaRegistrarse(contraseña);
          JOptionPane.showMessageDialog(null, "¡Usuario registrado exitosamente!");
         v2.setDato(txtUSuarioRegistrarse.getText());
         v2.setVisible(true);
         this.setVisible(false);
    
    } else {
        JOptionPane.showMessageDialog(null, "Error al registrar el usuario");
    }
} else {
    
    user.elNombreDeUSuarioEsValidoParaRegistrarse(usuario);
    user.laContraseñaEsValidoParaRegistrarse(contraseña);
    JOptionPane.showMessageDialog(null, "Usuario no registrado");
    txtUSuarioRegistrarse.setText(usuario);
    txtCorreoRegistrarse.setText(correo);
    txtContraseñaRegistrarse.setText(contraseña);
}

    }//GEN-LAST:event_btnRegistrarteActionPerformed

    private void txtUSuarioRegistrarseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUSuarioRegistrarseKeyReleased
        if (!txtUSuarioRegistrarse.getText().isEmpty()) {
            btnRegistrarte.setEnabled(true);
        } else {
            btnRegistrarte.setEnabled(false);

        }
    }//GEN-LAST:event_txtUSuarioRegistrarseKeyReleased

    private void txtCorreoRegistrarseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoRegistrarseKeyReleased
        if (!txtCorreoRegistrarse.getText().isEmpty()) {
            btnRegistrarte.setEnabled(true);
        } else {
            btnRegistrarte.setEnabled(false);

        }
    }//GEN-LAST:event_txtCorreoRegistrarseKeyReleased

    private void txtContraseñaRegistrarseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaRegistrarseKeyReleased
        if (!txtContraseñaRegistrarse.getText().isEmpty()) {
            btnRegistrarte.setEnabled(true);
        } else {
            btnRegistrarte.setEnabled(false);

        }

    }//GEN-LAST:event_txtContraseñaRegistrarseKeyReleased

    private void txtUSuarioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUSuarioSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUSuarioSesionActionPerformed

    private void txtUSuarioSesionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUSuarioSesionKeyReleased
        if (!txtUSuarioSesion.getText().isEmpty()) {
            btnIniciarSesion.setEnabled(true);

        } else {
            btnIniciarSesion.setEnabled(false);
        }
    }//GEN-LAST:event_txtUSuarioSesionKeyReleased

    private void txtContraseñaSesionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaSesionKeyReleased
        if (!txtContraseñaSesion.getText().isEmpty()) {
            btnIniciarSesion.setEnabled(true);
        } else {
            btnIniciarSesion.setEnabled(false);
        }
    }//GEN-LAST:event_txtContraseñaSesionKeyReleased

    private void txtContraseñaSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseñaSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraseñaSesionActionPerformed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed

        Ventana2 v2 = new Ventana2();
        Usuarios user = new Usuarios();
        String correo = txtUSuarioSesion.getText();
        String contraseña = txtContraseñaSesion.getText();

        v2.setVisible(true);
        this.setVisible(false);
        v2.setLocationRelativeTo(null);
                 
        
        user.elCorreoEsValidoParaIniciarSesion(correo);
        user.verificacionInicioDeSesion(correo, contraseña);
        v2.setDato(txtUSuarioSesion.getText());
        
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnRecuperarContraseñaSesion;
    private javax.swing.JButton btnRegistrarte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel lbUsuarioSesion;
    private javax.swing.JLabel lbUsuarioSesion2;
    private javax.swing.JPasswordField txtContraseñaRegistrarse;
    private javax.swing.JTextField txtContraseñaSesion;
    private javax.swing.JTextField txtCorreoRegistrarse;
    private javax.swing.JTextField txtUSuarioRegistrarse;
    private javax.swing.JTextField txtUSuarioSesion;
    // End of variables declaration//GEN-END:variables
}
