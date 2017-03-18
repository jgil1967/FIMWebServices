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

/**
 *
 * @author jonathangil
 */
public interface googleDriveInterface {
      public void pruebaDrive ();
      public googleDriveBackupDTO subirArchivos ();
     public DocumentDTOWithFolderDTO backupDocument  (DocumentDTOWithFolderDTO dto);
     public DocumentDTOWithFolderDTO backupFolder  (DocumentDTOWithFolderDTO dto);
       public void zipFolder(String srcFolder, String destZipFile) throws Exception;
       public void uploadFile (DocumentDTOWithFolderDTO dto, String nombreBackup, String zipFilePath) throws IOException;
}
