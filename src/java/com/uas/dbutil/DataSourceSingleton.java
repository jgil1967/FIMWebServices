/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.dbutil;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import javax.naming.Context;
import com.uas.properties.PropertiesFacade;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author jonathangil
 */
public class DataSourceSingleton {
     private static DataSourceSingleton     dataSourceSingleton;
   Context ctx = null;
  Hashtable ht = null;
  javax.sql.DataSource datasource = null;
  
   PropertiesFacade fac = null;
  public DataSourceSingleton () throws NamingException{
    ht = new Hashtable();
    ht.put(Context.INITIAL_CONTEXT_FACTORY,
         "weblogic.jndi.WLInitialContextFactory");
    //Properties para la direccion
  ht.put(Context.PROVIDER_URL,
         "t3://localhost:7001");
   
      ctx=new InitialContext( ht );
     fac = new PropertiesFacade();
     datasource = (javax.sql.DataSource) ctx.lookup (fac.obtenerValorPropiedad("dataSourceName"));
     
  
  }
  
    public static DataSourceSingleton getInstance() throws IOException, SQLException, PropertyVetoException, NamingException {
        if (dataSourceSingleton == null) {
            dataSourceSingleton = new DataSourceSingleton();
            return dataSourceSingleton;
        } else {
            return dataSourceSingleton;
        }
    }
      public Connection getConnection() throws SQLException {
        return this.datasource.getConnection();
    }
}
