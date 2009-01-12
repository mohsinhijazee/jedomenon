/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.zeropoint.jedomenon.rest;
// TODO: Add exception handling and logging
// TODO: Use generics to move method upwards
// TODO: Refactor the code sending HTTP requests. It would take following:
//       * URL
//       * Method
//       * Request Body
// TODO: Implement all the from_* methods.

import java.io.IOException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;




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
  public  String base_url = "http://localhost:3000";
  public  String path = "/databases";
  public  String format = "json";
  protected JSONObject object;
  
  // <editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Default constructor
   */
  public Database()
  {
    object = new JSONObject();
  }
  
  /**
   * Creates a database object of the given ID by fetching it.
   * @param id of the database to fetch
   */
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
  
  /**
   * Cretes database object from given JSON
   * @param json the JSON representation of the object
   */
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
  
  /**
   * Creates a database object from JSONObject
   * @param obj is  JSON Object
   */
  public Database(JSONObject obj)
  {
    this.object = obj;
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Might be to Base">
  /**
   * Gets the attribute of given name
   * @param field name to get
   * @return returns the value
   * @throws org.json.JSONException
   */
  public Object getAttribute(String field) throws JSONException
  {
    return object.get(field);
  }
  
  /**
   * Sets the attribute value
   * @param field is the name of the field
   * @param value value is the value to be set
   * @throws org.json.JSONException
   */
  public void setAttribute(String field, Object value) throws JSONException        
  {
    object.remove(field);
    object.put(field, value);
  }
  
  
  /**
   * Gets the URL of the field
   * @return
   * @throws org.json.JSONException
   */
  public String url() throws JSONException
  {
    return object.getString("url");
  }
  
  public boolean delete() throws JSONException
  {
    String url = this.url();
    HttpClient client = new HttpClient();
    HttpMethod method = new DeleteMethod(url);
    
    
    method.setQueryString("lock_version=" + this.getAttribute("lock_version").toString());
    
    try
    {
      client.executeMethod(method);
      if(method.getStatusCode() != HttpStatus.SC_OK)
      {
        System.err.println("Deletion failed: " + method.getStatusLine());
        System.err.println("Deletion failed: " + new String(method.getResponseBody()));
      }
    }catch(HttpException e)
    {
      return false;
    }
    catch(IOException e)
    {
      return false;
    }
    finally
    {
      method.releaseConnection();      
    }
    
    return true;
  }
  
  public boolean put() throws JSONException
  {
    

    HttpClient client = new HttpClient();
    PutMethod method = new PutMethod(this.url());
    NameValuePair[] data = {new NameValuePair("database", this.object.toString())};
    method.setQueryString(data);
    
    
    
    

    
    try
    {
      client.executeMethod(method);
      
      if(method.getStatusCode() != HttpStatus.SC_OK)
      {
        System.err.println("PUT failed!: " + method.getStatusLine());
        System.err.println("PUT failed!: " + new String(method.getResponseBody()));
      }
    }
    catch(HttpException e)
    {
      return false;
    }
    catch(IOException e)
    {
      
    }
    finally
    {
      method.releaseConnection();
    }
    
    return true;
  }
  
  public static boolean delete(String url)
  {
    return false;
  }
  
  public static boolean delete(int id)
  {
    throw  new NotImplementedException();
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
  
  public Database[] getAll() throws JSONException
  {
    Database[] databases = null;
    String json = getAllRaw();
    JSONObject obj = new JSONObject(json);
    
    JSONArray resources = obj.getJSONArray("resources");
    
    databases = new Database[resources.length()];
    
    for(int i = 0; i < resources.length(); i++)
      databases[i] = new Database(resources.getJSONObject(i));
    return databases;
  }
    
  public String toJSON() throws JSONException
  {
    return object.toString(2);
  }
  
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Factory methods">
  public static Database fromURL(String url)
  {
    throw  new NotImplementedException();
  }
  
  public static Database fromID(int id)
  {
    throw  new NotImplementedException();
  }
  
  public static Database fromJSON(String json)
  {
    throw  new NotImplementedException();
  }
  
  public static Database fromJSONObject(JSONObject object)
  {
    throw  new NotImplementedException();
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Specific to Database">
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
  
  public Entity[] getEntities()
  {
    throw  new NotImplementedException();
  }
  
  public Detail[] getDetails()
  {
    throw  new NotImplementedException();
  }
  // </editor-fold>
  
  

}
