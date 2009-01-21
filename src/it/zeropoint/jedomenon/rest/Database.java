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
 * Represents a Dedomenon Database
 * Has following attributes:
 * 
 *   * url
 *   * account_url
 *   * name
 *   * lock_version
 * 
 * @author Mohsin Hijazee
 */
public class Database extends Resource
{
  
  // <editor-fold defaultstate="collapsed" desc="Constructors"> 
  /**
   * Default contructor. Initializes all the fields with empty strings.
   * @throws org.json.JSONException
   */
  public Database() throws JSONException
  {
    this.initialize(null);
  }
  
  /**
   * Creates a database object corresponding to the given ID
   * @param id
   * @throws java.io.IOException
   * @throws it.zeropoint.jedomenon.rest.exceptions.RestException
   * @throws org.json.JSONException
   */
  public Database(int id) throws IOException, RestException, JSONException
  {
    
    this.initialize(this.fromID(id, null));
    
  }
  
  /**
   * Creates a Database object from the given URL
   * @param url
   * @throws java.io.IOException
   * @throws it.zeropoint.jedomenon.rest.exceptions.RestException
   * @throws org.json.JSONException
   */
  public Database(String url) throws IOException, RestException, JSONException
  {
   this.initialize(this.fromURL(url, null));
  }
  
  /**
   * Creates a Database object from the given JSON
   * @param json
   * @throws org.json.JSONException
   */
  public Database(JSONObject json) throws JSONException
  {
    this.initialize(json);
  }
  
  /**
   * Creates a Database object from the given Resource
   * @param resource
   */
  public Database(Resource resource)
  {
    super(resource);
  }
  // </editor-fold>
  
  
  
  
  // <editor-fold defaultstate="collapsed" desc="Overrides"> 
  
  /**
   * Initializes the database object with attributes.
   * 
   * Also sets the path of the Database 
   * @param jsonResource
   * @throws org.json.JSONException
   */
  @Override
  protected void initialize(JSONObject jsonResource) throws JSONException
  {
    // CRITICAL! Must be set!
    this.path = "/databases";
    if(jsonResource == null)
    {
      this.resource = new JSONObject();
      this.resource.put("url", "");
      this.resource.put("account_url", "");
      this.resource.put("entities_url", "");
      this.resource.put("details_url", "");
      this.resource.put("name", "");
      this.resource.put("lock_version", 0);
    }
    else
      this.resource = jsonResource;
      
  }
  
  /**
   * Returns the POST data as an array of NameValuePair
   * 
   * Data is the JSONObject turned into JSON string and put under
   * field name of "database"
   * @return
   */
  @Override
  protected NameValuePair[] getPostData()
  {
    NameValuePair[] data = {new NameValuePair("database",
            this.resource.toString())};
    return data;
  }
  
    // <editor-fold defaultstate="collapsed" desc="HTTP Methods">
      // <editor-fold defaultstate="collapsed" desc="GET">
  @Override
  public Database doGet() throws JSONException, IOException, RestException
  {
    super.doGet();
    return this;
  }
  
  @Override
  public Database doGet(int id) throws JSONException, IOException, RestException
  {
    super.doGet(id);
    return this;
  }
  
  @Override
  public Database doGet(String url) throws JSONException, IOException, RestException
  {
    super.doGet(url);
    return this;
  }
      // </editor-fold>
  
      // <editor-fold defaultstate="collapsed" desc="GET ALL">
  @Override
  public Database[] doGetAll() throws IOException, RestException, JSONException
  {
    Resource[] resources = super.GetAll(null);
    Database[] databases = new Database[resources.length];
    
    for(int i = 0; i < resources.length; i++)
      databases[i] = new Database(resources[i]);
    
    return databases;
  }
      // </editor-fold>
  
      // <editor-fold defaultstate="collapsed" desc="POST">
  @Override
  public Database doPost() throws IOException, JSONException, RestException
  {
    super.doPost();
    return this;
  }
     // </editor-fold> POST
  
      // <editor-fold defaultstate="collapsed" desc="PUT">
  @Override
  public Database doPut() throws IOException, RestException, JSONException
  {
    super.doPut();
    return this;
  }
      // </editor-fold>
  
  
    // </editor-fold> HTTP Methods
  // </editor-fold> Overrides
  
  // <editor-fold defaultstate="collapsed" desc="Specific methods"> 
  public String getName() throws JSONException
  {
    return (String)this.getAttribute("name");
  }
  
  public void setName(String name) throws JSONException
  {
    this.setAttribute("name", name);
  }
  
  public String getAccountURL() throws JSONException
  {
    return (String)this.getAttribute("account_url");
  }
  
  public void setAccountURL(String accountURL) throws JSONException
  {
    this.setAttribute("account_url", accountURL);
  }
  
  public String getEntitiesURL() throws JSONException
  {
    return (String)this.getAttribute("entities_url");
  }
  
  public String getDetailsURL() throws JSONException
  {
    return (String)this.getAttribute("details_url");
  }
  
  
  /**
   * Gets all the Entity objects of this Database
   * @return Array of Entity objects
   * @throws org.json.JSONException
   * @throws java.io.IOException
   * @throws it.zeropoint.jedomenon.rest.exceptions.RestException
   */
  public Entity[] getEntities() throws JSONException, IOException, RestException
  {
    // Get the URL for entities
    Entity e = new Entity();
    String url = e.getFullPath();
    // Prepare a condition for it
    NameValuePair[] context  = {new NameValuePair("database_id", 
                                  Integer.toString(this.fromURLToID(this.url())))};
    
    Entity[] entities = null;
    // Get teh resources
    Resource[] resources = GetAll(url, context);
    entities = new Entity[resources.length];
    
    for(int i = 0; i < resources.length; i++)
      entities[i] = new Entity(resources[i]);
    
    return entities;
  }
  
  /**
   * Get all the details of this Database
   * @return Array of Detail objects
   * @throws org.json.JSONException
   * @throws java.io.IOException
   * @throws it.zeropoint.jedomenon.rest.exceptions.RestException
   */
  public Detail[] getDetails() throws JSONException, IOException, RestException
  {
        // Get the URL for entities
    Detail d = new Detail();
    String url = d.getFullPath();
    // Prepare a condition for it
    NameValuePair[] context  = {new NameValuePair("database_id", 
                                  Integer.toString(this.fromURLToID(this.url())))};
    
    Detail[] details = null;
    // Get teh resources
    Resource[] resources = GetAll(url, context);
    details = new Detail[resources.length];
    
    for(int i = 0; i < resources.length; i++)
      details[i] = new Detail(resources[i]);
    
    return details;
  }
  
          
  // </editor-fold>
}