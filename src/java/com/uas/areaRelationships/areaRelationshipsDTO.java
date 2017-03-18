/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.areaRelationships;

import java.io.Serializable;

/**
 *
 * @author jonathangil
 */
public class areaRelationshipsDTO implements Serializable {
    int idArea1,idArea2;
    boolean uploadAndEdit;

    public boolean isUploadAndEdit() {
        return uploadAndEdit;
    }

    public void setUploadAndEdit(boolean uploadAndEdit) {
        this.uploadAndEdit = uploadAndEdit;
    }
    

    public int getIdArea1() {
        return idArea1;
    }

    public void setIdArea1(int idArea1) {
        this.idArea1 = idArea1;
    }

    public int getIdArea2() {
        return idArea2;
    }

    public void setIdArea2(int idArea2) {
        this.idArea2 = idArea2;
    }
    
}
