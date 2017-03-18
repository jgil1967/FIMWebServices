/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.areas;

import com.uas.Files.FilesFacade;
import com.uas.dbutil.DataSource;
import com.uas.dbutil.getTomcatDataSource;
import com.uas.properties.PropertiesFacade;
import com.uas.transactionRecord.TransactionRecordDTO;
import com.uas.transactionRecord.TransactionRecordFacade;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public class areaDAO implements areaInterface {

    @Override
    public areaDTO createArea(areaDTO dto) {
        fac = new areaFacade();
        PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs = null;
      try {
          c = DataSource.getInstance().getConnection();  
          
            String SQL = "INSERT INTO \"public\".\"area\" (\"id\",\"superuser\",\"enabled\") VALUES (?,?,?)";
            preparedStmt = c.prepareStatement(SQL);
            preparedStmt.setInt(1, dto.getId());
            preparedStmt.setBoolean(2, dto.isSuperuser());
            preparedStmt.setBoolean(3, dto.isEnabled());
            preparedStmt.executeUpdate();
            TransactionRecordFacade tFac = new TransactionRecordFacade();
            TransactionRecordDTO tDto = new TransactionRecordDTO();
            tDto.getObjectDTO().setId(dto.getId());
            tDto.getTransactionTypeDTO().setId(1);
            tDto.getUsuarioDTO().setId(dto.getCreatedBy());
            tFac.createTransactionRecord(tDto);
            ArrayList<areaDTO> list = new ArrayList<areaDTO> ();
            list.add(dto);
            fac.crearCarpetasAPartirDeAreas(list);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return dto;
    }

    @Override
    public ArrayList<areaDTO> getAreas() {
        ArrayList<areaDTO> list = null;
        areaDTO dto = null;
        
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
        list = new ArrayList<areaDTO>();
       try {
          c = DataSource.getInstance().getConnection(); 
          String SQL = "SELECT \"area\".\"id\", \"area\".\"superuser\", \"area\".\"enabled\", \"object\".\"name\", \"area\".\"folderName\" FROM \"area\" JOIN \"object\" ON \"area\".\"id\" = \"object\".\"id\"";
            ps = c.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                dto = new areaDTO();
                dto.setId(rs.getInt("id"));
                dto.setEnabled(rs.getBoolean("enabled"));
                dto.setSuperuser(rs.getBoolean("superuser"));
                dto.setName(rs.getString("name"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return list;
    }

    @Override
    public ArrayList<areaDTO> getPossibleAreasByArea(areaDTO oDto) {

        ArrayList<areaDTO> list = null;
        areaDTO dto = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
        list = new ArrayList<areaDTO>();
       try {
          c = DataSource.getInstance().getConnection(); 
          String SQL = "SELECT \"area\".\"id\", \"object\".\"name\" FROM \"area\" JOIN \"object\" ON \"area\".\"id\" = \"object\".\"id\" WHERE \"area\".\"id\" NOT IN( SELECT \"object\".\"id\" FROM \"areaRelationships\" JOIN \"object\" ON \"areaRelationships\".\"idArea2\" = \"object\".\"id\" WHERE \"areaRelationships\".\"idArea1\" = ?) ";
            ps = c.prepareStatement(SQL);
            ps.setInt(1, oDto.getId());
            rs = ps.executeQuery();
            while (rs.next()) {

                if (oDto.getId() == rs.getInt("id")) {
                    //System.out.println("Es el mismo ID");
                    continue;
                }
                dto = new areaDTO();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                list.add(dto);
            }
            // System.out.println("list: " + list.size());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
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
        return list;
    }

    @Override
    public ArrayList<areaDTO> getAreasByArea(areaDTO oDto) {

        ArrayList<areaDTO> list = null;
        areaDTO dto = null;
        
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
        list = new ArrayList<areaDTO>();
         try {
          c = DataSource.getInstance().getConnection(); 
          
            String SQL = "SELECT \"object\".\"name\", \"object\".\"id\", \"areaRelationships\".\"idArea1\",\"areaRelationships\".\"uploadAndEdit\" FROM \"areaRelationships\" JOIN \"object\" ON \"areaRelationships\".\"idArea2\" = \"object\".\"id\" where \"areaRelationships\".\"idArea1\" = ? ";
            ps = c.prepareStatement(SQL);
            ps.setInt(1, oDto.getId());
            rs = ps.executeQuery();
            while (rs.next()) {

                if (oDto.getId() == rs.getInt("id")) {
                    continue;
                }
                dto = new areaDTO();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setUploadAndEdit(rs.getBoolean("uploadAndEdit"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return list;
    }

    @Override
    public ArrayList<areaDTO> getAreasByArea2(areaDTO oDto) {

       // System.out.println("oDto : " + oDto.getId());
        ArrayList<areaDTO> list = null;
        areaDTO dto = null;
        
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
        list = new ArrayList<areaDTO>();
         try {
          c = DataSource.getInstance().getConnection(); 
           String SQL = "SELECT \"object\".\"name\", \"object\".\"id\", \"areaRelationships\".\"idArea1\",\"areaRelationships\".\"uploadAndEdit\" FROM \"areaRelationships\" JOIN \"object\" ON \"areaRelationships\".\"idArea2\" = \"object\".\"id\" JOIN \"area\" ON \"areaRelationships\".\"idArea2\" = \"area\".\"id\" where \"areaRelationships\".\"idArea1\" = ? and \"area\".\"enabled\" = TRUE order by \"object\".\"name\" asc";
            ps = c.prepareStatement(SQL);
            ps.setInt(1, oDto.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("rs.getInt(\"id\") : " + rs.getInt("id"));
                dto = new areaDTO();
                dto.setId(rs.getInt("id"));
//                       if (rs.getInt("id") == oDto.getId()){
//                           System.out.println("rs.getInt(\"id\") : " + rs.getInt("id"));
//                       }
                dto.setName(rs.getString("name"));
                dto.setUploadAndEdit(rs.getBoolean("uploadAndEdit"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return list;
    }

    @Override
    public areaDTO updateArea(areaDTO dto) {
        PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs = null;
        // 
        
         try {
          c = DataSource.getInstance().getConnection(); 
           String SQL = "update \"public\".\"area\" set \"superuser\"=?, \"enabled\"=? where \"id\"=?";
            preparedStmt = c.prepareStatement(SQL);
            preparedStmt.setBoolean(1, dto.isSuperuser());

            preparedStmt.setBoolean(2, dto.isEnabled());
            preparedStmt.setInt(3, dto.getId());
            preparedStmt.executeUpdate();

            TransactionRecordFacade tFac = new TransactionRecordFacade();
            TransactionRecordDTO tDto = new TransactionRecordDTO();
            tDto.getObjectDTO().setId(dto.getId());
            tDto.getTransactionTypeDTO().setId(2);
            tDto.getUsuarioDTO().setId(dto.getCreatedBy());
            tFac.createTransactionRecord(tDto);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return dto;
    }
    PropertiesFacade propertiesFacade = null;
    areaFacade fac = null;

    @Override
    public ArrayList<areaDTO> crearCarpetasAPartirDeAreas(ArrayList<areaDTO> list) {
        
        try {
            propertiesFacade = new PropertiesFacade();
            for (areaDTO dto : list) {
                
                String pathForFiles = propertiesFacade.obtenerValorPropiedad("pathForFiles") + dto.getName();
                String pathForTrash = propertiesFacade.obtenerValorPropiedad("pathForTrash") + dto.getName();

                File files = new File(pathForFiles);
                File trash = new File(pathForTrash);
                if (!files.exists() && !trash.exists()) {
                    files.mkdirs();
                    trash.mkdirs();
                }
                else{
                    FilesFacade fFac = new FilesFacade();
                    pathForFiles = fFac.retornaNombreBienParaCarpeta(pathForFiles);
                    pathForTrash = fFac.retornaNombreBienParaCarpeta(pathForTrash);
                        files = new File(pathForFiles);
                        trash = new File(pathForTrash);
                        files.mkdirs();
                        trash.mkdirs();
                        
                }
                String folderName = pathForFiles.substring(pathForFiles.lastIndexOf("/")+1, pathForFiles.length());
                //System.out.println("folderName : " + folderName);
                dto.setFolderName(folderName);
                
                fac = new areaFacade ();
                fac.asignarFolderName(dto);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public areaDTO asignarFolderName(areaDTO dto) {
         PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs = null;
        
         try {
          c = DataSource.getInstance().getConnection(); 
            String SQL = "update \"public\".\"area\" set \"folderName\"=? where \"id\"=?";
            preparedStmt = c.prepareStatement(SQL);
            preparedStmt.setString(1, dto.getFolderName());
            preparedStmt.setInt(2, dto.getId());
            preparedStmt.executeUpdate();
           
            
           

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return dto;
    }

    @Override
    public areaDTO getAreaByID(areaDTO dto) {
         
        
        
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
        
          try {
          c = DataSource.getInstance().getConnection(); 
           String SQL = "SELECT \"area\".\"id\", \"area\".\"superuser\", \"area\".\"enabled\", \"object\".\"name\", \"area\".\"folderName\" FROM \"area\" JOIN \"object\" ON \"area\".\"id\" = \"object\".\"id\" where \"area\".\"id\" = ?";
            ps = c.prepareStatement(SQL);
            ps.setInt(1, dto.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                dto = new areaDTO();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setFolderName(rs.getString("folderName"));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return dto;
    }

}
