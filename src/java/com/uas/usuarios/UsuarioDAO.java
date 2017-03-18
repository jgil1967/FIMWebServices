/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.usuarios;


import com.uas.dbutil.DataSource;
import com.uas.dbutil.getTomcatDataSource;
import com.uas.transactionRecord.TransactionRecordDTO;
import com.uas.transactionRecord.TransactionRecordFacade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public class UsuarioDAO implements UsuarioInterface{

    @Override
    public UsuarioDTO createUsuario(UsuarioDTO oDto) {
     PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        
         try {
         c = DataSource.getInstance().getConnection(); 
          String SQL = "INSERT INTO \"public\".\"usuario\" (\"id\",\"contraseña\",\"isAdministrator\",\"enabled\",\"idArea\",\"root\") VALUES (?,?,?,?,?,?)";
     	preparedStmt = c.prepareStatement(SQL);
         preparedStmt.setInt(1, oDto.getId());
            preparedStmt.setString(2, oDto.getcontrasena());
            preparedStmt.setBoolean(3, oDto.getIsAdministrator());
            preparedStmt.setBoolean(4, oDto.getEnabled());
            preparedStmt.setInt(5, oDto.getArea().getId());
            preparedStmt.setBoolean(6, oDto.getRoot());
          preparedStmt.executeUpdate();
            TransactionRecordFacade tFac = new TransactionRecordFacade();
             TransactionRecordDTO tDto = new TransactionRecordDTO();
             tDto.getObjectDTO().setId(oDto.getId());
             tDto.getTransactionTypeDTO().setId(7);
             tDto.getUsuarioDTO().setId(oDto.getCreatedBy());
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
    return oDto;}

    @Override
    public UsuarioDTO iniciarSesion(UsuarioDTO dto) throws Exception {
          ArrayList<UsuarioDTO> lista = null;
     UsuarioDTO objeto = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       lista = new ArrayList<UsuarioDTO> ();
         try{
                c = DataSource.getInstance().getConnection(); 
               String SQL = "SELECT \"usuario\".\"id\", \"usuario\".\"root\", \"usuario\".\"contraseña\", \"usuario\".\"isAdministrator\", \"object\".\"name\", \"object\".\"description\", \"object\".\"createdOn\", \"object\".\"createdBy\", \"object\".\"color\", \"object\".\"kind\", \"usuario\".\"enabled\" AS \"enabledUser\", \"area\".\"enabled\" AS \"enabledArea\" FROM \"usuario\" JOIN \"object\" ON \"usuario\".\"id\" = \"object\".\"id\" JOIN \"area\" ON \"usuario\".\"idArea\" = \"area\".\"id\" where \"object\".\"name\" = ? and \"usuario\".\"contraseña\" = ? and \"usuario\".\"enabled\" = true and  \"area\".\"enabled\" = true ";
               ps = c.prepareStatement(SQL);
               ps.setString(1, dto.getName());
                ps.setString(2, dto.getContrasena());
               rs = ps.executeQuery();
                   while (rs.next()) {
                       objeto = new UsuarioDTO();
                       objeto.setId(rs.getInt("id"));
                       objeto.setName(rs.getString("name"));
                       objeto.setVerified(true);
                       objeto.setIsAdministrator(rs.getBoolean("isAdministrator"));
                       objeto.setRoot(rs.getBoolean("root"));
                      lista.add(objeto);   
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
         if (lista.size()==0){
             objeto = new UsuarioDTO();
             objeto.setVerified(false);
         }
        return objeto;
    }

    @Override
    public ArrayList<UsuarioDTO> obtenerUsuariosForRoot(UsuarioDTO uDto) {
       
        ArrayList<UsuarioDTO> list = null;
     UsuarioDTO dto = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       list = new ArrayList<UsuarioDTO> ();
         try{
              c = DataSource.getInstance().getConnection(); 
               String SQL = "SELECT \"usuario\".\"id\",\"usuario\".\"root\", \"usuario\".\"contraseña\", \"usuario\".\"idArea\", \"usuario\".\"isAdministrator\", \"usuario\".\"enabled\", \"object\".\"name\", \"object\".\"createdBy\", \"object2\".\"name\" AS \"nameArea\" FROM \"usuario\" JOIN \"object\" ON \"usuario\".\"id\" = \"object\".\"id\" JOIN \"object\" AS \"object2\" ON \"usuario\".\"idArea\" = \"object2\".\"id\"";
               if  (!uDto.getName().equals("root0")){
                   SQL = SQL + "WHERE  \"object\".\"name\" != 'root0'";
               }
               ps = c.prepareStatement(SQL);
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       dto = new UsuarioDTO();
                       dto.setId(rs.getInt("id"));
                       dto.setName(rs.getString("name"));
                       dto.setContrasena(rs.getString("contraseña"));
                        dto.setContrasenaVerify(rs.getString("contraseña"));
                        dto.setEnabled(rs.getBoolean("enabled"));
                        dto.setRoot(rs.getBoolean("root"));
                        dto.setIsAdministrator(rs.getBoolean("isAdministrator"));
                        dto.setCreatedBy(rs.getInt("createdBy"));
                        dto.setIdArea(rs.getInt("idArea"));
                        dto.getArea().setId(rs.getInt("idArea"));
                        dto.getArea().setName(rs.getString("nameArea"));
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

    @Override
    public UsuarioDTO updateUsuario(UsuarioDTO oDto) {
   PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        // 
                 
         try {
         c = DataSource.getInstance().getConnection(); 
          String SQL = "update \"public\".\"usuario\" set \"enabled\"=?, \"contraseña\"=?, \"isAdministrator\"=?,\"idArea\"=?,\"root\"=?   where \"id\"=? ";
     	preparedStmt = c.prepareStatement(SQL);
         
            preparedStmt.setBoolean(1, oDto.getEnabled());
            preparedStmt.setString(2, oDto.getcontrasena());
            preparedStmt.setBoolean(3, oDto.getIsAdministrator());
            preparedStmt.setInt(4, oDto.getArea().getId());
            preparedStmt.setBoolean(5, oDto.getRoot());
            preparedStmt.setInt(6, oDto.getId());
            
          preparedStmt.executeUpdate();
              TransactionRecordFacade tFac = new TransactionRecordFacade();
             TransactionRecordDTO tDto = new TransactionRecordDTO();
             tDto.getObjectDTO().setId(oDto.getId());
             tDto.getTransactionTypeDTO().setId(8);
             tDto.getUsuarioDTO().setId(oDto.getCreatedBy());
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
    return oDto; }

    @Override
    public UsuarioDTO verificaDisponibilidadUsuario(UsuarioDTO dto) throws Exception {
        //System.out.println("verificaDisponibilidadUsuario");
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       
         try{
              c = DataSource.getInstance().getConnection(); 
               String SQL = "SELECT \"object\".\"name\" FROM \"usuario\" JOIN \"object\" ON \"usuario\".\"id\" = \"object\".\"id\" where \"object\".\"name\" = ?";
               ps = c.prepareStatement(SQL);
              // System.out.println("dto.getName(); " + dto.getName());
               ps.setString(1, dto.getName());
                 rs = ps.executeQuery();
                while (rs.next()) {
                        dto.setAvailability(false);
                        return dto;
                   }
                       dto.setAvailability(true);
                        return dto; 
                    
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
        return dto;
    }

    @Override
    public UsuarioDTO getUsuarioByID(UsuarioDTO dto) {
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       
         try{
              c = DataSource.getInstance().getConnection(); 
               String SQL = "SELECT \"usuario\".\"id\", \"usuario\".\"root\", \"usuario\".\"contraseña\", \"usuario\".\"idArea\", \"usuario\".\"isAdministrator\", \"usuario\".\"enabled\", \"object\".\"name\", \"object\".\"createdBy\", \"area\".\"superuser\", \"object2\".\"name\" AS \"nameArea\" FROM \"usuario\" JOIN \"object\" ON \"usuario\".\"id\" = \"object\".\"id\" JOIN \"area\" ON \"usuario\".\"idArea\" = \"area\".\"id\" JOIN \"object\" AS \"object2\" ON \"area\".\"id\" = \"object2\".\"id\" where \"usuario\".\"id\" = ?";
               ps = c.prepareStatement(SQL);
               ps.setInt(1, dto.getId());
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       dto.setId(rs.getInt("id"));
                       dto.setName(rs.getString("name"));
                       dto.setContrasena(rs.getString("contraseña"));
                        dto.setContrasenaVerify(rs.getString("contraseña"));
                        dto.setEnabled(rs.getBoolean("enabled"));
                        dto.setIsAdministrator(rs.getBoolean("isAdministrator"));
                        dto.setRoot(rs.getBoolean("root"));
                        dto.setCreatedBy(rs.getInt("createdBy"));
                        dto.setIdArea(rs.getInt("idArea"));
                        dto.getArea().setSuperuser(rs.getBoolean("superuser"));
                        dto.getArea().setId(rs.getInt("idArea"));
                        dto.getArea().setName(rs.getString("nameArea"));
                      
                   }
                   return dto;
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
        return dto; }

    @Override
    public ArrayList<UsuarioDTO> obtenerUsuariosForAdministrator() {
   
        ArrayList<UsuarioDTO> list = null;
     UsuarioDTO dto = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       list = new ArrayList<UsuarioDTO> ();
         try{
              c = DataSource.getInstance().getConnection(); 
               String SQL = "SELECT \"usuario\".\"id\",\"usuario\".\"root\", \"usuario\".\"contraseña\", \"usuario\".\"idArea\", \"usuario\".\"isAdministrator\", \"usuario\".\"enabled\", \"object\".\"name\", \"object\".\"createdBy\", \"object2\".\"name\" AS \"nameArea\" FROM \"usuario\" JOIN \"object\" ON \"usuario\".\"id\" = \"object\".\"id\" JOIN \"object\" AS \"object2\" ON \"usuario\".\"idArea\" = \"object2\".\"id\" where \"usuario\".\"root\" = FALSE AND \"usuario\".\"isAdministrator\" = FALSE  ";
               ps = c.prepareStatement(SQL);
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       dto = new UsuarioDTO();
                       dto.setId(rs.getInt("id"));
                       dto.setName(rs.getString("name"));
                       dto.setContrasena(rs.getString("contraseña"));
                        dto.setContrasenaVerify(rs.getString("contraseña"));
                        dto.setEnabled(rs.getBoolean("enabled"));
                        dto.setRoot(rs.getBoolean("root"));
                        dto.setIsAdministrator(rs.getBoolean("isAdministrator"));
                        dto.setCreatedBy(rs.getInt("createdBy"));
                        dto.setIdArea(rs.getInt("idArea"));
                        dto.getArea().setId(rs.getInt("idArea"));
                        dto.getArea().setName(rs.getString("nameArea"));
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
        return list; }
    
}
