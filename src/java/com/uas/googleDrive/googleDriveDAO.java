/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.googleDrive;

import com.uas.googleDriveBackups.googleDriveBackupDTO;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.uas.document.DocumentDTO;
import com.uas.document.DocumentDTOWithFolderDTO;
import com.uas.document.DocumentFacade;
import com.uas.googleDriveBackups.googleDriveBackupFacade;
import com.uas.properties.PropertiesFacade;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;



/**
 *
 * @author jonathangil
 */
public class googleDriveDAO implements googleDriveInterface{
//documentosfim2017@gmail.com
//Fimdocumentos12
    /** Application name. */
    private static final String APPLICATION_NAME =
        "FIMWebServices";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials3/drive-java-quickstart");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/drive-java-quickstart
     */
    private static final List<String> SCOPES =
        Arrays.asList(DriveScopes.DRIVE);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in = googleDriveDAO.class.getResourceAsStream("client_secret.json");
       
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Drive client service.
     * @return an authorized Drive client service
     * @throws IOException
     */
    public static Drive getDriveService() throws IOException {
        Credential credential = authorize();
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    @Override
    public void pruebaDrive() {
      // Build a new authorized API client service.
       
        try {
             Drive service = getDriveService();
            // Print the names and IDs for up to 10 files.
        FileList result = service.files().list()
             .setPageSize(20)
             .setFields("nextPageToken, files(id, name)")
             .execute();
        ////////////////////////////
        List<File> files = result.getFiles();
        if (files == null || files.size() == 0) {
            System.out.println("No files found.");
        } else {
            System.out.println("Files:");
            for (File file : files) {
                System.out.printf("%s (%s)\n", file.getName(), file.getId());
            }
        }
        } catch (IOException ex) {
            Logger.getLogger(googleDriveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    
    }

    public String returnPath(String property){
	Properties prop = new Properties();
	InputStream input = null;
	try {
		input = new FileInputStream("config.properties");
		prop.load(input);
		return prop.getProperty(property);

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        return null;
    }
    
    
    
    public void zipFolder(String srcFolder, String destZipFile) throws Exception {
        System.out.println("srcFolder : " + srcFolder);
          System.out.println("destZipFile : " + destZipFile);
        ZipOutputStream zip = null;
    FileOutputStream fileWriter = null;
    Files.createDirectories(Paths.get(destZipFile).getParent()); 
    fileWriter = new FileOutputStream(destZipFile);
    zip = new ZipOutputStream(fileWriter);

    addFolderToZip("", srcFolder, zip);
    zip.flush();
    zip.close();
  }

  static private void addFileToZip(String path, String srcFile, ZipOutputStream zip)
      throws Exception {

    java.io.File folder = new java.io.File(srcFile);
    if (folder.isDirectory()) {
      addFolderToZip(path, srcFile, zip);
    } else {
      byte[] buf = new byte[1024];
      int len;
      FileInputStream in = new FileInputStream(srcFile);
      zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
      while ((len = in.read(buf)) > 0) {
        zip.write(buf, 0, len);
      }
    }
  }

  static private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip)
      throws Exception {
    java.io.File folder = new java.io.File(srcFolder);
   System.out.println("srcFolder addFolderToZip: " +srcFolder);
   if (folder.exists()){
       
   
    if (folder.list().length == 0){
        System.out.println("Carpeta Vacía");
        String filePath = srcFolder + "/emptyFolder.txt";
        //System.out.println("filePath : " + filePath);
         java.io.File file = new java.io.File(filePath);
         file.createNewFile();
          for (String fileName : folder.list()) {
      if (path.equals("")) {
        addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
      } else {
        addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
      }
    }
    }else{
        for (String fileName : folder.list()) {
      if (path.equals("")) {
        addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
      } else {
        addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
      }
    }
    
    }
    
    }
    
      
  }
    
  
  
    /**
     * Zip it
     * @param zipFile output ZIP file location
     */
    public void zipIt(String zipFile){

     byte[] buffer = new byte[1024];

     try{

    	FileOutputStream fos = new FileOutputStream(zipFile);
    	ZipOutputStream zos = new ZipOutputStream(fos);

    	System.out.println("Output to Zip : " + zipFile);

    	for(String file : this.fileList){

    		System.out.println("File intentado de agregar : " + file);
    		ZipEntry ze= new ZipEntry(file);
                
        	//zos.putNextEntry(ze);
java.io.File f = new java.io.File(file); //"Users/you/image.jpg"
zos.putNextEntry(new ZipEntry(f.getName())); //"image.jpg"
        	FileInputStream in =new FileInputStream(file);

        	int len;
        	while ((len = in.read(buffer)) > 0) {
        		zos.write(buffer, 0, len);
        	}

        	in.close();
    	}

    	zos.closeEntry();
    	
    	zos.close();

    	System.out.println("Done");
    }catch(IOException ex){
       ex.printStackTrace();
    }
   }

    
  
   List<String> fileList;
     String OUTPUT_ZIP_FILE = "";
   String SOURCE_FOLDER = "";

   public void uploadFile (DocumentDTOWithFolderDTO dto, String nombreBackup, String zipFilePath) throws IOException{
          java.io.File f =new  java.io.File(OUTPUT_ZIP_FILE);
       Drive service = getDriveService();
File fileMetadata = new File();
fileMetadata.setName(nombreBackup);
       System.out.println("zipFilePath: " + zipFilePath);
java.io.File filePath = new java.io.File(zipFilePath);
FileContent mediaContent = new FileContent("application/octet-stream", filePath);
File file = service.files().create(fileMetadata, mediaContent)
        .setFields("id")
        .execute();


System.out.println("File ID: " + file.getId());
  googleDriveBackupDTO gDto = new googleDriveBackupDTO();
              gDto.setFileName(nombreBackup);
              gDto.setLength(f.length());
              gDto.setId(file.getId());
              googleDriveBackupFacade gFac = new googleDriveBackupFacade();
      gFac.creategoogleDriveBackup(gDto);
        
        dto.setBackedUp(true);
        DocumentFacade fac = new DocumentFacade();
             fac.updateDocument(dto);
   }
   
   
     @Override
    public DocumentDTOWithFolderDTO backupDocument(DocumentDTOWithFolderDTO dto) {
    try {
        
      String nombreBackup = dto.getFilename().substring(0, dto.getFilename().lastIndexOf('.'));
          nombreBackup = nombreBackup+".zip";
        
        if (dto.getAscendenteBorrado()){
            
           SOURCE_FOLDER = dto.getFullPathToFolderInDeleted();
        }
        else{
            SOURCE_FOLDER = dto.getFullPathToFolder();
        }
             
             
             OUTPUT_ZIP_FILE =  returnPath("pathForDocumentBackups")+nombreBackup;;
             fileList = new ArrayList<String>();
                fileList.add(SOURCE_FOLDER);
            zipIt(OUTPUT_ZIP_FILE);
            uploadFile(dto, nombreBackup,OUTPUT_ZIP_FILE);
     

    }
    catch (Exception e){
        e.printStackTrace();
    }
    return dto;
    }
    
    public googleDriveBackupDTO createAndUpload (String nombreBackup, String sourceDirPath, String zipFilePath) throws Exception{
        System.out.println(sourceDirPath);
             System.out.println(zipFilePath);
             
             zipFolder(sourceDirPath, zipFilePath);
              java.io.File f =new  java.io.File(zipFilePath);
            
             Drive service = getDriveService();
      
File fileMetadata = new File();
fileMetadata.setName(nombreBackup);


java.io.File filePath = new java.io.File(zipFilePath);
          
FileContent mediaContent = new FileContent("application/octet-stream", filePath);
File file = service.files().create(fileMetadata, mediaContent)
        .setFields("id")
        .execute();
System.out.println("File ID: " + file.getId());
  googleDriveBackupDTO gDto = new googleDriveBackupDTO();
              gDto.setFileName(nombreBackup);
              gDto.setLength(f.length());
              gDto.setId(file.getId());
              googleDriveBackupFacade gFac = new googleDriveBackupFacade();
     return gFac.creategoogleDriveBackup(gDto);
    }
    
    @Override
    public googleDriveBackupDTO subirArchivos() {
      try {
          //long unixTime = System.currentTimeMillis() / 1000L;
          String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
          String nombreBackup = "FullFIMBackup#"+timeStamp+".zip";
          
          String sourceDirPath = returnPath("pathForFullBackup");
             String zipFilePath =  returnPath("pathForDocumentBackups")+nombreBackup;
            return createAndUpload(nombreBackup,sourceDirPath,zipFilePath);
      } 
      catch (Exception ex) {
            ex.printStackTrace();
        }
    return null;
    }
    PropertiesFacade pFac= null;
    @Override
    public DocumentDTOWithFolderDTO backupFolder(DocumentDTOWithFolderDTO dto) {
     try {pFac = new PropertiesFacade();
   String  zipFilePathFolder = pFac.obtenerValorPropiedad("pathForDocumentBackups")+dto.getFilename()+".zip";
   String source ="";
   if (dto.getAscendenteBorrado()){
           source = dto.getFullPathToFolderInDeleted();     
      }
      else{
           source = dto.getFullPathToFolder();
      }
       
            zipFolder(source, zipFilePathFolder);
       uploadFile(dto, dto.getFilename(), zipFilePathFolder);
        
         } catch (Exception ex) {
           ex.printStackTrace();
        }
     return null;
    }
    
}
