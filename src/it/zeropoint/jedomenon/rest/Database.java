/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.zeropoint.jedomenon.rest;

import java.io.IOException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;




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

  public static String base_url = "http://localhost:3000";
  
  public String get() 
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
  
  public String getAll()
  {
    return "";
  }
  
  public void put()
  {
    
  }
  
  public void post()
  {
    
  }
  
  public void delete()
  {
    
  }
  
  public void getEntities()
  {
    
  }
  
  public void getDetails()
  {
    
  }
}
