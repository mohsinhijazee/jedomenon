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
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
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
  public  String baseURL = "http://localhost:3000";
  public  String path = "/databases";
  public  String format = "json";
  
  public static HttpClient httpClient = new HttpClient();
  protected JSONObject object;
  
  
  

  // <editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Default constructor
   */
  public Database()
  {
    object = new JSONObject();
    initialize();
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
  
  protected void initialize()
  {
    if(object == null)
      object = new JSONObject();
    try
    {
      object.put("url", "");
      object.put("account_url", "");
      object.put("entities_url", "");
      object.put("details_url", "");
      object.put("name", "");
      object.put("lock_version", 0);
    }
    catch(JSONException e)
    {
      
    }
  }
  
  /**
   * Executes the given HTTP method on given URL with optionally supplied data.
   * 
   * <p>
   * This method would execute given HTTP method on the given URL and would return
   * the HttpMethod object after execution.
   * </p>
   * @param url on which to execute method
   * @param method HTTP method name like <code>GET</code>, <code>POST</code>,
   *        <code>PUT</code>, <code>DELETE</code>. Not case sensitive
   * @paatram data is an array of <code>NameValuePair</code>. Currently only sensible
   *        in case of <code>PUT</code> and <code>POST</code> methods.
   * @return HttpMethod object of specific type depending on method requested 
   *         to be executed. 
   *        <li><code>GET</code> would return <code>GetMethod</code></li>
   *        <li><code>POST</code> would return <code>PostMethod</code></li>
   *        <li><code>PUT</code> would return <code>PutMethod</code></li>
   *        <li><code>DELETE</code> would return <code>DeleteMethod</code></li>
   * @throws java.io.IOException
   * @throws org.apache.commons.httpclient.HttpException
   */
  protected static synchronized HttpMethod executeMethod(String url, String method, NameValuePair[] data) throws IOException, HttpException
  {
    
    HttpMethod httpMethod = null;
    
    if(method.equalsIgnoreCase("GET"))
    {
      httpMethod = new GetMethod(url);
    }
    
    if(method.equalsIgnoreCase("PUT"))
    {
      httpMethod = new PutMethod(url);
    }
    
    if(method.equalsIgnoreCase("POST"))
    {
      httpMethod = new PostMethod(url);
      ((PostMethod)httpMethod).setRequestBody(data);
    }
    
    if(method.equalsIgnoreCase("DELETE"))
    {
      httpMethod = new DeleteMethod(url);
    }
    
    // Required for DELETE where lock_version needed.
    // Also required for PUT
    if(data != null && !method.equalsIgnoreCase("POST"))
      httpMethod.setQueryString(data);
    
    httpClient.executeMethod(httpMethod);
    return httpMethod;
  }
  
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
    object.put(field, value);    
  }
  
  
  /**
   * Gets the URL of the field
   * @return
   * @throws org.json.JSONException
   */
  public String url()
  {
    return object.optString("url");
  }
  
  public boolean delete() throws JSONException, HttpException, IOException         
  {
    DeleteMethod method = null;
    
    NameValuePair[] data =  {
                              new NameValuePair("lock_version", 
                                      Integer.toString(lock_version()))        
                            };
    method = (DeleteMethod)executeMethod(this.url(), "DELETE", data);
    
    if(method.getStatusCode() != HttpStatus.SC_OK)
    {
      String msg = "DELETE failed: " + method.getStatusLine();
      msg += "Message: " + new String(method.getResponseBody());
      System.err.println(msg);
      return false;
    }
    
    return true;
  
  }
  
  public JSONObject put() throws JSONException
  { 
    NameValuePair[] data = {new NameValuePair("database", this.object.toString())};
    PutMethod method = null;
    JSONObject resource = null;
    
    try
    {
      method = (PutMethod) executeMethod(this.url(), "PUT", data); 
      
      if(method.getStatusCode() != HttpStatus.SC_OK)
      {
        System.err.println("PUT failed!: " + method.getStatusLine());
        System.err.println("PUT failed!: " + new String(method.getResponseBody()));
        return null;
      }
      
      resource = new JSONObject(new String(method.getResponseBody()));
      
    }
    catch(HttpException e)
    {
      return null;
    }
    catch(IOException e)
    {
      return null;
    }
    finally
    {
      method.releaseConnection();
    }
    
    return resource;
  }
  
  public JSONObject post() throws JSONException, HttpException, IOException
  {
    
    NameValuePair[] data = {new NameValuePair("database", object.toString())};
    PostMethod method = null;
    
  
    method = (PostMethod)executeMethod(baseURL + path + "." + format,
            "POST", data);
      
    if(method.getStatusCode() != HttpStatus.SC_CREATED)
    {
      String msg = "POST Failed: " + method.getStatusLine() + "\n";
      msg += new String(method.getResponseBody());
      System.err.println(msg);
      return null;
    }
      
    String json = new String(method.getResponseBody());
    JSONArray array = new JSONArray(json);
    method.releaseConnection();
    return fromURL(array.getString(0));      
  }
  
  public static boolean delete(String url)
  {
    return false;
  }
  
  public static boolean delete(int id)
  {
    return false;
  }

  public String getRaw(int id)
  {
    
    String resource = "";
    
    HttpClient client  = new HttpClient();
    HttpMethod method = new GetMethod(baseURL + path + "/" + id + "." + format);
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
    HttpMethod method = new GetMethod(baseURL + "/databases.json");
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
  public static synchronized  JSONObject fromURL(String url) throws JSONException, HttpException, IOException
  {
    
    GetMethod method = null;
    JSONObject obj = null;
    
    method = (GetMethod) executeMethod(url, "GET", null);
      
    if(method.getStatusCode() != HttpStatus.SC_OK)
    {
      System.err.println(new String(method.getResponseBody()));
      return null;
    }
    
    obj = new JSONObject(new String(method.getResponseBody()));
    
    return obj;
  }
  
  public static JSONObject fromID(int id)
  {
    return null;
  }
  
  public static Database fromJSON(String json)
  {
    return null;
  }
  
  public static Database fromJSONObject(JSONObject object)
  {
    return null;
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
    return null;
  }
  
  public Detail[] getDetails()
  {
    return null;
  }
  
  public int lock_version()
  {
    return this.object.optInt("lock_version");
  }
  // </editor-fold>
  
  

}
