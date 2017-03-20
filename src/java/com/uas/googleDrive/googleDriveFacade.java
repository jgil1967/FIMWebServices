/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.googleDrive;

import com.uas.document.DocumentDTO;
import com.uas.document.DocumentDTOWithFolderDTO;
import com.uas.googleDriveBackups.googleDriveBackupDTO;
import java.io.IOException;
import com.uas.googleDrive.googleDriveDAO;

/**
 *
 * @author jonathangil
 */
public class googleDriveFacade implements googleDriveInterface {
googleDriveInterface dao = null;

    public googleDriveFacade() {
        dao = new googleDriveDAO();
    }

 
    @Override
    public DocumentDTOWithFolderDTO backupDocument(DocumentDTOWithFolderDTO dto) {
   return dao.backupDocument(dto);   }
    @Override
    public void pruebaDrive() {
    dao.pruebaDrive();
    
    }

    @Override
    public googleDriveBackupDTO subirArchivos() {
   return  dao.subirArchivos(); }

    @Override
    public void zipFolder(String srcFolder, String destZipFile) throws Exception {
      dao.zipFolder(srcFolder,destZipFile); }

    @Override
    public void uploadFile(DocumentDTOWithFolderDTO dto, String nombreBackup, String zipFilePath) throws IOException {
      dao.uploadFile(dto,nombreBackup,zipFilePath); }

    @Override
    public DocumentDTOWithFolderDTO backupFolder(DocumentDTOWithFolderDTO dto) {
      return dao.backupFolder(dto); 
    }
    
}
