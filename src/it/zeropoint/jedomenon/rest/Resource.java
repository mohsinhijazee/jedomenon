/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.zeropoint.jedomenon.rest;

import it.zeropoint.jedomenon.rest.exceptions.ResourceNotFound;
import it.zeropoint.jedomenon.rest.exceptions.RestException;
import java.io.IOException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Represents a single REST Resource
 * 
 * @author Mohsin Hijazee
 */

public class Resource {
  
  protected static String baseURL = "http://localhost:3000";
  protected static String format = "json";
  protected String path;
  protected JSONObject resource;
  protected static HttpClient httpClient = new HttpClient();
  
  //<editor-fold defaultstate="collapsed" desc="Constructors">
  public Resource() throws JSONException
  {
     this.initialize(null); 
  }
  
  public Resource(int id) throws IOException, RestException, JSONException
  {
    this.resource = this.fromID(id, null);
  }
  
  public Resource(String url) throws IOException, RestException, JSONException
  {
    this.resource = this.fromURL(url, null);
  }
  
  public Resource(JSONObject json)
  {
    this.resource = json;
  }
  
  public Resource(Resource resource)
  {
    this.resource = resource.resource;
  }
  
  protected void initialize(JSONObject jsonResource) throws JSONException
  {
    if(jsonResource == null)
    {
      this.resource = new JSONObject();
      resource.put("url", "");
    }
    else
      this.resource = jsonResource;
    
    
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Builders">
  // Override this
  public static JSONObject buildFromJSON(String json) throws JSONException
  {
    return new JSONObject(json);
    
  }
  //</editor-fold>  
  
  //<editor-fold defaultstate="collapsed" desc="Utility Methods">
  
  /**
   * Internal method to expose the resource to child classes.
   * @return
   */
  
  protected JSONObject getResource()
  {
    return this.resource;
  }
  
  public static void setBaseURL(String url)
  {
    baseURL = url;
  }
  
  public static String getBaseURL()
  {
    return baseURL;
  }
 
  /**
   * Gets the path of the resource
   * @return
   */
  public String getPath()
  {
    return path;
  }
  
  /**
   * Gets full path includilng hostname and format
   * @return
   */
  public String getFullPath()
  {
    return baseURL + path + "." + format;
  }
  
  public int fromURLToID(String url)
  {
    
     int last_dot = url.lastIndexOf(".");
     int last_slash = url.lastIndexOf("/");
     String id = url.substring(last_slash + 1, last_dot);
     return Integer.parseInt(id);
  }
  
  public String fromIDToURL(int id)
  {
    return baseURL + path + "/" + Integer.toString(id) + "." + format;
  }
  
    /**
   * Gets the attribute of given name
   * @param field name to get
   * @return returns the value
   * @throws org.jsonResource.JSONException
   */
  public Object getAttribute(String field) throws JSONException
  {
    return this.resource.get(field);
  }
  
  /**
   * Sets the attribute value
   * @param field is the name of the field
   * @param value value is the value to be set
   * @throws org.jsonResource.JSONException
   */
  public void setAttribute(String field, Object value) throws JSONException        
  {
    this.resource.put(field, value);    
  }
  
  public String toJSON()
  {
    return this.resource.toString();
  }
  
  public String toJSON(int indentFactor) throws JSONException
  {
    return this.resource.toString(indentFactor);
  }
  /**
   * This method returns the resource as a name value pair. This must be 
   * overriden by the child classes. This is needed because each PUT and POST
   * request in current version of the API requires you to mention payload
   * in a parameter named after the type of resource. But in future versions,
   * payload would be always 
   * @return the object as a name value pair.
   */
  protected NameValuePair[] getPostData()
  {
    NameValuePair[] data = {new NameValuePair("resource", this.resource.toString())};
    return data;
  }
  
  /**
   * Gets the URL of the resource being held
   * @return URL of the resource
   */
  public String url()
  {
    return this.resource.optString("url");
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
  protected HttpMethod executeMethod(String url, String method, NameValuePair[] data) throws IOException, HttpException
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
  
  public JSONObject fromID(int id, NameValuePair[] options) throws IOException, RestException, JSONException
  {
    String url = baseURL + path + "/" + Integer.toString(id) + "." + format;
    return fromURL(url, options);
  }
  
  
  public JSONObject fromURL(String url, NameValuePair[] options) throws IOException, RestException, JSONException
  {
    GetMethod method = (GetMethod)executeMethod(url, "GET", null);
    reportPossibleException(method);
    String json = new String(method.getResponseBody());
    method.releaseConnection();
    return new JSONObject(json);
  }
  
  public JSONObject fromJSON(String json) throws JSONException
  {
    return  new JSONObject(json);
  }
  
  /**
   * Throws appropiate RestException based on the status of the executed
   * HTTP methods.
   * 
   * <p>
   *  After executing an HTTP method, call this method and it would raise
   *  Any RestException for you based on the type of error.
   * </p>
   * @param method is HttpMethod you've executed.
   * @throws it.zeropoint.jedomenon.rest.exceptions.RestException
   */
  public void reportPossibleException(HttpMethod method) throws RestException
  {
    switch(method.getStatusCode())
    {
      case HttpStatus.SC_NOT_FOUND:
        throw new ResourceNotFound();
    }
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="HTTP Methods">
  
  /**
   * Gets the remote resource from the url in it. Call it to reload for example.
   * @return Object fo appropiate type
   * @throws org.jsonResource.JSONException
   * @throws java.io.IOException
   * @throws it.zeropoint.jedomenon.rest.exceptions.RestException
   */
  public Resource doGet() throws JSONException, IOException, RestException
  {
    this.resource = this.doGet((String)this.getAttribute("url")).resource;
    return this;
  }
  
  /**
   * Load the resource of given id
   * 
   * @param id of the remote resource
   * @return Object of appropiate type
   * @throws java.io.IOException
   * @throws it.zeropoint.jedomenon.rest.exceptions.RestException
   * @throws org.jsonResource.JSONException
   */
  public Resource doGet(int id) throws IOException, RestException, JSONException
  {
    this.resource = this.fromID(id, null);
    return this;
    
  }
  
  /**
   * Get the remote resource from the given URL
   * @param url where the resouce to be fetched from
   * @return Resource of appropiate type
   * @throws java.io.IOException
   * @throws it.zeropoint.jedomenon.rest.exceptions.RestException
   * @throws org.jsonResource.JSONException
   */
  public Resource doGet(String url) throws IOException, RestException, JSONException
  {
    this.resource =  this.fromURL(url, null);
    return this;
  }
  
  /**
   * Gets all the resources based on any context provided. For example, if you're
   * Trying to get Details of a database, do make a call like this:
   * Better e
   * Detail detailObj = new Detail();
   * @param context
   * @return
   * @throws java.io.IOException
   * @throws it.zeropoint.jedomenon.rest.exceptions.RestException
   * @throws org.jsonResource.JSONException
   */
  protected Resource[] GetAll(NameValuePair[] context) throws IOException, RestException, JSONException
  {
    return GetAll(this.getFullPath(), context);
  }
  
  protected Resource[] GetAll(String url, NameValuePair[] context) throws IOException, RestException, JSONException
  {
    // Excute the method
    GetMethod method = (GetMethod) executeMethod(url, "GET", context);
    Resource[] resource_list = null;
    
    
    reportPossibleException(method);
    JSONObject resource_parcel = new JSONObject(new String(method.getResponseBody()));
    
    
    JSONArray resources = resource_parcel.getJSONArray("resources");
    resource_list = new Resource[resources.length()];
    
    
    for(int i = 0; i < resources.length(); i++)
      resource_list[i] = new Resource(resources.getJSONObject(i));
    
    method.releaseConnection();

    return (resource_list);
  }
  
  /**
   * Gets all the resources. Child classes woudl have to override and
   * supply NameValueParameters for themselves.
   * @return
   */
  public Resource[] doGetAll() throws IOException, RestException, JSONException
  {
    return this.GetAll(null);
  }
  
  /**
   * Gets all the resources based on conditions. 
   * @param conditions
   * @return
   */
  public Resource[] doGetAll(NameValuePair[] conditions) throws IOException, JSONException, RestException
  {
    return this.GetAll(conditions);
  }
  
  /**
   * Would PUT this object to remove server
   * @return reference to itself
   */
  public Resource doPut() throws IOException, RestException, JSONException
  {
    // {new NameValuePair("database", this.resource.toString())};
    NameValuePair[] data = getPostData();
    PutMethod method = null;
    
    method = (PutMethod) executeMethod(this.url(), "PUT", data); 
    reportPossibleException(method);
      
    this.resource = new JSONObject(new String(method.getResponseBody()));
    method.releaseConnection();
    return this;
      
    
  }
  

  protected String doDeleteInternal(String url) throws IOException, RestException
  { 
    NameValuePair[] lock_version = null;
    
    if(this.resource.has("lock_version"))
    {
      lock_version = new NameValuePair[1];
      lock_version[0] = new NameValuePair("lock_version", 
              Integer.toString(this.resource.optInt("lock_version")));
    }
    
    
    DeleteMethod method = (DeleteMethod)executeMethod(url, "DELETE", lock_version);
    reportPossibleException(method);
    String response = new String(method.getResponseBody());
    method.releaseConnection();
    return response;
  }
  
  
  /**
   * Delete current object
   * @return True of deletion succusfull false otherwise.
   */
  public boolean doDelete() throws IOException, RestException
  {
    String response = doDeleteInternal(this.url());
    return response.equalsIgnoreCase("\"deleted\"") ? true : false;
  }
  
  public boolean doDelete(int id) throws IOException, RestException
  {
    String url = baseURL + path + "/" + id + "." + format;
    String response = doDeleteInternal(url);
    return response.equalsIgnoreCase("\"deleted\"") ? true : false;
  }
  
  public boolean doDelete(String url) throws IOException, RestException
  {
    String response = doDeleteInternal(url);
    return response.equalsIgnoreCase("\"deleted\"") ? true : false;
  }
  

  
  /**
   * Posts the current object to Dedomenon
   * @return reference to itself
   * @throws java.io.IOException
   * @throws org.jsonResource.JSONException
   * @throws it.zeropoint.jedomenon.rest.exceptions.RestException
   */
  public Resource doPost() throws IOException, JSONException, RestException
  {
    NameValuePair[] data = getPostData();
    PostMethod method = null;
    
  
    method = (PostMethod)executeMethod(this.getFullPath(),
            "POST", data);
      
      
    String json = new String(method.getResponseBody());
    JSONArray array = new JSONArray(json);
    method.releaseConnection();
    this.resource = fromURL(array.getString(0), null);
    method.releaseConnection();
    return this;
  }
  
  
  //</editor-fold>
  
  // How many ways you can get?
  // id
  // url
  // jsonResource
  // jsonResource object
  

}


