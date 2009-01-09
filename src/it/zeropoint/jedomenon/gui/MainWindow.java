/*
 * MainWindow.java
 *
 * Created on January 9, 2009, 12:08 PM
 */

package it.zeropoint.jedomenon.gui;
import it.zeropoint.jedomenon.rest.Database;
/**
 *
 * @author  mohsinhijazee
 */
public class MainWindow extends javax.swing.JFrame {
  
  /** Creates new form MainWindow */
  public MainWindow() {
    initComponents();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    getDatabasesButton = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    jsonText = new javax.swing.JTextPane();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Celesus Convertor");
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    getDatabasesButton.setText("Get Databases");
    getDatabasesButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        getDatabasesButtonActionPerformed(evt);
      }
    });
    getContentPane().add(getDatabasesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, -1, -1));

    jScrollPane1.setViewportView(jsonText);

    getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, 220));

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void getDatabasesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getDatabasesButtonActionPerformed
    // TODO add your handling code here:
    Database d = new Database();
    
    jsonText.setText(d.get());
  }//GEN-LAST:event_getDatabasesButtonActionPerformed
  
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton getDatabasesButton;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextPane jsonText;
  // End of variables declaration//GEN-END:variables
  
}
