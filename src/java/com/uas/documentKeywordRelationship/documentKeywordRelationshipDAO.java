/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.documentKeywordRelationship;

import com.uas.dbutil.DataSource;
import com.uas.dbutil.getTomcatDataSource;
import com.uas.document.DocumentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author jonathangil
 */
public class documentKeywordRelationshipDAO implements documentKeywordRelationshipInterface {

    @Override
    public documentKeywordRelationshipDTO createdocumentKeywordRelationshipDTO(documentKeywordRelationshipDTO dDto) {
      PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        
                 
         try {
        c = DataSource.getInstance().getConnection(); 
          String SQL = "INSERT INTO \"public\".\"documentKeywordRelationship\" (\"idKeyword\",\"idDocument\") VALUES (?,?)";
     	preparedStmt = c.prepareStatement(SQL);
         preparedStmt.setInt(1, dDto.getIdKeyword());
          preparedStmt.setInt(2, dDto.getIdDocument());
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
    return dDto;}

    @Override
    public documentKeywordRelationshipDTO deleteDocumentKeywordRelationshipDTO(documentKeywordRelationshipDTO dDto) {
    PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        
                 
         try {
       c = DataSource.getInstance().getConnection(); 
          String SQL = "delete from \"public\".\"documentKeywordRelationship\" where \"idDocument\"=? and \"idKeyword\"=?";
     	preparedStmt = c.prepareStatement(SQL);
         preparedStmt.setInt(1, dDto.getIdKeyword());
          preparedStmt.setInt(2, dDto.getIdDocument());
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
    return dDto; }

    @Override
    public DocumentDTO deleteDocumentKeywordRelationshipsByDocument(DocumentDTO dDto) {
   PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        
                 
         try {
       c = DataSource.getInstance().getConnection(); 
          String SQL = "delete from \"public\".\"documentKeywordRelationship\" where \"idDocument\"=?";
     	preparedStmt = c.prepareStatement(SQL);
         
          preparedStmt.setInt(1, dDto.getId());
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
    return dDto;  }
    
}
