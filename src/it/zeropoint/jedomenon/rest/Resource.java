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
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author mohsinhijazee
 */

public class Resource <ResourceType> {
  
  protected static String baseURL;
  protected static String format;
  protected String path;
  protected JSONObject resource;
  protected static HttpClient httpClient = new HttpClient();
  
  //<editor-fold defaultstate="collapsed" desc="Constructors">
  public Resource()
  {
     this.initialize(); 
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
  
  protected void initialize()
  {
    this.resource = new JSONObject();
  }
  //</editor-fold>

  

  //<editor-fold defaultstate="collapsed" desc="Utility Methods">
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
  
    /**
   * Gets the attribute of given name
   * @param field name to get
   * @return returns the value
   * @throws org.json.JSONException
   */
  public Object getAttribute(String field) throws JSONException
  {
    return this.resource.get(field);
  }
  
  /**
   * Sets the attribute value
   * @param field is the name of the field
   * @param value value is the value to be set
   * @throws org.json.JSONException
   */
  public void setAttribute(String field, Object value) throws JSONException        
  {
    this.resource.put(field, value);    
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
    return new JSONObject(new String(method.getResponseBody()));
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
  
  public ResourceType doGet() throws JSONException, IOException, RestException
  {
    return this.doGet((String)this.getAttribute("url"));
  }
  
  public ResourceType doGet(int id) throws IOException, RestException, JSONException
  {
    this.resource = this.fromID(id, null);
    return ((ResourceType)this);
  }
  
  public ResourceType doGet(String url) throws IOException, RestException, JSONException
  {
    this.resource = this.fromURL(url, null);
    return ((ResourceType)this);
  }
  
  //</editor-fold>
  
  // How many ways you can get?
  // id
  // url
  // json
  // json object
  

}


