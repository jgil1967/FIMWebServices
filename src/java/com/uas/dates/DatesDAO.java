package com.uas.dates;

import com.uas.dbutil.DataSource;
import com.uas.dbutil.DataSourceSingleton;
import com.uas.dbutil.getTomcatDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonathangil
 */
public class DatesDAO implements DatesInterface{

    @Override
    public DatesDTO getDatesDTO() {
    
     DatesDTO oDto = null;
//       
          
ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       
          try {
          c = DataSourceSingleton.getInstance().getConnection(); 
          String SQL = "SELECT( SELECT \"object\".\"createdOn\" FROM \"document\" JOIN \"object\" ON \"document\".\"id\" = \"object\".\"id\" ORDER BY \"object\".\"createdOn\" ASC LIMIT 1) AS oldestCreatedOn,( SELECT \"object\".\"createdOn\" FROM \"document\" JOIN \"object\" ON \"document\".\"id\" = \"object\".\"id\" ORDER BY \"object\".\"createdOn\" DESC LIMIT 1) AS newestCreatedOn,( SELECT \"document\".\"fileDate\" FROM \"document\" ORDER BY \"document\".\"fileDate\" ASC LIMIT 1) AS oldestFileDate,( SELECT \"document\".\"fileDate\" FROM \"document\" ORDER BY \"document\".\"fileDate\" DESC LIMIT 1) AS newestFileDate";
               ps = c.prepareStatement(SQL);
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       oDto = new DatesDTO();
                       
                       oDto.setNewestCreatedOn(rs.getString("newestCreatedOn"));
                       oDto.setNewestFileDate(rs.getString("newestFileDate"));
                        oDto.setOldestCreatedOn(rs.getString("oldestCreatedOn"));
                        oDto.setOldestFileDate(rs.getString("oldestFileDate"));
                        
                   }
         }
         catch (Exception e){
             e.printStackTrace();
         }
         finally{
             try{
                 if (rs != null){
                     rs.close();
                 }
                  if (c != null){
                     c.close();
                 }
                   if (ps != null){
                     ps.close();
                 }
                    
                
             }
             catch (Exception e2){
                 e2.printStackTrace();
             }
         }
        return oDto;  }
    
}
