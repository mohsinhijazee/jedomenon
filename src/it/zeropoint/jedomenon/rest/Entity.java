/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.zeropoint.jedomenon.rest;

import it.zeropoint.jedomenon.rest.exceptions.RestException;
import java.io.IOException;
import org.apache.commons.httpclient.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Mohsin Hijazee
 * 
 *    this.resource.put("url", "");
 *     this.resource.put("database_url", "");
 *     this.resource.put("details_url", "");
 *     this.resource.put("relations_url", "");
 *     this.resource.put("instances_url", "");
 *     this.resource.put("name", "");
 *     this.resource.put("lock_version", 0);
 */
public class Entity extends Resource {

   // <editor-fold defaultstate="collapsed" desc="Constructors"> 
  public Entity() throws JSONException
  {
    this.initialize(null);
  }
  
  public Entity(int id) throws IOException, RestException, JSONException
  {
    
    this.initialize(this.fromID(id, null));
    
  }
  
  public Entity(String url) throws IOException, RestException, JSONException
  {
   this.initialize(this.fromURL(url, null));
  }
  
  public Entity(JSONObject json) throws JSONException
  {
    this.initialize(json);
  }
  
  public Entity(Resource resource)
  {
    super(resource);
  }
  // </editor-fold>
  
  
  
  
  // <editor-fold defaultstate="collapsed" desc="Overrides"> 
  
  @Override
  protected void initialize(JSONObject jsonResource) throws JSONException
  {
    // CRITICAL! Must be set!
    this.path = "/entities";
    if(jsonResource == null)
    {
      this.resource = new JSONObject();
      this.resource.put("url", "");
      this.resource.put("database_url", "");
      this.resource.put("details_url", "");
      this.resource.put("relations_url", "");
      this.resource.put("instances_url", "");
      this.resource.put("name", "");
      this.resource.put("lock_version", 0);
    }
    else
      this.resource = jsonResource;
      
  }
  @Override
  protected NameValuePair[] getPostData()
  {
    NameValuePair[] data = {new NameValuePair("entity",
            this.resource.toString())};
    return data;
  } 
  
  //</editor-fold> Overrides
  
  // <editor-fold defaultstate="collapsed" desc="Specific Methods"> 
  
  public String getDatabaseURL() throws JSONException
  {
    return (String) this.getAttribute("database_url");
  }
  
  public void setDatabaseURL(String url) throws JSONException
  {
    this.setAttribute("database_url", url);
  }
  
  public String getDetailsURL() throws JSONException
  {
    return (String) this.getAttribute("details_url");
  }
  
  public String getInstancesURL() throws JSONException
  {
    return (String) this.getAttribute("instances_url");
  }
  
  public String getRelationsURL() throws JSONException
  {
    return (String) this.getAttribute("relations_url");
  }
  
  public Database getDatabase() throws JSONException, IOException, RestException
  {
    return new Database(this.getDatabaseURL());
  }
  
  public void setDatbase(Database database) throws JSONException
  {
    this.setAttribute("database_url", database.url());
  }
  
  public Detail[] getDetails()
  {
    return null;
  }
  
  public Resource[] getRelations()
  {
    return null;
  }
  
  public Resource[] getInstances()
  {
    return null;
  }
  
  // </editor-fold> Specific Methods
          
}
