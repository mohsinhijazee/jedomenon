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
  
    // <editor-fold defaultstate="collapsed" desc="HTTP Methods">
      // <editor-fold defaultstate="collapsed" desc="GET">
  @Override
  public Entity doGet() throws JSONException, IOException, RestException
  {
    super.doGet();
    return this;
  }
  
  @Override
  public Entity doGet(int id) throws JSONException, IOException, RestException
  {
    super.doGet(id);
    return this;
  }
  
  @Override
  public Entity doGet(String url) throws JSONException, IOException, RestException
  {
    super.doGet(url);
    return this;
  }
      // </editor-fold>
  
      // <editor-fold defaultstate="collapsed" desc="GET ALL">
  /**
   * Get all the entities of the database to which this entity belongs to
   * @return Array of Entity objects
   */
  @Override
  public Entity[] doGetAll() throws JSONException, IOException, RestException
  {
    return this.doGetAll(this.getDatabaseURL());
  }
  
  /**
   * Get all entities of the given database id
   * @param database_id ID of the database
   * @return Array of Entity objects
   * @throws java.io.IOException
   * @throws it.zeropoint.jedomenon.rest.exceptions.RestException
   * @throws org.json.JSONException
   */
  public Entity[] doGetAll(int database_id) throws IOException, RestException, JSONException
  {
    NameValuePair[] context = {new NameValuePair("database_id", Integer.toString(database_id))};
    Resource[] resources = super.GetAll(context);
    Entity[] entities = new Entity[resources.length];
    
    for(int i = 0; i < resources.length; i++)
      entities[i] = new Entity(resources[i]);
    
    return entities;
  }
  
  /**
   * Get all the entities of the database whose URL is given as parameter
   * @param database_url
   * @return Array of Entity objects that belong to the given Database
   */
  public Entity[] doGetAll(String database_url) throws IOException, RestException, JSONException
  {
    return doGetAll(fromURLToID(database_url));
  }

  /**
   * Get all the entities of the given Database
   * @param database
   * @return
   */
  public Entity[] doGetAll(Database database) throws IOException, RestException, JSONException
  {
    return doGetAll(database.url());
  }
      // </editor-fold>
  
      // <editor-fold defaultstate="collapsed" desc="POST">
  @Override
  public Entity doPost() throws IOException, JSONException, RestException
  {
    super.doPost();
    return this;
  }
     // </editor-fold> POST
  
      // <editor-fold defaultstate="collapsed" desc="PUT">
  @Override
  public Entity doPut() throws IOException, RestException, JSONException
  {
    super.doPut();
    return this;
  }
      // </editor-fold>
  
  
    // </editor-fold> HTTP Methods
  
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
  
  public Detail[] getDetails() throws JSONException, IOException, RestException
  {
    Detail d = new Detail();
    String url = d.getFullPath();
    // Prepare a condition for it
    NameValuePair[] context  = {new NameValuePair("entity_id", 
                                  Integer.toString(this.fromURLToID(this.url())))};
    
    Detail[] details = null;
    // Get teh resources
    Resource[] resources = GetAll(url, context);
    details = new Detail[resources.length];
    
    for(int i = 0; i < resources.length; i++)
      details[i] = new Detail(resources[i]);
    
    return details;
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
