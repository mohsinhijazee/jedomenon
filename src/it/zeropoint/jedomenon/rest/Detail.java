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
 */
public class Detail extends Resource{

  // <editor-fold defaultstate="collapsed" desc="Constructors"> 
  public Detail() throws JSONException
  {
    this.initialize(null);
  }
  
  public Detail(int id) throws IOException, RestException, JSONException
  {
    
    this.initialize(this.fromID(id, null));
    
  }
  
  public Detail(String url) throws IOException, RestException, JSONException
  {
   this.initialize(this.fromURL(url, null));
  }
  
  public Detail(JSONObject json) throws JSONException
  {
    this.initialize(json);
  }
  
  public Detail(Resource resource)
  {
    super(resource);
  }
  // </editor-fold>
    
  // <editor-fold defaultstate="collapsed" desc="Overrides"> 
  
  @Override
  protected void initialize(JSONObject jsonResource) throws JSONException
  {
    // CRITICAL! Must be set!
    this.path = "/details";
    if(jsonResource == null)
    {
      this.resource = new JSONObject();
      this.resource.put("url", "");
      this.resource.put("database_url", "");
      this.resource.put("status_url", "");
      this.resource.put("data_type_url", "");
      this.resource.put("name", "");
      this.resource.put("lock_version", 0);
    }
    else
      this.resource = jsonResource;
      
  }
  @Override
  protected NameValuePair[] getPostData()
  {
    NameValuePair[] data = {new NameValuePair("detail",
            this.resource.toString())};
    return data;
  }
  
    // <editor-fold defaultstate="collapsed" desc="HTTP Methods">
      // <editor-fold defaultstate="collapsed" desc="GET">
  @Override
  public Detail doGet() throws JSONException, IOException, RestException
  {
    super.doGet();
    return this;
  }
  
  @Override
  public Detail doGet(int id) throws JSONException, IOException, RestException
  {
    super.doGet(id);
    return this;
  }
  
  @Override
  public Detail doGet(String url) throws JSONException, IOException, RestException
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
  public Detail doPost() throws IOException, JSONException, RestException
  {
    super.doPost();
    return this;
  }
     // </editor-fold> POST
  
      // <editor-fold defaultstate="collapsed" desc="PUT">
  @Override
  public Detail doPut() throws IOException, RestException, JSONException
  {
    super.doPut();
    return this;
  }
      // </editor-fold>
  
  
    // </editor-fold> HTTP Methods
  // </editor-fold> Overrides
  
}
