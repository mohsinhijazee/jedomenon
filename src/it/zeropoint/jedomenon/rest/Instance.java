
package it.zeropoint.jedomenon.rest;

import it.zeropoint.jedomenon.rest.exceptions.RestException;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.httpclient.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents an Instance
 * @author Mohsin Hijazee
 * 
 * url
 * entity_url
 * details_url
 * links_url
 * named_details where each is an array
 */
public class Instance extends Resource{


  // <editor-fold defaultstate="collapsed" desc="Constructors"> 
  public Instance() throws JSONException
  {
    this.initialize(null);
  }
  
  public Instance(int id) throws IOException, RestException, JSONException
  {
    
    this.initialize(this.fromID(id, null));
    
  }
  
  public Instance(String url) throws IOException, RestException, JSONException
  {
   this.initialize(this.fromURL(url, null));
  }
  
  public Instance(JSONObject json) throws JSONException
  {
    this.initialize(json);
  }
  
  public Instance(Resource resource)
  {
    super(resource);
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Overrides"> 
  
  @Override
  protected void initialize(JSONObject jsonResource) throws JSONException
  {
    // CRITICAL! Must be set!
    this.path = "/instances";
    if(jsonResource == null)
    {
      this.resource = new JSONObject();
      this.resource.put("url", "");
      this.resource.put("entity_url", "");
      this.resource.put("details_url", "");
      this.resource.put("links_url", "");
      this.resource.put("lock_version", 0);
    }
    else
      this.resource = jsonResource;
      
  }
  @Override
  protected NameValuePair[] getPostData()
  {
    NameValuePair[] data = {new NameValuePair("instance",
            this.resource.toString())};
    return data;
  } 
  
    // <editor-fold defaultstate="collapsed" desc="HTTP Methods">
      // <editor-fold defaultstate="collapsed" desc="GET">
  @Override
  public Instance doGet() throws JSONException, IOException, RestException
  {
    super.doGet();
    return this;
  }
  
  @Override
  public Instance doGet(int id) throws JSONException, IOException, RestException
  {
    super.doGet(id);
    return this;
  }
  
  @Override
  public Instance doGet(String url) throws JSONException, IOException, RestException
  {
    super.doGet(url);
    return this;
  }
      // </editor-fold>
  
      // <editor-fold defaultstate="collapsed" desc="GET ALL">
  /**
   * Get all the instances of the entity
   * @return Array of Instance objects
   */
  @Override
  public Instance[] doGetAll() throws JSONException, IOException, RestException
  {
    return this.doGetAll(this.getEntityURL());
  }
  
  /**
   * Get all instances of the given entity id
   * @param entity_id ID of the entity
   * @return Array of Instance objects
   * @throws java.io.IOException
   * @throws it.zeropoint.jedomenon.rest.exceptions.RestException
   * @throws org.json.JSONException
   */
  public Instance[] doGetAll(int entity_id) throws IOException, RestException, JSONException
  {
    NameValuePair[] context = {new NameValuePair("entity_id", Integer.toString(entity_id))};
    Resource[] resources = super.GetAll(context);
    Instance[] instances = new Instance[resources.length];
    
    for(int i = 0; i < resources.length; i++)
      instances[i] = new Instance(resources[i]);
    
    return instances;
  }
  
  /**
   * Get all the instances of the entity whose URL is given as parameter
   * @param entity_url
   * @return Array of Instance objects that belong to the given Database
   */
  public Instance[] doGetAll(String entity_url) throws IOException, RestException, JSONException
  {
    return doGetAll(fromURLToID(entity_url));
  }

  /**
   * Get all the Instances of the given Entity
   * @param Entity
   * @return Instance objects
   */
  public Instance[] doGetAll(Entity entity) throws IOException, RestException, JSONException
  {
    return doGetAll(entity.url());
  }
      // </editor-fold>
  
      // <editor-fold defaultstate="collapsed" desc="POST">
  @Override
  public Instance doPost() throws IOException, JSONException, RestException
  {
    super.doPost();
    return this;
  }
     // </editor-fold> POST
  
      // <editor-fold defaultstate="collapsed" desc="PUT">
  @Override
  public Instance doPut() throws IOException, RestException, JSONException
  {
    super.doPut();
    return this;
  }
      // </editor-fold>
  
  
    // </editor-fold> HTTP Methods
  
  //</editor-fold> Overrides
  
  
  // <editor-fold defaultstate="collapsed" desc="Specific Methods"> 
  public String getDetailsURL() throws JSONException
  {
    return (String) this.getAttribute("details_url");
  }
  
  public String getLinksURL() throws JSONException
  {
    return (String) this.getAttribute("links_url");
  }
  
  public String getEntityURL() throws JSONException
  {
   return (String) this.getAttribute("entity_url");  
  }
  
  public void setEntityURL(String url) throws JSONException
  {
   this.setAttribute("entity_url", url); 
  }
  
  public Detail[] getDetails() throws JSONException, IOException, RestException
  {
    return this.getEntity().getDetails();
  }
  
  public Link[] getLinks()
  {
    return null;
  }
  public Entity getEntity() throws JSONException, IOException, RestException
  {
    return new Entity(this.fromURL(this.getEntityURL(), null));
  }
  
  public void setEntity(Entity entity) throws JSONException
  {
    this.setEntityURL(entity.url());
  }
  
  /**
   * Would get the names of the details from the local object
   * This method would fail if the instance object is yet not bound to
   * any remote instance.
   * @return String Array of names
   */
  public String[] getDetailNames() throws JSONException
  {
    
    String[] keys = JSONObject.getNames(resource);
    ArrayList names = new ArrayList();
    
    for(int i = 0; i < keys.length; i++)
    {
      if(resource.get(keys[i]) instanceof JSONArray)
        names.add(keys[i]);
    }
    
    String[] array = new String[names.size()];
    
    for(int i = 0; i < names.size(); i++)
      array[i] = (String) names.get(i);
    
    return array;
    
  }
  
  /**
   * 
   * @param detail_name
   * @param value_index
   * @return
   * @throws org.json.JSONException
   */
  public String getValueOf(String detail_name, int value_index) throws JSONException
  {
    String value = "";
    JSONArray values = (JSONArray)this.getAttribute(detail_name);
    JSONObject val = (JSONObject) values.get(value_index);
    
    // If a FileAttachment type, then:
    if(val.get("value") instanceof JSONObject)
      value = ((JSONObject)val).getJSONObject("value").getString("filename");
    else
      value = val.getString("value");
    
    return value;
  }
  
  public int getValueCountOf(String detail_name) throws JSONException
  {
    int count = 0;
    JSONArray array = (JSONArray)this.getAttribute(detail_name);
    count = array.length();
    return count;
  }
  
  public String getValueOf(String detail_name) throws JSONException
  {
    return this.getValueOf(detail_name, 0);
  }
  
  public String[] getValuesFor(String detail_name) throws JSONException
  {
    String[] values = null;
    values = new String[this.getValueCountOf(detail_name)];
    
    for(int i = 0; i < values.length; i++)
      values[i] = this.getValueOf(detail_name, i);
    
    return values;
  }
  
  
  // </editor-fold> Specific Methods
  
}
