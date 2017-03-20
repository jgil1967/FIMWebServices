/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.postgresql;

import com.uas.dbutil.DataSource;
import com.uas.dbutil.DataSourceSingleton;
import com.uas.dbutil.getTomcatDataSource;
import com.uas.properties.PropertiesFacade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author jonathangil
 */
public class PGSQLDAO implements PGSQLInterface{

    @Override
    public void liberarConexiones() {
        
         PropertiesFacade fac = new PropertiesFacade();
        
        
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
        
        try {
           c = DataSourceSingleton.getInstance().getConnection(); 
           
           String SQL = "SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE pid <> pg_backend_pid() AND datname = ?";
           
           
           ps = c.prepareStatement(SQL);
            ps.setString(1, fac.obtenerValorPropiedad("databaseName"));
            rs = ps.executeQuery();
            System.out.println("Liberando conexiones PostgreSQL");
            while (rs.next()) {
            }
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (c != null) {
                    c.close();
                }
                if (ps != null) {
                    ps.close();
                }
          
                
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        
    }
    
}
