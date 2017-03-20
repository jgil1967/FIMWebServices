/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.document;

import com.uas.Files.FilesFacade;
import com.uas.areas.areaDTO;
import com.uas.areas.areaFacade;
import com.uas.dates.filters.filtersDTO.FiltersDTO;
import com.uas.dbutil.DataSource;
import com.uas.dbutil.DataSourceSingleton;
import com.uas.dbutil.getTomcatDataSource;
import com.uas.documentRelationship.DocumentRelationshipDTO;
import com.uas.documentRelationship.DocumentRelationshipFacade;
import com.uas.keyword.KeywordFacade;
import com.uas.object.ObjectFacade;

import com.uas.properties.PropertiesFacade;
import com.uas.transactionRecord.TransactionRecordDTO;
import com.uas.transactionRecord.TransactionRecordFacade;
import com.uas.usuarios.UsuarioDTO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathangil
 */
public class DocumentDAO implements DocumentInterface {
KeywordFacade kFac = null;

    @Override
    public DocumentDTOWithFolderDTO getDocument(DocumentDTOWithFolderDTO document) {
   
      ascendientes = new ArrayList<Boolean>();
        PropertiesFacade pDto = new PropertiesFacade();
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       DocumentDTOWithFolderDTO dtoNew=null;
           try {
          c = DataSourceSingleton.getInstance().getConnection(); 
           String SQL = "SELECT \"object\".\"id\", \"object\".\"name\", \"document\".\"fileName\", \"document\".\"isFolder\", \"document\".\"deleted\", \"document\".\"fileDate\", \"document\".\"backedUp\", \"document\".\"idArea\", \"object3\".\"name\" AS \"nameArea\", \"area\".\"folderName\" FROM \"document\" JOIN \"object\" ON \"document\".\"id\" = \"object\".\"id\" JOIN \"object\" AS \"object2\" ON \"object\".\"createdBy\" = \"object2\".\"id\" JOIN \"area\" ON \"document\".\"idArea\" = \"area\".\"id\" JOIN \"object\" AS \"object3\" ON \"area\".\"id\" = \"object3\".\"id\" WHERE \"object\".\"id\" = ?";
               ps = c.prepareStatement(SQL);
               ps.setInt(1,document.getId());
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       dtoNew = new DocumentDTOWithFolderDTO();
                       dtoNew.setIsFolder(rs.getBoolean("isFolder"));
                       dtoNew.setId(rs.getInt("id"));
                       dtoNew.setName(rs.getString("name"));
                       dtoNew.setFileDate(rs.getString("fileDate"));
                        dtoNew.setFilename(rs.getString("filename"));
                        dtoNew.setDeleted(rs.getBoolean("deleted"));
                        dtoNew.setAscendenteBorrado(rs.getBoolean("deleted"));
                    dtoNew.setIdArea(rs.getInt("idArea"));
                   dtoNew.getArea().setName(rs.getString("nameArea"));
                   dtoNew.getArea().setFolderName(rs.getString("folderName"));
                   dtoNew.setBackedUp(rs.getBoolean("backedUp"));
                   
                        String pathTrash = pDto.obtenerValorPropiedad("pathForTrash");
                       String fullPathOriginal =  pDto.obtenerValorPropiedad("pathForFiles");
                    System.out.println("#######################");
                        savingUp = "";
                        getFullPath(dtoNew,dtoNew.getFilename());
                        String fp = savingUp;
                        System.out.println("ascendientes : " + ascendientes.size());
                        for(Boolean b : ascendientes){
                            if (b){
                                dtoNew.setAscendenteBorrado(true);
                                break;
                            }
                        }
                        
                       System.out.println("FP : " + fp);
                       System.out.println("#######################");
                    dtoNew.setFullPathToFolder(  fullPathOriginal  + dtoNew.getArea().getFolderName()   + "/"+fp);
                    dtoNew.setFullPathToFolderInDeleted(pathTrash  + dtoNew.getArea().getFolderName()   + "/"+fp);
                    System.out.println("setFullPathToFolder : " + dtoNew.getFullPathToFolder());
                     System.out.println("setFullPathToFolderInDeleted : " + dtoNew.getFullPathToFolderInDeleted());
                   
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
        return dtoNew;
    
    }

    @Override
    public ArrayList<DocumentDTOWithFolderDTO> getDocuments() {
       kFac = new KeywordFacade ();
        ArrayList<DocumentDTOWithFolderDTO> documents = null;
     DocumentDTOWithFolderDTO document = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       documents = new ArrayList<DocumentDTOWithFolderDTO> ();
         try {
          c = DataSourceSingleton.getInstance().getConnection(); 
           String SQL = "SELECT \"object\".\"id\",\"object\".\"createdBy\", \"object\".\"name\", \"object\".\"description\", \"object\".\"createdOn\", \"object\".\"createdBy\", \"object\".\"color\", \"object\".\"kind\", \"document\".\"fileName\", \"document\".\"isFolder\", \"document\".\"fileDate\", \"document\".\"deleted\", \"document\".\"backedUp\", \"document\".\"idArea\", \"object3\".\"name\" AS \"nameArea\", \"object2\".\"name\" AS \"nameCreatedBy\" FROM \"document\" JOIN \"object\" ON \"document\".\"id\" = \"object\".\"id\" JOIN \"object\" AS \"object3\" ON \"document\".\"idArea\" = \"object3\".\"id\" JOIN \"object\" AS \"object2\" ON \"object\".\"createdBy\" = \"object2\".\"id\"  WHERE  \"object\".\"id\" NOT IN (SELECT \"documentRelationships\".\"idDocumentChild\" FROM \"documentRelationships\")  ORDER BY \"object\".\"createdOn\" ASC";
               ps = c.prepareStatement(SQL);
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       document = new DocumentDTOWithFolderDTO();
                       document.setIsFolder(rs.getBoolean("isFolder"));
                       document.setId(rs.getInt("id"));
                       document.setCreatedBy(rs.getInt("createdBy"));
                       document.setName(rs.getString("name"));
                       document.setDescription(rs.getString("description"));
                        document.setColor(rs.getString("color"));
                        document.setCreatedOn(rs.getTimestamp("createdOn"));
                        document.setKind(rs.getString("kind"));
                        document.setFilename(rs.getString("filename"));
                        document.setKeywords(kFac.getKeywordsByDocument(document));
                    document.setFileDate(rs.getString("fileDate"));
               document.setDeleted(rs.getBoolean("deleted"));
                    document.setIdArea(rs.getInt("idArea"));
                   document.getArea().setName(rs.getString("nameArea"));
                   document.getUser().setName(rs.getString("nameCreatedBy"));
                   document.setBackedUp(rs.getBoolean("backedUp"));
                  document.setVengoDeRootYPuedoCambiarDeArea(true);
                   
                      documents.add(document);
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
        return documents;
    }

    @Override
    public DocumentDTOWithFolderDTO createDocument(DocumentDTOWithFolderDTO dDto) {
      PreparedStatement preparedStmt = null;
        Connection c = null;
        ResultSet rs =null;
        // 
       try {
          c = DataSourceSingleton.getInstance().getConnection(); 
          String SQL = "INSERT INTO \"public\".\"document\" (\"id\",\"fileName\",\"fileDate\",\"idArea\",\"isFolder\") VALUES (?,?,?,?,?)";
     	preparedStmt = c.prepareStatement(SQL);
         preparedStmt.setInt(1, dDto.getId());
             System.out.println("");
            preparedStmt.setString(2, dDto.getFilename());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
             //System.out.println("Dto.getFileDate().substring(0,10): " + dDto.getFileDate().substring(0,10));
            java.util.Date parsedDate = dateFormat.parse(dDto.getFileDate().substring(0,10));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            preparedStmt.setTimestamp(3, timestamp);
            preparedStmt.setInt(4, dDto.getIdArea());
            preparedStmt.setBoolean(5, dDto.getIsFolder());
          preparedStmt.executeUpdate();
             
               TransactionRecordFacade tFac = new TransactionRecordFacade();
             TransactionRecordDTO tDto = new TransactionRecordDTO();
             tDto.getObjectDTO().setId(dDto.getId());
             tDto.getTransactionTypeDTO().setId(3);
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
    return dDto;  }

    

    @Override
    public ArrayList<DocumentDTO> searchDocuments(DocumentDTO dDto) {
          kFac = new KeywordFacade ();
        ArrayList<DocumentDTO> documents = null;
     DocumentDTO document = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       documents = new ArrayList<DocumentDTO> ();
          try {
          c = DataSourceSingleton.getInstance().getConnection(); 
           String SQL = "SELECT \"object\".\"id\", \"object\".\"name\", \"object\".\"description\", \"object\".\"createdOn\", \"object\".\"createdBy\", \"object\".\"color\", \"object\".\"kind\", \"document\".\"fileName\", \"document\".\"idArea\" FROM \"document\" JOIN \"object\" ON \"document\".\"id\" = \"object\".\"id\"  where \"object\".\"name\" ILIKE ? or \"object\".\"description\" ILIKE ?";
               ps = c.prepareStatement(SQL);
               ps.setString(1, "%"+dDto.getQuery() + "%");
               ps.setString(2, "%"+dDto.getQuery() + "%");
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       document = new DocumentDTO();
                       document.setId(rs.getInt("id"));
                       document.setName(rs.getString("name"));
                       document.setDescription(rs.getString("description"));
                        document.setColor(rs.getString("color"));
                        document.setCreatedOn(rs.getTimestamp("createdOn"));
                        document.setKind(rs.getString("kind"));
                        document.setFilename(rs.getString("filename"));
                        document.setKeywords(kFac.getKeywordsByDocument(document));
                      documents.add(document);
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
        return documents;}

    @Override
    public DocumentDTOWithFolderDTO updateDocument(DocumentDTOWithFolderDTO dDto) {
     
        
        System.out.println("dDto.getDeleted() : " + dDto.getDeleted());
        DocumentDTO dtoViejo = getDocument(dDto);
        
            DocumentDTO objectDto = null;
               
     
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement preparedStmt = null;
     
          try {
          c = DataSourceSingleton.getInstance().getConnection(); 
            String SQL = "update \"public\".\"document\" set \"fileDate\"=?,\"deleted\"=?,\"backedUp\"=?,\"idArea\"=? where \"id\"=? ";
                preparedStmt = c.prepareStatement(SQL);
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dDto.getFileDate().substring(0,10));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            preparedStmt.setTimestamp(1, timestamp);
            preparedStmt.setBoolean(2, dDto.getDeleted());
             System.out.println("dDto.getDeleted() : " + dDto.getDeleted());
            preparedStmt.setBoolean(3, dDto.getBackedUp());
            preparedStmt.setInt(4, dDto.getIdArea());
            preparedStmt.setInt(5, dDto.getId());
                preparedStmt.executeUpdate();
                System.out.println("dDto.getVengoDeRootYPuedoCambiarDeArea() : " + dDto.getVengoDeRootYPuedoCambiarDeArea());
                if ((dtoViejo.getIdArea() != dDto.getIdArea()) && (dDto.getVengoDeRootYPuedoCambiarDeArea())){
                     DocumentDTO dtoNuevo = getDocument(dDto);
                     if (dDto.getDeleted()){
                          Files.createDirectories(Paths.get(dtoNuevo.getFullPathToFolderInDeleted()).getParent()); 
                            Files.move(Paths.get(dtoViejo.getFullPathToFolderInDeleted()), Paths.get(dtoNuevo.getFullPathToFolderInDeleted()));
                             }
                     else{
                         //Si 
                         Files.createDirectories(Paths.get(dtoNuevo.getFullPathToFolder()).getParent()); 
                         Files.move(Paths.get(dtoViejo.getFullPathToFolder()), Paths.get(dtoNuevo.getFullPathToFolder()));
                        }
                      }
              /* if (!dDto.getBackedUp()){
             TransactionRecordFacade tFac = new TransactionRecordFacade();
             TransactionRecordDTO tDto = new TransactionRecordDTO();
             tDto.getObjectDTO().setId(dDto.getId());
             tDto.getTransactionTypeDTO().setId(4);
             tDto.getUsuarioDTO().setId(dDto.getCreatedBy());
             tFac.createTransactionRecord(tDto);
               }
   */
               
              
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
         return dDto; }

    @Override
    public ArrayList<DocumentDTOWithFolderDTO> getDocuments(FiltersDTO filters) {
     PreparedStatement ps = null;
             Connection c = null;
        ResultSet rs =null;
        ArrayList<DocumentDTOWithFolderDTO> documents = null;
        
        try{
             
       //System.out.println("Keywords : "+ filters.getKeywords().size());
        //System.out.println(filters.toString());
        //System.out.println("QUery : " + filters.getFilterQuery());
        String SQL = "";
        
        
//        if (filters.getKeywords().size()>0){
//            System.out.println("Hay keywords");
//            SQL += "SELECT DISTINCT ON( \"documentKeywordRelationship\".\"idDocument\") \"documentKeywordRelationship\".\"idDocument\", \"object\".\"name\", \"documentKeywordRelationship\".\"idKeyword\", \"document\".\"id\", \"document\".\"fileName\", \"document\".\"idArea\", \"document\".\"fileDate\", \"object2\".\"id\" AS \"id_0\", \"object2\".\"name\" AS \"name_0\", \"object2\".\"description\", \"object2\".\"createdOn\", \"object2\".\"createdBy\", \"object2\".\"color\", \"object2\".\"kind\" FROM \"documentKeywordRelationship\" JOIN \"keyword\" ON \"documentKeywordRelationship\".\"idKeyword\" = \"keyword\".\"id\" JOIN \"object\" ON \"keyword\".\"id\" = \"object\".\"id\" JOIN \"document\" ON \"documentKeywordRelationship\".\"idDocument\" = \"document\".\"id\" JOIN \"object\" AS \"object2\" ON \"document\".\"id\" = \"object2\".\"id\" ";
//        }
        //|| !filters.getFilterQuery().equals("") 
          if (filters.getKeywords().size()>0 ||filters.getDates().getOldestCreatedOn() != null || filters.getDates().getNewestCreatedOn() != null|| filters.getDates().getOldestFileDate() != null|| filters.getDates().getNewestFileDate() != null){
               SQL += "SELECT \"documentKeywordRelationship\".\"idDocument\", \"object\".\"createdBy\", \"object\".\"name\" AS \"keywordName\", \"documentKeywordRelationship\".\"idKeyword\", \"document\".\"id\", \"document\".\"fileName\", \"document\".\"isFolder\", \"document\".\"idArea\", \"document\".\"fileDate\", \"object2\".\"id\" AS \"id_0\", \"object2\".\"name\" AS \"name\", \"object2\".\"description\", \"object2\".\"createdOn\", \"object2\".\"createdBy\" AS \"createdBy_0\", \"object2\".\"color\", \"object2\".\"kind\", \"object3\".\"name\" AS \"nameArea\", \"object_alias1\".\"name\" AS \"nameCreatedBy\" FROM \"documentKeywordRelationship\" JOIN \"keyword\" ON \"documentKeywordRelationship\".\"idKeyword\" = \"keyword\".\"id\" JOIN \"object\" ON \"keyword\".\"id\" = \"object\".\"id\" JOIN \"document\" ON \"documentKeywordRelationship\".\"idDocument\" = \"document\".\"id\" JOIN \"object\" AS \"object2\" ON \"document\".\"id\" = \"object2\".\"id\" JOIN \"object\" AS \"object3\" ON \"document\".\"idArea\" = \"object3\".\"id\" JOIN \"object\" AS \"object_alias1\" ON \"object2\".\"createdBy\" = \"object_alias1\".\"id\" ";
        
              SQL +=" where ";
          }
          
          for (int i = 0;i<filters.getKeywords().size();i++){
              SQL = SQL + "\"documentKeywordRelationship\".\"idKeyword\" = "+filters.getKeywords().get(i).getId()+" ";
            
             if (i<filters.getKeywords().size()-1){
                  SQL = SQL + " OR ";
              }
          }
//!filters.getFilterQuery().equals("") || 
          
 if (filters.getKeywords().size()>0 && (filters.getDates().getOldestCreatedOn()!=null||filters.getDates().getNewestCreatedOn() != null|| filters.getDates().getOldestFileDate() != null|| filters.getDates().getNewestFileDate() != null)){
                SQL =SQL + "AND";
            }
            System.out.println("filters.getDates().getOldestCreatedOn() : " + filters.getDates().getOldestCreatedOn());
 if (filters.getDates().getOldestCreatedOn() != null){
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(filters.getDates().getOldestCreatedOn().substring(0,10));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            System.out.println("1 " + timestamp);
            SQL =SQL + " \"object\".\"createdOn\" >=  \'"+timestamp+"\' ";
            if (filters.getDates().getNewestCreatedOn() != null|| filters.getDates().getOldestFileDate() != null|| filters.getDates().getNewestFileDate() != null){
                SQL =SQL + " AND ";
            }
        }
        if (filters.getDates().getNewestCreatedOn() != null){
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(filters.getDates().getNewestCreatedOn().substring(0,10));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
             
            System.out.println("2  " + timestamp);
            SQL =SQL +" \"object\".\"createdOn\" <= \'"+timestamp+"\' ";
             if ( filters.getDates().getOldestFileDate() != null|| filters.getDates().getNewestFileDate() != null){
                SQL =SQL + " AND ";
            }
        }
       
        if (filters.getDates().getOldestFileDate()!= null){
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(filters.getDates().getOldestFileDate().substring(0,10));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
             
            System.out.println("3 " + timestamp);
            SQL =SQL +" \"document\".\"fileDate\" >=  \'"+timestamp+"\' ";
             if (  filters.getDates().getNewestFileDate() != null){
                SQL =SQL + " AND ";
            }
        }
        if (filters.getDates().getNewestFileDate() != null){
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(filters.getDates().getNewestFileDate().substring(0,10));
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
             
            System.out.println("4 " + timestamp);
            SQL =SQL +" \"document\".\"fileDate\" <=  \'"+timestamp+"\' ";
        }
        
       System.out.println("SQL : " + SQL);
        if (!SQL.equals("")){
        DocumentDTOWithFolderDTO document = null;
             
          c = DataSourceSingleton.getInstance().getConnection(); 
         
        ps = c.prepareStatement(SQL);
        rs = ps.executeQuery();
        kFac = new KeywordFacade ();
        documents = new ArrayList<DocumentDTOWithFolderDTO> ();
         while (rs.next()) {
                       document = new DocumentDTOWithFolderDTO();
                       document.setId(rs.getInt("id"));
                       document.setCreatedBy(rs.getInt("createdBy"));
                        document.setIsFolder(rs.getBoolean("isFolder"));
                       document.setName(rs.getString("name"));
                       document.setDescription(rs.getString("description"));
                        document.setColor(rs.getString("color"));
                        document.setCreatedOn(rs.getTimestamp("createdOn"));
                        document.setKind(rs.getString("kind"));
                        document.setFilename(rs.getString("filename"));
                        document.setFileDate(rs.getString("fileDate"));
                        document.setIdArea(rs.getInt("idArea"));
                        document.setKeywords(kFac.getKeywordsByDocument(document));
                         document.getArea().setName(rs.getString("nameArea"));
               document.getUser().setName(rs.getString("nameCreatedBy"));
                      documents.add(document);
                   }
            System.out.println("documents size : " + documents.size());
         return documents;
        }
        else{
           // System.out.println("regresando getDocuments");
            return getDocuments();
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
        
        return documents;
    }
    
    public Boolean getVisible (int idArea, ArrayList<areaDTO> areas){
        for (areaDTO a : areas){
         if (a.getId() == idArea){
             return a.isUploadAndEdit();
         }   
        }
        return false;
    }
    
//SELECT "object"."id", "object"."createdBy", "object"."name", "object"."description", "object"."createdOn", "object"."createdBy", "object"."color", "object"."kind", "document"."fileName", "document"."fileDate", "document"."idArea", "object2"."name" AS "nameCreatedBy", "object3"."name" AS "nameArea", "area"."enabled", "area"."enabled" FROM "document" JOIN "object" ON "document"."id" = "object"."id" JOIN "object" AS "object2" ON "object"."createdBy" = "object2"."id" JOIN "area" ON "document"."idArea" = "area"."id" JOIN "object" AS "object3" ON "area"."id" = "object3"."id" ORDER BY "object"."createdOn" ASC WHERE "area"."enabled" = TRUE
    @Override
    public ArrayList<DocumentDTO> getDocumentsOnlyEnabled(ArrayList<areaDTO> areas) {
        System.out.println("Only Enabled");
        System.out.println("areas : " + areas.size());
     kFac = new KeywordFacade ();
        ArrayList<DocumentDTO> documents = null;
     DocumentDTO document = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       documents = new ArrayList<DocumentDTO> ();
         try{
               c = DataSourceSingleton.getInstance().getConnection(); 
               String SQL = "SELECT \"object\".\"id\", \"object\".\"createdBy\", \"object\".\"name\", \"object\".\"description\", \"object\".\"createdOn\", \"object\".\"createdBy\", \"object\".\"color\", \"object\".\"kind\", \"document\".\"fileName\", \"document\".\"fileDate\", \"document\".\"isFolder\", \"document\".\"idArea\", \"object2\".\"name\" AS \"nameCreatedBy\", \"object3\".\"name\" AS \"nameArea\", \"area\".\"enabled\", \"area\".\"enabled\" FROM \"document\" JOIN \"object\" ON \"document\".\"id\" = \"object\".\"id\" JOIN \"object\" AS \"object2\" ON \"object\".\"createdBy\" = \"object2\".\"id\" JOIN \"area\" ON \"document\".\"idArea\" = \"area\".\"id\" JOIN \"object\" AS \"object3\" ON \"area\".\"id\" = \"object3\".\"id\" WHERE \"area\".\"enabled\" = TRUE AND  \"document\".\"deleted\" = FALSE ";
              if (areas.size()>0){
                 SQL = SQL + " AND ("; 
              }
               for (int a=0;a<areas.size();a++){
                 //  System.out.println("area : " + areas.get(a).getName() + "  " + areas.get(a).isUploadAndEdit());
                  SQL = SQL + "\"document\".\"idArea\" = " + areas.get(a).getId();
                  if (!(a == areas.size()-1)){
                      SQL = SQL + " OR ";
                  }
                  else{
                      SQL = SQL + " ) ";
                  }
              }
               ps = c.prepareStatement(SQL);
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       document = new DocumentDTO();
                       document.setId(rs.getInt("id"));
                       document.setCreatedBy(rs.getInt("createdBy"));
                        document.setIsFolder(rs.getBoolean("isFolder"));
                       document.setName(rs.getString("name"));
                       document.setDescription(rs.getString("description"));
                        document.setColor(rs.getString("color"));
                        document.setCreatedOn(rs.getTimestamp("createdOn"));
                        document.setKind(rs.getString("kind"));
                        document.setFilename(rs.getString("filename"));
                        document.setKeywords(kFac.getKeywordsByDocument(document));
                    document.setFileDate(rs.getString("fileDate"));
         
                    document.setIdArea(rs.getInt("idArea"));
                         document.setVisible(getVisible(rs.getInt("idArea"), areas));
               document.getArea().setName(rs.getString("nameArea"));
               document.getUser().setName(rs.getString("nameCreatedBy"));
                      documents.add(document);
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
        return documents;}

    @Override
    public ArrayList<DocumentDTO> getDocumentsByUser(UsuarioDTO dto) {
       kFac = new KeywordFacade ();
        ArrayList<DocumentDTO> documents = null;
     DocumentDTO document = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       documents = new ArrayList<DocumentDTO> ();
         try{
              c = DataSourceSingleton.getInstance().getConnection(); 
               String SQL = "SELECT \"object\".\"id\",\"object\".\"createdBy\", \"object\".\"name\", \"object\".\"description\", \"object\".\"createdOn\", \"object\".\"createdBy\", \"object\".\"color\", \"object\".\"kind\", \"document\".\"fileName\", \"document\".\"isFolder\", \"document\".\"deleted\", \"document\".\"fileDate\", \"document\".\"idArea\", \"object3\".\"name\" AS \"nameArea\", \"object2\".\"name\" AS \"nameCreatedBy\" FROM \"document\" JOIN \"object\" ON \"document\".\"id\" = \"object\".\"id\" JOIN \"object\" AS \"object3\" ON \"document\".\"idArea\" = \"object3\".\"id\" JOIN \"object\" AS \"object2\" ON \"object\".\"createdBy\" = \"object2\".\"id\" where \"object\".\"createdBy\" = ?  ORDER BY \"object\".\"createdOn\" ASC";
               ps = c.prepareStatement(SQL);
                   ps.setInt(1, dto.getId()); 
               rs = ps.executeQuery();
                   while (rs.next()) {
                       document = new DocumentDTO();
                       document.setId(rs.getInt("id"));
                       document.setCreatedBy(rs.getInt("createdBy"));
                        document.setIsFolder(rs.getBoolean("isFolder"));
                       document.setName(rs.getString("name"));
                       document.setDescription(rs.getString("description"));
                        document.setColor(rs.getString("color"));
                           document.setDeleted(rs.getBoolean("deleted"));
                        document.setCreatedOn(rs.getTimestamp("createdOn"));
                        document.setKind(rs.getString("kind"));
                        document.setFilename(rs.getString("filename"));
                        document.setKeywords(kFac.getKeywordsByDocument(document));
                    document.setFileDate(rs.getString("fileDate"));
                    document.setIdArea(rs.getInt("idArea"));
                   document.getArea().setName(rs.getString("nameArea"));
                   document.getUser().setName(rs.getString("nameCreatedBy"));
                      documents.add(document);
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
        return documents;}
    
    
    List <Boolean > ascendientes = null;
    String fullPath = "";
    String savingUp = "";
    public String getFullPath (DocumentDTO dto, String aSumar){
      aSumar = dto.getFilename();
        System.out.println("a sumar: " + aSumar);
       if (aSumar.length()>savingUp.length()){
           savingUp = aSumar;
           System.out.println("savingUp : " + savingUp);
       }
        System.out.println(" dto.getId() : " + dto.getId() + " " + dto.getFilename());
     ////########## VERIFICAR SI TIENE PADRES
     DocumentDTOWithFolderDTO document = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       
       try{
             c = DataSourceSingleton.getInstance().getConnection(); 
               String SQL = "SELECT \"documentRelationships\".\"idDocumentChild\", \"documentRelationships\".\"idDocumentParent\", \"document\".\"fileName\", \"document\".\"deleted\" FROM \"documentRelationships\" JOIN \"document\" ON \"documentRelationships\".\"idDocumentParent\" = \"document\".\"id\" WHERE \"documentRelationships\".\"idDocumentChild\" = ?";
               ps = c.prepareStatement(SQL);
               ps.setInt(1, dto.getId()); 
                   ps.setInt(1, dto.getId()); 
               rs = ps.executeQuery();

if (rs.next()) {
    do {
                       document = new DocumentDTOWithFolderDTO();
                   document.setId(rs.getInt("idDocumentParent"));
                   document.setFilename(rs.getString("fileName") + "/" +aSumar);
                   aSumar =rs.getString("fileName") + "/" +aSumar;
                  System.out.println("rs.getBoolean(\"deleted\") : " + rs.getBoolean("deleted"));
                   ascendientes.add(rs.getBoolean("deleted"));
                   System.out.println("document.getFilename() : " + document.getFilename());
                   getFullPath(document,document.getFilename());
    } while(rs.next());
} else {
    return aSumar;
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
       //setFileName el aSumar
       ////########## VERIFICAR SI TIENE PADRES
       System.out.println("a sumar antes de regresar: " + aSumar);
        return aSumar;
        
    }

    @Override
    public ArrayList<DocumentDTOWithFolderDTO> getDocumentsByFolder(DocumentDTOWithFolderDTO dto) {
        
        
     kFac = new KeywordFacade ();
        ArrayList<DocumentDTOWithFolderDTO> documents = null;
     DocumentDTOWithFolderDTO document = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
       documents = new ArrayList<DocumentDTOWithFolderDTO> ();
         try{
             c = DataSourceSingleton.getInstance().getConnection(); 
               String SQL = "SELECT \"document\".\"id\", \"document\".\"fileName\", \"document\".\"fileDate\", \"document\".\"idArea\", \"document\".\"deleted\", \"document\".\"backedUp\", \"document\".\"isFolder\", \"object\".\"name\", \"object\".\"description\", \"object\".\"createdOn\", \"object\".\"createdBy\", \"object\".\"kind\", \"documentRelationships\".\"idDocumentChild\", \"documentRelationships\".\"idDocumentParent\", \"object2\".\"name\" AS \"nameArea\", \"object3\".\"name\" AS \"nameCreatedBy\" FROM \"documentRelationships\" JOIN \"document\" ON \"documentRelationships\".\"idDocumentChild\" = \"document\".\"id\" JOIN \"object\" ON \"document\".\"id\" = \"object\".\"id\" JOIN \"area\" ON \"document\".\"idArea\" = \"area\".\"id\" JOIN \"object\" AS \"object2\" ON \"area\".\"id\" = \"object2\".\"id\" JOIN \"usuario\" ON \"object\".\"createdBy\" = \"usuario\".\"id\" JOIN \"object\" AS \"object3\" ON \"usuario\".\"id\" = \"object3\".\"id\" WHERE \"documentRelationships\".\"idDocumentParent\" = ?";
               ps = c.prepareStatement(SQL);
                ps.setInt(1, dto.getId()); 
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       document = new DocumentDTOWithFolderDTO();
                       document.setIsFolder(rs.getBoolean("isFolder"));
                       document.setId(rs.getInt("id"));
                       document.setCreatedBy(rs.getInt("createdBy"));
                       document.setName(rs.getString("name"));
                       document.setDescription(rs.getString("description"));
                        document.setCreatedOn(rs.getTimestamp("createdOn"));
                        document.setKind(rs.getString("kind"));
                        document.setFilename(rs.getString("filename"));
                        document.setKeywords(kFac.getKeywordsByDocument(document));
                    document.setFileDate(rs.getString("fileDate"));
               document.setDeleted(rs.getBoolean("deleted"));
                    document.setIdArea(rs.getInt("idArea"));
                   document.getArea().setName(rs.getString("nameArea"));
                   document.getUser().setName(rs.getString("nameCreatedBy"));
                   document.setBackedUp(rs.getBoolean("backedUp"));
                   documents.add(document);
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
        return documents;}

  

    @Override
    public DocumentDTOWithFolderDTO createFolder(DocumentDTOWithFolderDTO document) {
        System.out.println("Carpeta padre : " + document.getFolder().getId());
        System.out.println("Carpeta nombre : " + document.getName());
        System.out.println("Carpeta area ID : " + document.getIdArea());
        PropertiesFacade pDto = new PropertiesFacade();
        System.out.println("padre - id : " + document.getFolder().getId() );
       Boolean ascendienteBorrado = false;
       DocumentDTOWithFolderDTO dtoFolder = null;
        fullPath = getFullPath(document,document.getFilename());
        if (document.getFolder().getId() != 0){
            ascendientes = new ArrayList<Boolean>();
            System.out.println("Estamos hablando que esta dentro de una carpeta");
            /////////////
            dtoFolder = new DocumentDTOWithFolderDTO();
            dtoFolder.setId(document.getFolder().getId());
            DocumentFacade fac = new DocumentFacade();
            //Obtenemos info de la carpeta 
            dtoFolder = fac.getDocument(dtoFolder);
            /////////////
            ascendientes.add(dtoFolder.getDeleted());
            dtoFolder.setFilename(dtoFolder.getFilename() + "/"+document.getFilename());
            System.out.println("dtoFolder : " + dtoFolder.getFilename());
            
             savingUp = "";
             getFullPath(dtoFolder,dtoFolder.getFilename());
            fullPath = savingUp;
            
            
            System.out.println("fullPath : " + fullPath);
             for (Boolean b : ascendientes){
                 if (b){
                     ascendienteBorrado = true;
                 }
                }
        }
                
       
        areaFacade aFac = new areaFacade();
        areaDTO aDto = new areaDTO();
        aDto.setId(document.getIdArea());
        aDto = aFac.getAreaByID(aDto);
        String carpetaDestinoParaGrabar = "";
        //////////////////////////////////////////////////////
        if (ascendienteBorrado){
             carpetaDestinoParaGrabar = pDto.obtenerValorPropiedad("pathForTrash");
        }
        else{
              carpetaDestinoParaGrabar =  pDto.obtenerValorPropiedad("pathForFiles");
             }
          document.setFullPathToFolder( carpetaDestinoParaGrabar  + aDto.getFolderName() + "/"+fullPath);
         FilesFacade fFac = new FilesFacade();
      
        if (fFac.verificaSiExiste(document.getFullPathToFolder())){
             document.setFullPathToFolder(fFac.retornaNombreBienParaCarpeta(document.getFullPathToFolder())); ;
          }
         File files = new File(document.getFullPathToFolder());
         files.mkdirs();
        ObjectFacade  oFac = new ObjectFacade();
         document.setId(oFac.createObject(document).getId()); 
      DocumentFacade dFac = new DocumentFacade();
       document =  dFac.createDocument(document);
     if (document.getFolder().getId() != 0){
       DocumentRelationshipFacade  drfac = new DocumentRelationshipFacade();
       DocumentRelationshipDTO dDto = new DocumentRelationshipDTO ();
       dDto.setIdDocumentChild(document.getId());
       dDto.setIdDocumentParent(dtoFolder.getId());
       drfac.createDocumentRelationship(dDto);
       }
       return document;
        
    }

    @Override
    public DocumentDTOWithFolderDTO createDocument2(DocumentDTOWithFolderDTO document) {
         System.out.println("Carpeta padre : " + document.getFolder().getId());
        System.out.println("Carpeta nombre : " + document.getName());
        System.out.println("Filename : " + document.getFilename());
        System.out.println("Carpeta area ID : " + document.getIdArea());
        System.out.println("#########################################");
        PropertiesFacade pFac = new PropertiesFacade();
        fullPath = "";
        ascendientes  = new ArrayList <Boolean > ();
        Boolean ascendienteBorrado = false;
        savingUp = "";
         getFullPath(document,document.getFilename());
         fullPath =savingUp;
         System.out.println("fullPath archivo : " + fullPath);
         DocumentDTOWithFolderDTO dtoFolder = null;
          if (document.getFolder().getId() != 0){
             ascendientes = new ArrayList<Boolean>();
            dtoFolder = new DocumentDTOWithFolderDTO();
            dtoFolder.setId(document.getFolder().getId());
            DocumentFacade fac = new DocumentFacade();
            //Obtenemos info de la carpeta 
            dtoFolder = fac.getDocument(dtoFolder);
            ascendientes.add(dtoFolder.getDeleted());
            dtoFolder.setFilename(dtoFolder.getFilename() + "/"+document.getFilename());
            System.out.println("dtoFolder : " + dtoFolder.getFilename());
            savingUp = "";
            getFullPath(dtoFolder,dtoFolder.getFilename());
            fullPath = savingUp;
            System.out.println("fullPath : " + fullPath);
             for (Boolean b : ascendientes){
                 if (b){
                     ascendienteBorrado = true;
                 }
                }
          }
        areaFacade aFac = new areaFacade();
          areaDTO aDto = new areaDTO();
          aDto.setId(document.getIdArea());
          aDto  = aFac.getAreaByID(aDto);
         File afile =new File(pFac.obtenerValorPropiedad("pathForTemporaryFiles") + document.getFilename());
         String carpetaDestinoParaGrabar = "";
        //////////////////////////////////////////////////////
                if (ascendienteBorrado){
             carpetaDestinoParaGrabar = pFac.obtenerValorPropiedad("pathForTrash");
        }
        else{
              carpetaDestinoParaGrabar =  pFac.obtenerValorPropiedad("pathForFiles");
             }
         document.setFullPathToFolder( carpetaDestinoParaGrabar  + aDto.getFolderName()  + "/"+fullPath);
         
         afile.renameTo(new File(document.getFullPathToFolder()));
        
         ObjectFacade  oFac = new ObjectFacade();
         document.setId(oFac.createObject(document).getId()); 
      DocumentFacade dFac = new DocumentFacade();
       document =  dFac.createDocument(document);
if (document.getFolder().getId() != 0){
       DocumentRelationshipFacade  drfac = new DocumentRelationshipFacade();
       DocumentRelationshipDTO dDto = new DocumentRelationshipDTO ();
       dDto.setIdDocumentChild(document.getId());
       dDto.setIdDocumentParent(dtoFolder.getId());
       drfac.createDocumentRelationship(dDto);
       }
        return document;
    }

    @Override
    public ArrayList<DocumentDTO> getFolders() {
         ascendientes = new ArrayList<Boolean>();
        ArrayList<DocumentDTO> documents = null;
     DocumentDTO document = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
           PropertiesFacade pFac = null;
       documents = new ArrayList<DocumentDTO> ();
         try {
          c = DataSourceSingleton.getInstance().getConnection(); 
           String SQL = "SELECT \"document\".\"id\", \"document\".\"fileName\", \"document\".\"idArea\", \"document\".\"deleted\", \"document\".\"isFolder\", \"area\".\"folderName\" FROM \"document\" JOIN \"area\" ON \"document\".\"idArea\" = \"area\".\"id\" WHERE \"document\".\"isFolder\" = TRUE AND \"document\".\"id\" NOT IN( SELECT \"documentRelationships\".\"idDocumentChild\" FROM \"documentRelationships\")";
               ps = c.prepareStatement(SQL);
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       document = new DocumentDTO();
                       document.setIsFolder(rs.getBoolean("isFolder"));
                       document.setId(rs.getInt("id"));
                       
                        document.setFilename(rs.getString("fileName"));
               document.setDeleted(rs.getBoolean("deleted"));
               document.setAscendenteBorrado(rs.getBoolean("deleted"));
                    document.setIdArea(rs.getInt("idArea"));
                   document.getArea().setFolderName(rs.getString("folderName"));
                    String fullPathOriginal = "";
                  pFac = new PropertiesFacade();
                        String pathTrash = pFac.obtenerValorPropiedad("pathForTrash");
                        fullPathOriginal =  pFac.obtenerValorPropiedad("pathForFiles");
                    System.out.println("#######################");
                        savingUp = "";
                        getFullPath(document,document.getFilename());
                        String fp = savingUp;
                        document.setName(fp);
                        System.out.println("ascendientes : " + ascendientes.size());
                        for(Boolean b : ascendientes){
                            if (b){
                                document.setAscendenteBorrado(true);
                                break;
                            }
                        }
                        
                       System.out.println("FP : " + fp);
                       System.out.println("#######################");
                    document.setFullPathToFolder( fp);
                    document.setFullPathToFolderInDeleted(fp);
                    System.out.println("setFullPathToFolder : " + document.getFullPathToFolder());
                     System.out.println("setFullPathToFolderInDeleted : " + document.getFullPathToFolderInDeleted());
                      documents.add(document);
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
        return documents;
    }

    
    
    @Override
    public DocumentGovernmentDTO getDocumentGovernment() {
        DocumentGovernmentDTO dgDto = new DocumentGovernmentDTO();
        ArrayList<DocumentDTO> foldersRoot= new ArrayList<DocumentDTO>();
        foldersRoot = getFolders();
        for (DocumentDTO doc :foldersRoot){
            doc.setChildren(getFoldersChildren (doc));
        }
        dgDto.setChildren(foldersRoot);
        return dgDto;
    }

    @Override
    public ArrayList<DocumentDTO> getFoldersChildren(DocumentDTO dto) {
      
        ArrayList<DocumentDTO> documents = null;
     DocumentDTO document = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
           PropertiesFacade pFac = null;
       documents = new ArrayList<DocumentDTO> ();
         try {
          c = DataSourceSingleton.getInstance().getConnection(); 
           String SQL = "SELECT \"document\".\"fileName\", \"document\".\"id\",\"document\".\"idArea\" FROM \"documentRelationships\" JOIN \"document\" ON \"documentRelationships\".\"idDocumentChild\" = \"document\".\"id\" WHERE \"document\".\"isFolder\" = TRUE AND \"documentRelationships\".\"idDocumentParent\" = ?";
               ps = c.prepareStatement(SQL);
               ps.setInt(1, dto.getId());
               
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       
                       document = new DocumentDTO();
                       
                       document.setId(rs.getInt("id"));
                       document.setIdArea(rs.getInt("idArea"));
                       document.setName(rs.getString("fileName"));
                        document.setFilename(rs.getString("fileName"));
                       documents.add(document);
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
         for (DocumentDTO doc :documents){
            doc.setChildren(getFoldersChildren (doc));
        }
        return documents;}

    @Override
    public String moveDocuments(ArrayList<DocumentDTOWithFolderDTO> documents) {
        
          //Verificar si existe desde el comienzo  
      
    try {
        //No esta agarrando bien el origen
        if (!verificaSiEsDescendiente(documents)){
            
            
        DocumentFacade doFac = new DocumentFacade(); 
          DocumentDTOWithFolderDTO documentoOriginal = documents.get(0);
            
          DocumentDTOWithFolderDTO documentoDestino = documents.get(1);
          ///////////////////////////////////
          documentoOriginal = doFac.getDocument(documentoOriginal);
          System.out.println("documentoOriginal : " + documentoOriginal.getFullPathToFolder());
            System.out.println("documentoOriginal : " + documentoOriginal.getFullPathToFolderInDeleted());
          //////////////////////////////////
          DocumentRelationshipDTO drDto = new DocumentRelationshipDTO ();
          drDto.setIdDocumentChild(documentoOriginal.getId());
          DocumentRelationshipFacade fac = new DocumentRelationshipFacade();
          fac.deleteDocumentRelationship(drDto);
         ///////////////////////////////////
          
            
            
            System.out.println("documents.get(1).getId()  : " + documents.get(1).getId() );
          if (documents.get(1).getId() != 1){
              documentoDestino = doFac.getDocument(documentoDestino);
              if (documentoOriginal.getIdArea()!= documentoDestino.getIdArea() ){
                  System.out.println("son de áreas diferentes, vamos a poner ORDEN EN ESTA VIDA PERROS : documentoDestino.getIdArea() : " + documentoDestino.getIdArea() );
                  documentoOriginal.setIdArea(documentoDestino.getIdArea());
                  doFac.updateDocument(documentoOriginal);
                  //documentoOriginal = doFac.getDocument(documentoOriginal);
              }
              
          drDto = new DocumentRelationshipDTO ();
          drDto.setIdDocumentChild(documentoOriginal.getId());
          drDto.setIdDocumentParent(documentoDestino.getId());
          fac.createDocumentRelationship(drDto);
          }
          else{
              System.out.println("HASTA LA RAIZ WEY");
              PropertiesFacade pDto = new PropertiesFacade();
                String pathTrash = pDto.obtenerValorPropiedad("pathForTrash");
                String fullPathOriginal =  pDto.obtenerValorPropiedad("pathForFiles");
                ///AQUI FALTA EL C1
              documentoDestino.setFullPathToFolder(fullPathOriginal+documentoOriginal.getArea().getFolderName());
              documentoDestino.setFullPathToFolderInDeleted(pathTrash+documentoOriginal.getArea().getFolderName());
              
          }
          
          String pathOrigen="";
          String pathDestino="";
          System.out.println("Documento original");
          if (documentoOriginal.getDeleted()){
            pathOrigen = documentoOriginal.getFullPathToFolderInDeleted();
             
          }
          else{
              pathOrigen = documentoOriginal.getFullPathToFolder();
             
              
          }
          System.out.println("Documento destino");
            if (documentoDestino.getDeleted()){
                 pathDestino = documentoDestino.getFullPathToFolderInDeleted() + "/" + documentoOriginal.getFilename() ;
               
              }
              else{
                pathDestino = documentoDestino.getFullPathToFolder()  + "/" + documentoOriginal.getFilename();
                 
              }
            FilesFacade fFac = new FilesFacade();
             if (fFac.verificaSiExiste(pathDestino)){
                 System.out.println("YA EXISTE ESE NOMBRE");
             pathDestino = fFac.retornaNombreBienParaCarpeta(pathDestino) ;
             System.out.println("pathDestino new : " + pathDestino);
             String nuevoFileName = pathDestino.substring(pathDestino.lastIndexOf("/")+1, pathDestino.length());
              System.out.println("pathDestino  new solo: " + nuevoFileName);
              documentoOriginal.setFilename(nuevoFileName);
          }
            
            
        Files.createDirectories(Paths.get(pathDestino).getParent());
   
          System.out.println("pathOrigen :" + pathOrigen);
          System.out.println("pathDestino :" + pathDestino);
          
          
      
         Files.move(Paths.get(pathOrigen), Paths.get(pathDestino));
         
            
             return "success";
        }
        
           } 
    catch (IOException ex) {
        Logger.getLogger(DocumentDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
        System.out.println("Error");
    return "error1";
    }

   

    @Override
    public Boolean verificaSiEsDescendiente(ArrayList<DocumentDTOWithFolderDTO> documents) {
   
        
     DocumentDTO document = null;
       
        ResultSet rs = null;
        Connection c = null;
        PreparedStatement ps = null;
           PropertiesFacade pFac = null;
       
         try {
          c = DataSourceSingleton.getInstance().getConnection(); 
           String SQL = "SELECT \"document\".\"fileName\", \"document\".\"id\" FROM \"documentRelationships\" JOIN \"document\" ON \"documentRelationships\".\"idDocumentChild\" = \"document\".\"id\" WHERE \"document\".\"isFolder\" = TRUE AND \"documentRelationships\".\"idDocumentParent\" = ?";
               ps = c.prepareStatement(SQL);
               ps.setInt(1, documents.get(0).getId());
               
                 rs = ps.executeQuery();
                   while (rs.next()) {
                       if (rs.getInt("id") == documents.get(1).getId()){
                           System.out.println("Es descendiente");
                           return true;
                       }
                       
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
         for (DocumentDTO doc :documents){
            doc.setChildren(getFoldersChildren (doc));
        }
        return false; }
    
}
