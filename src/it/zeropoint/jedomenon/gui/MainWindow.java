/*
 * MainWindow.java
 *
 * Created on January 9, 2009, 12:08 PM
 */

package it.zeropoint.jedomenon.gui;
import it.zeropoint.jedomenon.rest.Database;
import it.zeropoint.jedomenon.rest.Database;
import it.zeropoint.jedomenon.rest.Detail;
import it.zeropoint.jedomenon.rest.Entity;
import it.zeropoint.jedomenon.rest.Resource;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.json.JSONException;

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

    getDatabaseButton = new javax.swing.JButton();
    getAllDatabasesButton = new javax.swing.JButton();
    deleteDatabaseButton = new javax.swing.JButton();
    putDatabaseButton = new javax.swing.JButton();
    postDatabaseButton = new javax.swing.JButton();
    getDatabaseEntitiesButton = new javax.swing.JButton();
    getDatabaseDetails = new javax.swing.JButton();
    getEntityButton = new javax.swing.JButton();
    getEntitiesButton = new javax.swing.JButton();
    postEntityButton = new javax.swing.JButton();
    putEntityButton = new javax.swing.JButton();
    deleteEntityButton = new javax.swing.JButton();
    getDetailsButton = new javax.swing.JButton();
    getInstances = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Jedomenon");

    getDatabaseButton.setText("GET /database/6.json");
    getDatabaseButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        getDatabaseButtonActionPerformed(evt);
      }
    });

    getAllDatabasesButton.setText("GET /databases.json");
    getAllDatabasesButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        getAllDatabasesButtonActionPerformed(evt);
      }
    });

    deleteDatabaseButton.setText("DELETE /databases/6.json");
    deleteDatabaseButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        deleteDatabaseButtonActionPerformed(evt);
      }
    });

    putDatabaseButton.setText("PUT /databases/6.json");
    putDatabaseButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        putDatabaseButtonActionPerformed(evt);
      }
    });

    postDatabaseButton.setText("POST /databases.json");
    postDatabaseButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        postDatabaseButtonActionPerformed(evt);
      }
    });

    getDatabaseEntitiesButton.setText("GET /entities.json");
    getDatabaseEntitiesButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        getDatabaseEntitiesButtonActionPerformed(evt);
      }
    });

    getDatabaseDetails.setText("GET /details.json");
    getDatabaseDetails.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        getDatabaseDetailsActionPerformed(evt);
      }
    });

    getEntityButton.setText("GET /entities/101.json");
    getEntityButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        getEntityButtonActionPerformed(evt);
      }
    });

    getEntitiesButton.setText("GET /entities.json");
    getEntitiesButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        getEntitiesButtonActionPerformed(evt);
      }
    });

    postEntityButton.setText("POST /entities.json");
    postEntityButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        postEntityButtonActionPerformed(evt);
      }
    });

    putEntityButton.setText("PUT /entities/101.json");
    putEntityButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        putEntityButtonActionPerformed(evt);
      }
    });

    deleteEntityButton.setText("DELETE /entities/101.json");
    deleteEntityButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        deleteEntityButtonActionPerformed(evt);
      }
    });

    getDetailsButton.setText("GET Details");
    getDetailsButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        getDetailsButtonActionPerformed(evt);
      }
    });

    getInstances.setText("GET Instances");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(getDatabaseEntitiesButton, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
          .addComponent(getDatabaseDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
          .addComponent(getDatabaseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
          .addComponent(getAllDatabasesButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
          .addComponent(postDatabaseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
          .addComponent(putDatabaseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
          .addComponent(deleteDatabaseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(getEntityButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(deleteEntityButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(getEntitiesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(postEntityButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(putEntityButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(getInstances, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(getDetailsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(264, 264, 264))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(getDatabaseEntitiesButton)
          .addComponent(getInstances))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(getDatabaseDetails)
          .addComponent(getDetailsButton))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(getDatabaseButton)
          .addComponent(getEntityButton))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(getAllDatabasesButton)
          .addComponent(getEntitiesButton))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(postDatabaseButton)
          .addComponent(postEntityButton))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(putDatabaseButton)
          .addComponent(putEntityButton))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(deleteDatabaseButton)
          .addComponent(deleteEntityButton))
        .addContainerGap(49, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void getDatabaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getDatabaseButtonActionPerformed
    try
    {
      
      Database d = new Database(6);
      
      String json = d.toJSON(2);
      String msg = "Database of ID 6:\n" + json;
      System.out.print(json);
      JOptionPane.showMessageDialog(this, msg);
      
      d = null;
      d = new Database(Resource.buildFromJSON(json));
      msg = "Created from JSON:\n" + d.toJSON(2);
      JOptionPane.showMessageDialog(this, msg);
      
    }catch(Exception e)
    {
      JOptionPane.showMessageDialog(this, e.toString());
      e.printStackTrace();
    }
  }//GEN-LAST:event_getDatabaseButtonActionPerformed

  private void getAllDatabasesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getAllDatabasesButtonActionPerformed
    

    
    
    
    try
    {
      Database d = new Database();
      Database[]  a = d.doGetAll();
      String json = "";
      
      for(int i = 0; i < a.length; i++)
        json += a[i].toJSON(2) + "\n";
      
      System.out.print(json);
      String msg = "Lenght of Database Array:" + a.length + "\n\n" + json;
      JOptionPane.showMessageDialog(this, msg);
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(this, e.toString());
    }

}//GEN-LAST:event_getAllDatabasesButtonActionPerformed

  private void deleteDatabaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDatabaseButtonActionPerformed

    try
    {
      
      Database d = new Database(6);
      if(d.doDelete())
        JOptionPane.showMessageDialog(this, "Deleted!");
      else
        JOptionPane.showMessageDialog(this, "Failed...");
      
      d = new Database();
      d.setName("New Temp Database");
      d.setAccountURL("http://localhost:3000/accounts/1.json");
      d.doPost();
      JOptionPane.showMessageDialog(this, d.toJSON(2));
      
      if(d.doDelete(d.url()))
        JOptionPane.showMessageDialog(this, "Deleted!");
      else
        JOptionPane.showMessageDialog(this, "Failed...");
      
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(this, e.toString());
    }

}//GEN-LAST:event_deleteDatabaseButtonActionPerformed

  private void putDatabaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_putDatabaseButtonActionPerformed
   
    try
    {
      
      Database d = new Database(6);
      JOptionPane.showMessageDialog(this, "Before PUT:\n" + d.toJSON(2));
      d.setName("Updated BY JAVA!!!");
      d.doPut();
      d = null;
      d = new Database(6);
      JOptionPane.showMessageDialog(this, "After PUT:\n" + d.toJSON(2));
    }catch(Exception e)
    {
      JOptionPane.showMessageDialog(this, e.toString());
    }
   
}//GEN-LAST:event_putDatabaseButtonActionPerformed

  private void postDatabaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postDatabaseButtonActionPerformed
    // TODO add your handling code here:
    try
    {

      Database d = new Database();
      d.setName("MY NEWLY JAVA DATABASE");
      d.setAccountURL("http://localhost:3000/accounts/1.json");
      d.doPost();
      JOptionPane.showMessageDialog(this, d.toJSON(2));
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(this, e);
      e.printStackTrace();
    }
  }//GEN-LAST:event_postDatabaseButtonActionPerformed

  private void getDatabaseEntitiesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getDatabaseEntitiesButtonActionPerformed
    // TODO add your handling code here:
    try
    {

      Database d = new Database(6);
      Entity[] entities = d.getEntities();
      String json = "";
      
      for(int i = 0; i < entities.length; i++)
      {
        json += entities[i].toJSON(2) + "\n";
      }
      
      System.out.print(json);
      JOptionPane.showMessageDialog(this, json);
      
    }
    catch(Exception e)
      {
        JOptionPane.showMessageDialog(this, e);
        e.printStackTrace();
      }
}//GEN-LAST:event_getDatabaseEntitiesButtonActionPerformed

  private void getDatabaseDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getDatabaseDetailsActionPerformed
    // TODO add your handling code here:
    try
    {

      Database d = new Database(6);
      Detail[] details = d.getDetails();
      String json = "";
      for(int i = 0; i < details.length; i++)
        json += details[i].toJSON(2) + "\n";
      
      JOptionPane.showMessageDialog(this, json);
      System.out.print(json);
              
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(this, e);
      e.printStackTrace();
    }
}//GEN-LAST:event_getDatabaseDetailsActionPerformed

  private void getEntityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getEntityButtonActionPerformed

    try
    {
     Entity e1 = new Entity(101);
     Entity e2 = new Entity("http://localhost:3000/entities/101.json");
     String json1 = e1.toJSON(2);
     String json2 = e2.toJSON(2);
     System.out.print(json1);
     System.out.print(json2);
     
     JOptionPane.showMessageDialog(this, json1);
     JOptionPane.showMessageDialog(this, json2);
      
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(this, e);
      e.printStackTrace();
    }
        
  }//GEN-LAST:event_getEntityButtonActionPerformed

  private void getEntitiesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getEntitiesButtonActionPerformed
    // TODO add your handling code here:
    try
    {
      Entity e = new Entity(101);
      Database d = new Database(6);
      
      Entity[] entities = e.doGetAll();
      JOptionPane.showMessageDialog(this, toJSON(entities, 2)); 
      
      entities = e.doGetAll(6);
      JOptionPane.showMessageDialog(this, toJSON(entities, 2)); 
      
      entities = e.doGetAll("http://localhost:3000/databases/6.json");
      JOptionPane.showMessageDialog(this, toJSON(entities, 2)); 
      
      entities = e.doGetAll(d);
      JOptionPane.showMessageDialog(this, toJSON(entities, 2)); 
      System.out.print(toJSON(entities, 2));
      
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(this, e);
      e.printStackTrace();
    }
  }//GEN-LAST:event_getEntitiesButtonActionPerformed

  private void postEntityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postEntityButtonActionPerformed
    // TODO add your handling code here:
    try
    {
      Database database = new Database(6);
      Entity e = new Entity();
      e.setName("Newly created entityy");
      e.setDatbase(database);
      String json = e.toJSON(2);
      String msg = "Before POST: \n" + json;
      JOptionPane.showMessageDialog(this, msg);
      
      
      e.doPost();
      json = e.toJSON(2);
      msg = "After POST:\n" + json;
      System.out.print(json);
      JOptionPane.showMessageDialog(this, msg);
      
      
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(this, e);
      e.printStackTrace();
    }       
  }//GEN-LAST:event_postEntityButtonActionPerformed

  private void putEntityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_putEntityButtonActionPerformed
    try
    {
      Entity e = new Entity(101);
      String json = e.toJSON(2);
      String msg = "BEFORE PUT:\n" + json;
      JOptionPane.showMessageDialog(this, msg);
      
      e.setName("PUT CALL VIA JAVA LIB");
      e.doPut();
      
      json = e.toJSON(2);
      msg = "After PUT:\n" + json;
      JOptionPane.showMessageDialog(this, msg);
            
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(this, e);
      e.printStackTrace();
    }       
  }//GEN-LAST:event_putEntityButtonActionPerformed

  private void deleteEntityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEntityButtonActionPerformed
    try
    {
      Entity e = new Entity(101);
      
      if(e.doDelete())
        JOptionPane.showMessageDialog(this, "DELTEDD!");
      else
        JOptionPane.showMessageDialog(this, "Failed...");
      
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(this, e);
      e.printStackTrace();
    }       
  }//GEN-LAST:event_deleteEntityButtonActionPerformed

  private void getDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getDetailsButtonActionPerformed
    // TODO add your handling code here:
    try
    {
      Entity e = new Entity(100) ;
      Detail[] details = e.getDetails();
      String json = toJSON(details, 2);
      String msg = "Details:\n" + json;
      
      JOptionPane.showMessageDialog(this, msg);
    }
    catch(Exception e)
    {
      
    }
}//GEN-LAST:event_getDetailsButtonActionPerformed
  
  private String toJSON(Resource[] resources, int indent_factor) throws JSONException
  {
    String json = "";
    for(int i = 0; i < resources.length; i++)
      json += resources[i].toJSON(indent_factor) + "\n";
    
    return json;
  }
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton deleteDatabaseButton;
  private javax.swing.JButton deleteEntityButton;
  private javax.swing.JButton getAllDatabasesButton;
  private javax.swing.JButton getDatabaseButton;
  private javax.swing.JButton getDatabaseDetails;
  private javax.swing.JButton getDatabaseEntitiesButton;
  private javax.swing.JButton getDetailsButton;
  private javax.swing.JButton getEntitiesButton;
  private javax.swing.JButton getEntityButton;
  private javax.swing.JButton getInstances;
  private javax.swing.JButton postDatabaseButton;
  private javax.swing.JButton postEntityButton;
  private javax.swing.JButton putDatabaseButton;
  private javax.swing.JButton putEntityButton;
  // End of variables declaration//GEN-END:variables
  
}
