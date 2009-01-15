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
 * @author Mohsin Hijazee
 */
public class Database extends Resource<Database>
{
  
  // <editor-fold defaultstate="collapsed" desc="Constructors"> 
  public Database() throws JSONException
  {
    this.initialize(null);
  }
  
  public Database(int id) throws IOException, RestException, JSONException
  {
    
    this.initialize(this.fromID(id, null));
    
  }
  
  public Database(String url) throws IOException, RestException, JSONException
  {
   this.initialize(this.fromURL(url, null));
  }
  
  public Database(JSONObject json) throws JSONException
  {
    this.initialize(json);
  }
  // </editor-fold>
  
  
  
  
  // <editor-fold defaultstate="collapsed" desc="Overrides"> 
  
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
  @Override
  protected NameValuePair[] getPostData()
  {
    NameValuePair[] data = {new NameValuePair("database",
            this.resource.toString())};
    return data;
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Specific methods"> 
  // </editor-fold>
}