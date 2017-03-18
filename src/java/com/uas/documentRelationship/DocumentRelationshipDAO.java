/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.documentRelationship;

import com.uas.dbutil.DataSource;
import com.uas.dbutil.getTomcatDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author jonathangil
 */
public class DocumentRelationshipDAO implements DocumentRelationshipInterface{

    @Override
    public DocumentRelationshipDTO createDocumentRelationship(DocumentRelationshipDTO dto) {
      PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        
                 
         try {
       c = DataSource.getInstance().getConnection(); 
          String SQL = "INSERT INTO \"public\".\"documentRelationships\" (\"idDocumentParent\",\"idDocumentChild\") VALUES (?,?)";
     	preparedStmt = c.prepareStatement(SQL);
         
         preparedStmt.setInt(1, dto.getIdDocumentParent());
         preparedStmt.setInt(2, dto.getIdDocumentChild());
         
             preparedStmt.executeUpdate();
//              TransactionRecordFacade tFac = new TransactionRecordFacade();
//             TransactionRecordDTO tDto = new TransactionRecordDTO();
//             tDto.getObjectDTO().setId(dDto.getId());
//             tDto.getTransactionTypeDTO().setId(5);
//             tDto.getUsuarioDTO().setId(dDto.getCreatedBy());
//             tFac.createTransactionRecord(tDto);
         
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
    return dto;}

    
}
