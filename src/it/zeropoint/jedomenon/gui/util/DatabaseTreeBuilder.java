/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.zeropoint.jedomenon.gui.util;

import it.zeropoint.jedomenon.rest.Resource;
import it.zeropoint.jedomenon.rest.Database;
import it.zeropoint.jedomenon.rest.Entity;
import it.zeropoint.jedomenon.rest.Detail;
import it.zeropoint.jedomenon.rest.exceptions.RestException;
import java.io.IOException;
import javax.swing.tree.DefaultMutableTreeNode;
import org.json.JSONException;

/**
 *
 * @author Mohsin Hijazee
 */
public class DatabaseTreeBuilder {
  
  /**
   * From the given URL, it would fetch all the databases and would
   * build the databses, their details, details and everything.
   * @param url
   * @return
   */
  public DefaultMutableTreeNode buildTreeFrom(String url) throws IOException, RestException, JSONException
  {
    Resource.setBaseURL(url);
    Database[] databases = new Database().doGetAll();
    
    DefaultMutableTreeNode root = new DefaultMutableTreeNode(url);
    
    for(int i = 0; i < databases.length; i++)
    {
      DefaultMutableTreeNode database_node = new DefaultMutableTreeNode(databases[i]);
      addEntities(database_node, databases[i]);
      addDetails(database_node, databases[i]);
      root.add(database_node);
    }
    
    return root;
  }
  
  private void addEntities(DefaultMutableTreeNode dbNode, Database db) throws JSONException, IOException, RestException
  {
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Entities");
    
    
    Entity[] entities = db.getEntities();
    
    for(int i = 0; i < entities.length; i++)
    {
      DefaultMutableTreeNode entity_node = new DefaultMutableTreeNode(entities[i]);
      root.add(entity_node);
      addDetails(entity_node, entities[i]);
      addRelations(entity_node, entities[i]);
    }
    
    dbNode.add(root);
  }
  
  private void addDetails(DefaultMutableTreeNode dbNode, Database database) throws JSONException, IOException, RestException
  {
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Details");
    
    
    Detail[] details = database.getDetails();
    
    for(int i = 0; i < details.length; i++)
    {
      DefaultMutableTreeNode detail_node = new DefaultMutableTreeNode(details[i]);
      root.add(detail_node);
    }
    
    dbNode.add(root);
  }
  
private void addDetails(DefaultMutableTreeNode dbNode, Entity entity) throws JSONException, IOException, RestException  
{
   DefaultMutableTreeNode root = new DefaultMutableTreeNode("Details");
    
    
    Detail[] details = entity.getDetails();
    
    for(int i = 0; i < details.length; i++)
    {
      DefaultMutableTreeNode detail_node = new DefaultMutableTreeNode(details[i]);
      root.add(detail_node);
    }
    
    dbNode.add(root);
}

  private void addRelations(DefaultMutableTreeNode entity_node, Entity entity) {
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Relations");
    root.add(new DefaultMutableTreeNode());
    entity_node.add(root);
  }
 

}
