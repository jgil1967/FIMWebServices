/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.dbutil;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author jonathangilsantillan
 */
public class getDataSource {
   Context ctx = null;
  Hashtable ht = null;
  public getDataSource (){
      ht = new Hashtable();
    ht.put(Context.INITIAL_CONTEXT_FACTORY,
         "weblogic.jndi.WLInitialContextFactory");
    //Properties para la direccion
  ht.put(Context.PROVIDER_URL,
         "t3://localhost:7001");   
  }
    javax.sql.DataSource ds = null;
    
  public  javax.sql.DataSource  getDataSource (String datasource) throws NamingException
  
  
  {
      
      
      ctx=new InitialContext( ht );
     // ds = (javax.sql.DataSource) ctx.lookup ("jdbc/redplanetpizza");
      
     ds = (javax.sql.DataSource) ctx.lookup (datasource);
     return ds;
  }
  
  
}
