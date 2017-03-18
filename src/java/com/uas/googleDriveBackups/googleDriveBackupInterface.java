/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.googleDriveBackups;

import com.uas.document.DocumentDTO;
import java.util.ArrayList;

/**
 *
 * @author jonathangil
 */
public interface googleDriveBackupInterface {
    googleDriveBackupDTO creategoogleDriveBackup  (googleDriveBackupDTO dto);
    
    ArrayList <googleDriveBackupDTO> getBackups ();
}
