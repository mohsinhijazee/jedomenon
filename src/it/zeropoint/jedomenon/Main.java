/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.zeropoint.jedomenon;
import it.zeropoint.jedomenon.gui.MainWindow;
import it.zeropoint.jedomenon.gui.RestTestsWindow;
import it.zeropoint.jedomenon.rest.Resource;
import javax.swing.UIManager;

/**
 *
 * @author mohsinhijazee
 */
public class Main {

   
    
   /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    Resource.setBaseURL("http://localhost:3000");
    /*
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
     */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new MainWindow().setVisible(true);
      }
    });
  }

}
