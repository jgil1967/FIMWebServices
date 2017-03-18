/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.dbutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author jonathangilsantillan
 */
public class getTomcatDataSource {
 
  DataSource ds = null;

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }
  
  
    public String returnDataSource(){
       
	Properties prop = new Properties();
	InputStream input = null;

	try {

		input = new FileInputStream("config.properties");
		prop.load(input);
		return prop.getProperty("dataSourceName");

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        return null;
    }
 
   public  javax.sql.DataSource  getTomcatDataSource ()
  {
         try{
                Context initialContext = new InitialContext();
    
if ( initialContext == null){
    
}
 ds = (DataSource)initialContext.lookup("java:/comp/env/"+returnDataSource());
                                   
if (ds != null) {
  
        return ds;
}
if (ds.getConnection() == null){
    
}
        }
        catch (Exception e){
         //   e.printStackTrace();
        }
         return ds;
   }
    public getTomcatDataSource() {
     

    }
    
}
