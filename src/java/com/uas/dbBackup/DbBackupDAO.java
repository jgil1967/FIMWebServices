/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.dbBackup;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author jonathangil
 */
public class DbBackupDAO  implements DbBackupInterface{

    
     public String returnPath(String property){
	Properties prop = new Properties();
	InputStream input = null;
	try {
		input = new FileInputStream("config.properties");
		prop.load(input);
		return prop.getProperty(property);

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
    
    @Override
    public DbBackupDTO backupDatabase(DbBackupDTO dto) {
    String command = dto.postgresqlPath;
        System.out.println("returnPath(\"pathForBackups\") : "+ returnPath("pathForBackups"));
       if (  System.getProperty("os.name").toLowerCase().contains("windows")){
       command = command + "./pg_dump postgresql://";
       }
       else{
            command = command + "pg_dump postgresql://";
       }
     command = command +dto.getUser()+":"+dto.getPassword()+"@127.0.0.1:5432/"+dto.getDatabaseName() + "  > " +returnPath("pathForBackups")+  dto.getNombreBackup()+".sql;";
      try {
    Process process = Runtime.getRuntime().exec(new String[]{"sh", "-c", command});
    } catch (IOException e) {
        e.printStackTrace();
    }
     
     //si existe y no esta vacío
    return null;
    }
    
}
