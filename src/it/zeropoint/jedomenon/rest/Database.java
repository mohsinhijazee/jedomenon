/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.zeropoint.jedomenon.rest;

import java.io.IOException;
import javax.sound.midi.SysexMessage;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




/**
 *
 * @author mohsinhijazee
 * id
 * account_id
 * name
 * 
 * And for REST
 * url
 * account
 * name
 * entities_url
 * details_url
 * 
 */

public class Database {

  // These must be moved to an upper level
  public static String base_url = "http://localhost:3000";
  public static String path = "/databases";
  public static String format = "json";
  protected JSONObject object;
  
  public Database()
  {
    
  }
  
  public Database(int id)
  {
    try
    {
      this.object = new JSONObject(getRaw(id));  
    }
    catch(JSONException e)
    {
      
    }
  }
  
  public Database(String json)
  {
    try
    {
      object = new JSONObject(json);
    }
    catch(JSONException e)
    {
      System.err.println("Creation of resource from JSON failed!");
    }
  }
  
  public Object getAttribute(String field) throws JSONException
  {
    return object.get(field);
  }
  
  public String url() throws JSONException
  {
    return object.getString("url");
  }
  


  public String getRaw(int id)
  {
    
    String resource = "";
    
    HttpClient client  = new HttpClient();
    HttpMethod method = new GetMethod(base_url + path + "/" + id + "." + format);
    method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
            new DefaultHttpMethodRetryHandler(3, false));
    
    try
    {
      int statusCode = client.executeMethod(method);
      
      if(statusCode != HttpStatus.SC_OK)
      {
        System.err.println("Method failed: " + method.getStatusLine());
      }
      
      byte[] responseBody = method.getResponseBody();
      resource = new String(responseBody);
      this.object = new JSONObject(resource);
      
    }
    catch(HttpException e)
    {
      
    }
    catch(IOException e)
    {
      
    }
    catch(JSONException e)
    {
      
    }
    finally
    {
      method.releaseConnection();
    }
    
    return resource;  
  }
  
  public Database get(int id)
  {
    return new Database(getRaw(id));
  }
  
  public String getAllRaw()
  {
    String databases = "";
    HttpClient client  = new HttpClient();
    HttpMethod method = new GetMethod(base_url + "/databases.json");
    method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
            new DefaultHttpMethodRetryHandler(3, false));
    
    try
    {
      int statusCode = client.executeMethod(method);
      
      if(statusCode != HttpStatus.SC_OK)
      {
        System.err.println("Method failed: " + method.getStatusLine());
      }
      
      byte[] responseBody = method.getResponseBody();
      databases = new String(responseBody);
      
    }
    catch(HttpException e)
    {
      
    }
    catch(IOException e)
    {
      
    }
    finally
    {
      method.releaseConnection();
    }
    
    return databases;  
  }
  
  public String getAll() throws JSONException
  {
    Database[] databases = null;
    String json = getAllRaw();
    JSONObject obj = new JSONObject(json);
    
    JSONArray resources = obj.getJSONArray("resources");
    
    databases = new Database[resources.length()];
    
    for(int i = 0; i < resources.length(); i++)
      databases[i] = new Database();
    return obj.toString(2);
  }
    
  public String toJSON() throws JSONException
  {
    return object.toString(2);
  }
  

  // Specific to Database
  public String account_url() throws JSONException
  {
    return object.getString("account_url");
  }
  
  public String name() throws JSONException
  {
    return object.getString("name");
  }
  
  public void name(String name) throws JSONException
  {
    this.object.put("name", name);
  }
  
  public String entities_url() throws JSONException
  {
    return object.getString("entities_url");
  }
  
  public String details_url() throws JSONException
  {
    return object.getString("details_url");
  }
  

}
