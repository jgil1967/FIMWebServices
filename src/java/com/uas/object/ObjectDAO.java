/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.object;

import com.uas.dbutil.DataSource;
import com.uas.dbutil.DataSourceSingleton;
import com.uas.dbutil.getTomcatDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public class ObjectDAO implements ObjectInterface {
   
    @Override
    public ObjectDTO createObject(ObjectDTO oDto) {
      PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        // 
                 
         try {
       c = DataSourceSingleton.getInstance().getConnection(); 
          String SQL = "INSERT INTO \"public\".\"object\" (\"name\", \"description\", \"color\", \"kind\", \"createdBy\") VALUES (?, ?, ?,?,?) returning id;";
     	preparedStmt = c.prepareStatement(SQL);
         preparedStmt.setString(1, oDto.getName());
            preparedStmt.setString(2, oDto.getDescription());
            preparedStmt.setString(3, oDto.getColor());
            preparedStmt.setString(4, oDto.getKind());
            preparedStmt.setInt(5, oDto.getCreatedBy());
             rs =  preparedStmt.executeQuery();
              while (rs.next()) {
                  oDto.setId(rs.getInt("id"));
                 // System.out.println("ID a retornar: " + oDto.toString());
           }
         
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
    return oDto;  }

    @Override
    public ObjectDTO updateObject(ObjectDTO oDto) {
      ObjectDTO objectDto = null;
               
     // 
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement preparedStmt = null;
     
         try{
              c = DataSourceSingleton.getInstance().getConnection(); 
               String SQL = "update \"public\".\"object\" set \"name\"=?, \"description\"=?, \"color\"=? where \"id\"=? ";
                preparedStmt = c.prepareStatement(SQL);
                preparedStmt.setString(1, oDto.getName());
                preparedStmt.setString(2, oDto.getDescription());
                preparedStmt.setString(3, oDto.getColor());
                preparedStmt.setInt(4, oDto.getId());
                preparedStmt.executeUpdate();
              
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
                   if (preparedStmt != null){
                     preparedStmt.close();
                 }
                    
                
             }
             catch (Exception e2){
                 e2.printStackTrace();
             }
         
         }
         return oDto;}

    @Override
    public ArrayList<ObjectDTO> getObjects() {
    ArrayList<ObjectDTO> objects = null;
     ObjectDTO oDto = null;
//       
          
ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       objects= new ArrayList<ObjectDTO>  ();
         try{
              c = DataSourceSingleton.getInstance().getConnection(); 
               String SQL = "SELECT \"object\".\"name\", \"object\".\"description\", \"object\".\"kind\", \"object\".\"id\", \"object\".\"color\" FROM \"object\" WHERE \"object\".\"kind\" = 'document'";
               ps = c.prepareStatement(SQL);
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       oDto = new ObjectDTO();
                       oDto.setId(rs.getInt("id"));
                       oDto.setName(rs.getString("name"));
                       oDto.setDescription(rs.getString("description"));
                        oDto.setColor(rs.getString("color"));
                        oDto.setKind(rs.getString("kind"));
                        objects.add(oDto);
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
        return objects; }

    
    
}
