/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.areas;

import com.uas.object.ObjectDTO;

/**
 *
 * @author jonathangil
 */
public class areaDTO extends ObjectDTO{
   public boolean superuser, enabled;
  public boolean uploadAndEdit;
  public String folderName;

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
  

    public boolean isUploadAndEdit() {
        return uploadAndEdit;
    }

    public void setUploadAndEdit(boolean uploadAndEdit) {
        this.uploadAndEdit = uploadAndEdit;
    }
    
    public areaDTO() {
        superuser = false;
        enabled = true;
       kind = "area";
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isSuperuser() {
        return superuser;
    }

    public void setSuperuser(boolean superuser) {
        this.superuser = superuser;
    }
    
    
}
