/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Animacion.Animacion;
import Clases.Conexion;
import java.awt.BorderLayout;
import java.awt.Image;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 *
 * @author carlo
 */
public class General extends javax.swing.JFrame {
   int menu=0;
   
    /**
     * Creates new form General
     */
    Conexion con;
    public General() {
        initComponents();
     
          ImageIcon imagen1 = new ImageIcon(getClass().getResource("/logo.png"));
          Icon fondo1 = new ImageIcon(imagen1.getImage().getScaledInstance(lblImagen.getWidth(),lblImagen.getHeight(),Image.SCALE_DEFAULT));
         lblImagen.setIcon(fondo1);
         this.repaint();

      this.setLocationRelativeTo(null);
     
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        pnlContenedor = new javax.swing.JPanel();
        lblImagen = new javax.swing.JLabel();
        btnInventario = new javax.swing.JButton();
        btnPersonal = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnVentas = new javax.swing.JButton();
        btnFinanzas = new javax.swing.JButton();
        btnCitas = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Salida2.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Salida3.png"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salida1.png"))); // NOI18N
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 0, 70, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/minimiza2.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/minimizar3.png"))); // NOI18N
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/minimizar1.png"))); // NOI18N
        jButton2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 0, 70, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Clínica Médica");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, -1, -1));

        jPanel2.setBackground(new java.awt.Color(36, 41, 46));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlContenedor.setBackground(new java.awt.Color(36, 41, 46));
        pnlContenedor.setLayout(null);
        pnlContenedor.add(lblImagen);
        lblImagen.setBounds(82, 50, 1041, 623);

        jPanel2.add(pnlContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 1360, 800));

        btnInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Inventario.png"))); // NOI18N
        btnInventario.setBorder(null);
        btnInventario.setBorderPainted(false);
        btnInventario.setContentAreaFilled(false);
        btnInventario.setFocusPainted(false);
        btnInventario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnInventario.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Inventario1.png"))); // NOI18N
        btnInventario.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Inventario3.png"))); // NOI18N
        btnInventario.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Inventario1.png"))); // NOI18N
        btnInventario.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnInventario.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                jproveedores(evt);
            }
        });
        btnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventarioActionPerformed(evt);
            }
        });
        jPanel2.add(btnInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 100, 100));

        btnPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Personal.png"))); // NOI18N
        btnPersonal.setBorder(null);
        btnPersonal.setBorderPainted(false);
        btnPersonal.setContentAreaFilled(false);
        btnPersonal.setFocusPainted(false);
        btnPersonal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPersonal.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Personal1.png"))); // NOI18N
        btnPersonal.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Personal3.png"))); // NOI18N
        btnPersonal.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Personal3.png"))); // NOI18N
        btnPersonal.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnPersonal.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                mostar1(evt);
            }
        });
        btnPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalActionPerformed(evt);
            }
        });
        jPanel2.add(btnPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 100, 100));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 1390, 20));

        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Ventas.png"))); // NOI18N
        btnVentas.setBorder(null);
        btnVentas.setBorderPainted(false);
        btnVentas.setContentAreaFilled(false);
        btnVentas.setFocusPainted(false);
        btnVentas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Ventas1.png"))); // NOI18N
        btnVentas.setRequestFocusEnabled(false);
        btnVentas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Ventas3.png"))); // NOI18N
        btnVentas.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnVentas.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                Pasar(evt);
            }
        });
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });
        jPanel2.add(btnVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 100, 100));

        btnFinanzas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Finanzas.png"))); // NOI18N
        btnFinanzas.setBorder(null);
        btnFinanzas.setBorderPainted(false);
        btnFinanzas.setContentAreaFilled(false);
        btnFinanzas.setFocusPainted(false);
        btnFinanzas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFinanzas.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Finanzas1.png"))); // NOI18N
        btnFinanzas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Finanzas3.png"))); // NOI18N
        btnFinanzas.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnFinanzas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnFinanzasMouseMoved(evt);
            }
        });
        btnFinanzas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                a(evt);
            }
        });
        btnFinanzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinanzasActionPerformed(evt);
            }
        });
        jPanel2.add(btnFinanzas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 100, 100));

        btnCitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Citas.png"))); // NOI18N
        btnCitas.setBorder(null);
        btnCitas.setBorderPainted(false);
        btnCitas.setContentAreaFilled(false);
        btnCitas.setFocusPainted(false);
        btnCitas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCitas.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Citas1.png"))); // NOI18N
        btnCitas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Citas3.png"))); // NOI18N
        btnCitas.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnCitas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnCitasMouseMoved(evt);
            }
        });
        btnCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCitasa(evt);
            }
        });
        btnCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCitasActionPerformed(evt);
            }
        });
        jPanel2.add(btnCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 120, 100));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int sino = JOptionPane.YES_NO_OPTION;
        int resultado = JOptionPane.showConfirmDialog(null,"¿Desea Sallir del Sistema?","Exit",sino);
        if(resultado==0){
            
            System.exit(0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setState(General.ICONIFIED);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
       
    }//GEN-LAST:event_formWindowActivated

    private void btnPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalActionPerformed
        RecursosHumanos llamar = new RecursosHumanos();
        llamar.setSize(1420,810);
        llamar.setLocation(0,0);
        pnlContenedor.removeAll();
        pnlContenedor.add(llamar,BorderLayout.CENTER);
        pnlContenedor.revalidate();
        pnlContenedor.repaint();
    }//GEN-LAST:event_btnPersonalActionPerformed

    private void mostar1(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_mostar1
        btnPersonal.setToolTipText("Recursos Humanos");
    }//GEN-LAST:event_mostar1

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        Inventario llamar = new Inventario();
        llamar.setSize(1420,810);
        llamar.setLocation(0,0);
        pnlContenedor.removeAll();
        pnlContenedor.add(llamar,BorderLayout.CENTER);
        pnlContenedor.revalidate();
        pnlContenedor.repaint();
    }//GEN-LAST:event_btnInventarioActionPerformed

    private void jproveedores(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jproveedores
        btnInventario.setToolTipText("Inventario");
    }//GEN-LAST:event_jproveedores

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        // TODO add your handling code here:
        Ventas venta = null;
       try {
           venta = new Ventas();
       } catch (SQLException ex) {
           Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
       }
        venta.setSize(1420,810);
        venta.setLocation(0,0);

        pnlContenedor.removeAll();
        pnlContenedor.add(venta,BorderLayout.CENTER);
        pnlContenedor.revalidate();
        pnlContenedor.repaint();
    }//GEN-LAST:event_btnVentasActionPerformed

    private void Pasar(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_Pasar
        btnVentas.setToolTipText("VENTAS");
    }//GEN-LAST:event_Pasar

    private void btnFinanzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinanzasActionPerformed

       

    }//GEN-LAST:event_btnFinanzasActionPerformed

    private void a(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_a
        // TODO add your handling code here:
    }//GEN-LAST:event_a

    private void btnFinanzasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinanzasMouseMoved
        btnFinanzas.setToolTipText("Inventario");
    }//GEN-LAST:event_btnFinanzasMouseMoved

    private void btnCitasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCitasMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCitasMouseMoved

    private void btnCitasa(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCitasa
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCitasa

    private void btnCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCitasActionPerformed
        // TODO add your handling code here:
        Citas llamar = new Citas();
        llamar.setSize(1420,810);
        llamar.setLocation(0,0);
        pnlContenedor.removeAll();
        pnlContenedor.add(llamar,BorderLayout.CENTER);
        pnlContenedor.revalidate();
        pnlContenedor.repaint();
    }//GEN-LAST:event_btnCitasActionPerformed

    public int ancho(){
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
      
       return ancho;
    }
    
    public int alto(){
         int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
         return alto;
    }
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(General.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(General.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(General.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(General.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new General().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCitas;
    public javax.swing.JButton btnFinanzas;
    public javax.swing.JButton btnInventario;
    public javax.swing.JButton btnPersonal;
    public javax.swing.JButton btnVentas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblImagen;
    public static javax.swing.JPanel pnlContenedor;
    // End of variables declaration//GEN-END:variables
}
