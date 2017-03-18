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
public class googleDriveBackupFacade implements googleDriveBackupInterface {
googleDriveBackupInterface dao = null;

    public googleDriveBackupFacade() {
        dao = new googleDriveBackupDAO();
    }


    @Override
    public googleDriveBackupDTO creategoogleDriveBackup(googleDriveBackupDTO dto) {
   return dao.creategoogleDriveBackup(dto); }

    @Override
    public ArrayList<googleDriveBackupDTO> getBackups() {
   return dao.getBackups();   }

    
}
