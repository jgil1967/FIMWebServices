/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.googleDriveBackups;

import com.uas.dbutil.DataSource;
import com.uas.dbutil.DataSourceSingleton;
import com.uas.dbutil.getTomcatDataSource;
import com.uas.document.DocumentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public class googleDriveBackupDAO implements googleDriveBackupInterface{

    @Override
    public googleDriveBackupDTO creategoogleDriveBackup(googleDriveBackupDTO dto) {
     PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        
         try {
         c = DataSourceSingleton.getInstance().getConnection(); 
          String SQL = "INSERT INTO \"public\".\"googleDriveBackups\" (\"id\",\"fileName\",\"length\") VALUES (?,?,?)";
     	preparedStmt = c.prepareStatement(SQL);
         preparedStmt.setString(1, dto.getId());
            preparedStmt.setString(2, dto.getFileName());
            preparedStmt.setDouble(3, dto.getLength());
            preparedStmt.executeUpdate();
         }
          catch (Exception e)
            {
        	e.printStackTrace();
            }
        finally{
            try {
               if (c != null) {
                    c.close();
                }
                if (rs != null) {

                    rs.close();
                }
                if (preparedStmt != null) {
                    preparedStmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    return dto; }

    @Override
    public ArrayList<googleDriveBackupDTO> getBackups() {
       ArrayList<googleDriveBackupDTO> list = null;
     googleDriveBackupDTO dto = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       list = new ArrayList<googleDriveBackupDTO> ();
         try{
              c = DataSourceSingleton.getInstance().getConnection(); 
               String SQL = "SELECT \"googleDriveBackups\".\"fileName\", \"googleDriveBackups\".\"id\", \"googleDriveBackups\".\"date\", \"googleDriveBackups\".\"length\" FROM \"googleDriveBackups\" order by \"googleDriveBackups\".\"date\" desc";
             
               ps = c.prepareStatement(SQL);
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       dto = new googleDriveBackupDTO();
                       dto.setId(rs.getString("id"));
                       dto.setFileName(rs.getString("fileName"));
                       dto.setLength(rs.getDouble("length"));
                       dto.setDate(rs.getTimestamp("date"));
                       list.add(dto);
                   }
         }
         catch (Exception e){
             e.printStackTrace();
         }
         finally{
             try{
                  if (c != null){
                     c.close();
                 }
                 if (rs != null){
                     rs.close();
                 }
                 
                   if (ps != null){
                     ps.close();
                 }
             }
             catch (Exception e2){
                 e2.printStackTrace();
             }
         }
        return list;}

   
}
