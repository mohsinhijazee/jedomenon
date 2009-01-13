/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.zeropoint.jedomenon.rest.exceptions;

/**
 * Represents a REST Exception
 * @author Mohsin Hijazee
 */
public class RestException extends Exception {

  protected int statusCode;
  protected String statusLine;
  protected String response;
  
  
}
