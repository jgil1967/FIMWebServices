/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.keyword;

import com.uas.dbutil.DataSource;
import com.uas.dbutil.DataSourceSingleton;
import com.uas.dbutil.getTomcatDataSource;
import com.uas.document.DocumentDTO;
import com.uas.transactionRecord.TransactionRecordDTO;
import com.uas.transactionRecord.TransactionRecordFacade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public class KeywordDAO implements KeywordInterface {

    @Override
    public KeywordDTO createKeyword(KeywordDTO dDto) {
       PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        
                 
         try {
       c = DataSourceSingleton.getInstance().getConnection(); 
          String SQL = "INSERT INTO \"public\".\"keyword\" (\"id\") VALUES (?)";
     	preparedStmt = c.prepareStatement(SQL);
         preparedStmt.setInt(1, dDto.getId());
             preparedStmt.executeUpdate();
              TransactionRecordFacade tFac = new TransactionRecordFacade();
             TransactionRecordDTO tDto = new TransactionRecordDTO();
             tDto.getObjectDTO().setId(dDto.getId());
             tDto.getTransactionTypeDTO().setId(5);
             tDto.getUsuarioDTO().setId(dDto.getCreatedBy());
             tFac.createTransactionRecord(tDto);
         
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
    public ArrayList<KeywordDTO> getKeywords() {
           ArrayList<KeywordDTO> keywords = null;
     KeywordDTO keyword = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       keywords = new ArrayList<KeywordDTO> ();
         try{
              c = DataSourceSingleton.getInstance().getConnection(); 
               String SQL = "SELECT \"keyword\".\"id\", \"object\".\"name\" FROM \"keyword\" JOIN \"object\" ON \"keyword\".\"id\" = \"object\".\"id\"";
             
               ps = c.prepareStatement(SQL);
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       keyword = new KeywordDTO();
                       keyword.setId(rs.getInt("id"));
                       keyword.setName(rs.getString("name"));
                       keywords.add(keyword);
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
        return keywords; }

    @Override
    public ArrayList<KeywordDTO> getKeywordsByDocument(DocumentDTO dDto) {
     ArrayList<KeywordDTO> keywords = null;
     KeywordDTO keyword = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       keywords = new ArrayList<KeywordDTO> ();
         try{
              c = DataSourceSingleton.getInstance().getConnection(); 
               String SQL = "SELECT \"object\".\"name\", \"object\".\"description\", \"object\".\"id\", \"object\".\"createdOn\", \"object\".\"createdBy\", \"object\".\"color\", \"object\".\"kind\" FROM \"documentKeywordRelationship\" JOIN \"keyword\" ON \"documentKeywordRelationship\".\"idKeyword\" = \"keyword\".\"id\" JOIN \"object\" ON \"keyword\".\"id\" = \"object\".\"id\" WHERE \"documentKeywordRelationship\".\"idDocument\" = ?";
               ps = c.prepareStatement(SQL);
               ps.setInt(1, dDto.getId());
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       keyword = new KeywordDTO();
                       keyword.setId(rs.getInt("id"));
                       keyword.setName(rs.getString("name"));
                       keywords.add(keyword);
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
        return keywords;  }

    @Override
    public KeywordDTO updateKeyword(KeywordDTO dDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
