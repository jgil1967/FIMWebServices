/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.transactionRecord;

import com.uas.dbutil.DataSource;
import com.uas.dbutil.getTomcatDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public class TransactionRecordDAO implements TransactionRecordInterface{

    @Override
    public TransactionRecordDTO createTransactionRecord(TransactionRecordDTO dto) {
        PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        
                 
         try {
       c = DataSource.getInstance().getConnection(); 
          String SQL = "insert into \"public\".\"transactionRecord\" ( \"idObject\", \"idUsuario\", \"idTransactionType\") values ( ?, ?,?)";
     	preparedStmt = c.prepareStatement(SQL);
         preparedStmt.setInt(1, dto.getObjectDTO().getId());
           preparedStmt.setInt(2, dto.getUsuarioDTO().getId());
             preparedStmt.setInt(3, dto.getTransactionTypeDTO().getId());
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
    public ArrayList<TransactionRecordDTO> getTransactionRecords() {
       ArrayList<TransactionRecordDTO> list = null;
     TransactionRecordDTO dto = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       list = new ArrayList<TransactionRecordDTO> ();
         try{
              c = DataSource.getInstance().getConnection(); 
               String SQL = "SELECT \"transactionRecord\".\"id\", \"transactionRecord\".\"date\", \"object\".\"name\", \"object2\".\"name\" AS \"nameUsuario\", \"transactionType\".\"name\" AS \"nameObjeto\" FROM \"transactionRecord\" JOIN \"object\" ON \"transactionRecord\".\"idObject\" = \"object\".\"id\" JOIN \"object\" AS \"object2\" ON \"transactionRecord\".\"idUsuario\" = \"object2\".\"id\" JOIN \"transactionType\" ON \"transactionRecord\".\"idTransactionType\" = \"transactionType\".\"id\" order by \"transactionRecord\".\"date\" desc";
             
               ps = c.prepareStatement(SQL);
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       dto = new TransactionRecordDTO();
                       dto.setId(rs.getInt("id"));
                       dto.setDate(rs.getTimestamp("date"));
                       dto.getObjectDTO().setName(rs.getString("nameObjeto"));
                       dto.getUsuarioDTO().setName(rs.getString("nameUsuario"));
                       dto.getTransactionTypeDTO().setName(rs.getString("name"));
                       list.add(dto);
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
        return list;}
    
}
